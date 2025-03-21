package com.vortex.apirest.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class EmailService {
    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;

    /**
     * Método para enviar un correo con SendGrid.
     * 
     * @param toEmail  Destinatario del correo
     * @param subject  Asunto del correo
     * @param htmlBody Cuerpo del correo en formato HTML
     */
    public void sendEmail(String toEmail, String subject, String htmlBody) {
        Email from = new Email("noreply@tu-dominio.com"); // Cambiar por tu dominio verificado
        Email to = new Email(toEmail);
        Content content = new Content("text/html", htmlBody);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            System.out.println("Correo enviado con código: " + response.getStatusCode());
        } catch (IOException ex) {
            throw new RuntimeException("Error al enviar correo con SendGrid: " + ex.getMessage());
        }
    }
}
