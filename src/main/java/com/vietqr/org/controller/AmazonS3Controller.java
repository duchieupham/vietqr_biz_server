package com.vietqr.org.controller;

import com.vietqr.org.common.StatusResponse;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.service.AmazonS3Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class AmazonS3Controller {

    private final AmazonS3Service amazonS3Service;

    public AmazonS3Controller(AmazonS3Service amazonS3Service) {
        this.amazonS3Service = amazonS3Service;
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessageDTO> uploadFile(@RequestPart("file")MultipartFile file) {
        ResponseMessageDTO response = amazonS3Service.uploadFile(file.getOriginalFilename(), file);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }
}
