package com.vortex.apirest.service;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import org.springframework.stereotype.Service;

import com.vortex.apirest.ConfigProject.AwsS3Properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class S3Service {

    private final AwsS3Properties awsS3Properties;
    private final S3Client s3Client;

    public S3Service(AwsS3Properties awsS3Properties, S3Client s3Client) {
        this.awsS3Properties = awsS3Properties;
        this.s3Client = s3Client;
    }

    public String uploadFile(InputStream fileStream, String contentType, long fileSize) {
        String fileName = UUID.randomUUID().toString() + (contentType.equals("image/png") ? ".png" : ".jpg");
        String bucketName = awsS3Properties.getBucketName();
    
        PutObjectRequest putRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(fileName)
            .contentType(contentType)
            .build();
    
        s3Client.putObject(putRequest, RequestBody.fromInputStream(fileStream, fileSize));
    
        return String.format("https://%s.s3.%s.amazonaws.com/%s", 
            bucketName, 
            awsS3Properties.getRegion(), 
            fileName);
    }
    

    public void deleteFile(String fileUrl) {
        String bucketName = awsS3Properties.getBucketName();

        // Extraer el nombre del archivo desde la URL
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);

        // Crear la solicitud de eliminación
        DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        // Ejecutar la eliminación en S3
        s3Client.deleteObject(deleteRequest);
    }
}
