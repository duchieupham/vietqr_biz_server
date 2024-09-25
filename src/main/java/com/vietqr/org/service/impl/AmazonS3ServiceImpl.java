package com.vietqr.org.service.impl;

import com.vietqr.org.constant.Status;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.service.AmazonS3Service;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.UUID;
@Service
public class AmazonS3ServiceImpl implements AmazonS3Service {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(AmazonS3ServiceImpl.class);

    private final S3Client s3Client;

    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    public AmazonS3ServiceImpl(S3Client s3Client) {
        this.s3Client = s3Client;
    }


    @Override
    public ResponseMessageDTO uploadFile(String key, MultipartFile file) {
        ResponseMessageDTO result;
        File filePut = null;
        try {
            String fileName = file.getOriginalFilename();
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .metadata(Collections.singletonMap("name", fileName))
                    .build();
            filePut = convertMultiPartToFile(file);
            s3Client.putObject(request, RequestBody.fromFile(filePut));
            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            logger.error("ERROR uploadFile: " + e.getMessage() + " at " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        } finally {
            if (filePut != null && filePut.exists()) {
                boolean deleted = filePut.delete();
                if (!deleted) {
                    logger.warn("WARNING: Failed to delete temporary file: " + filePut.getAbsolutePath());
                }
            }
        }
        return result;
    }

    private File convertMultiPartToFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String fileName = (originalFilename != null && !originalFilename.isEmpty()) ? originalFilename : UUID.randomUUID().toString();
        File converedFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(converedFile)){
            fos.write(file.getBytes());
        } catch (IOException e) {
            logger.error("ERROR convertMultiPartToFile: " + e.getMessage() + " at " +System.currentTimeMillis());
        }
        return converedFile;
    }
}
