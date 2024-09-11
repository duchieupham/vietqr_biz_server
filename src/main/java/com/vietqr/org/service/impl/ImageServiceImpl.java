package com.vietqr.org.service.impl;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.entity.ImageEntity;
import com.vietqr.org.repository.ImageRepository;
import com.vietqr.org.service.ImageService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

    private static final Logger logger = Logger.getLogger(ImageServiceImpl.class);

    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public ResponseMessageDTO saveImage(ImageEntity image) {
        ResponseMessageDTO result;
        try {
            imageRepository.save(image);
        } catch (Exception e) {
            logger.error("");
        }
        return null;
    }
}
