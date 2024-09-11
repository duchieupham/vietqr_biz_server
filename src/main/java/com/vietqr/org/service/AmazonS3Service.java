package com.vietqr.org.service;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import org.springframework.web.multipart.MultipartFile;

public interface AmazonS3Service {
    ResponseMessageDTO uploadFile(String key, MultipartFile file);
}
