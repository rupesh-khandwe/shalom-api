package com.shalom.shalomapi.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.shalom.shalomapi.Config.AWSClientConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@RequiredArgsConstructor
@Service
public class DocumentService {

    private final AmazonS3 amazonS3;
    private final AWSClientConfig awsClientConfig;

    public String upload(InputStream stream, ObjectMetadata meta, String fileName) {
        //File localFile = convertMultipartFileToFile(file);

        amazonS3.putObject(new PutObjectRequest(awsClientConfig.getBucketName(), fileName, stream, meta));
        return "https://"+awsClientConfig.getBucketName()+".s3.amazonaws.com/"+fileName+"|";
    }

    private File convertMultipartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try {
            Files.copy(file.getInputStream(), convertedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return convertedFile;
    }

}