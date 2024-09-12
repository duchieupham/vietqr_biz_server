package com.vietqr.org.service;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.entity.ImageEntity;

public interface ImageService {
    ResponseMessageDTO saveImage(ImageEntity image);
}
