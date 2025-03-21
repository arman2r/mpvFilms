package com.vortex.apirest.ConfigProject;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration  // Indica que esta clase es de configuración
@ConfigurationProperties(prefix = "aws.s3")  // Mapea las propiedades del archivo .properties
public class AwsS3Properties {

    private String accessKey;
    private String secretKey;
    private String bucketName;
    private String region;

    // Getters y Setters
    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
