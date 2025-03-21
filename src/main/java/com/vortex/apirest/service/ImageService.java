package com.vortex.apirest.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

    private final S3Service s3Service;

    public ImageService(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    public String uploadImage(MultipartFile image) throws Exception {
        return s3Service.uploadFile(image.getInputStream(), image.getContentType(), image.getSize());
    }

    public String uploadBase64Image(String base64Image) throws Exception {
        String contentType = base64Image.startsWith("data:image/png") ? "image/png" : "image/jpeg";
        String base64Data = base64Image.split(",")[1];

        byte[] imageBytes = Base64.getDecoder().decode(base64Data);
        InputStream imageStream = new ByteArrayInputStream(imageBytes);

        return s3Service.uploadFile(imageStream, contentType, imageBytes.length);
    }

    public void deleteImage(String imageUrl) {
        s3Service.deleteFile(imageUrl);
    }

}
