package com.vietqr.org.service.impl;

import com.vietqr.org.constant.Status;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.common.ResponseObjectDTO;
import com.vietqr.org.dto.merchantproduct.MerchantProductDTO;
import com.vietqr.org.dto.productprice.ProductPriceDTO;
import com.vietqr.org.dto.productprice.ProductPriceInsertProductDTO;
import com.vietqr.org.entity.ImageEntity;
import com.vietqr.org.entity.MerchantProductEntity;
import com.vietqr.org.repository.ImageRepository;
import com.vietqr.org.repository.MerchantProductRepository;
import com.vietqr.org.service.AmazonS3Service;
import com.vietqr.org.service.ImageService;
import com.vietqr.org.service.MerchantProductService;
import com.vietqr.org.service.ProductPriceService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MerchantProductServiceImpl implements MerchantProductService {
    private static final Logger logger = Logger.getLogger(MerchantProductServiceImpl.class);
    private final MerchantProductRepository merchantProductRepository;
    private final AmazonS3Service amazonS3Service;
    private final ImageService imageService;
    private final ImageRepository imageRepository;

    private final ProductPriceService productPriceService;

    public MerchantProductServiceImpl(MerchantProductRepository merchantProductRepository, AmazonS3Service amazonS3Service, ImageService imageService, ImageRepository imageRepository, ProductPriceService productPriceService) {
        this.merchantProductRepository = merchantProductRepository;
        this.amazonS3Service = amazonS3Service;
        this.imageService = imageService;
        this.imageRepository = imageRepository;
        this.productPriceService = productPriceService;
    }

    @Override
    public ResponseMessageDTO saveMerchantProduct(MultipartFile file, MerchantProductDTO productInsertDTO, ProductPriceInsertProductDTO productPriceInsertProductDTO, String token) {
        ResponseMessageDTO result;
        try {
            MerchantProductEntity productEntity = new MerchantProductEntity();
            String id = generateUniqueId();
            productEntity.setId(id);

            String imgId = handleImageUpload(file, null);
            productEntity.setImgId(imgId);

            productEntity.setCategoryId(productInsertDTO.getCategoryId());
            productEntity.setName(productInsertDTO.getName());
            productEntity.setUnit(productInsertDTO.getUnit());
            productEntity.setStatus(0);
            productEntity.setTid(productInsertDTO.getTid());

            ProductPriceDTO productPriceDTO = new ProductPriceDTO(
                    id,
                    productPriceInsertProductDTO.getAmount(),
                    productPriceInsertProductDTO.getContent(),
                    productPriceInsertProductDTO.getBankAccount(),
                    productPriceInsertProductDTO.getBankCode(),
                    productPriceInsertProductDTO.getTransType(),
                    productPriceInsertProductDTO.getServiceCode()
            );

            merchantProductRepository.save(productEntity);
            productPriceService.insertProductPrice(productPriceDTO, token);
            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            logger.error("ERROR saveMerchantProduct: " + e.getMessage() + " at " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }
        return result;
    }

    @Override
    public ResponseMessageDTO updateMerchantProduct(String id, MerchantProductDTO productInsertDTO, MultipartFile file) {
        ResponseMessageDTO result;
        try {
            Optional<MerchantProductEntity> optionalMerchantProduct = merchantProductRepository.getMerchantProductById(id);
            if (optionalMerchantProduct.isPresent()) {
                MerchantProductEntity merchantProduct = optionalMerchantProduct.get();

                String imgId = handleImageUpload(file, merchantProduct.getImgId());
                merchantProduct.setImgId(imgId);

                merchantProductRepository.save(updateFieldProduct(merchantProduct, productInsertDTO));
                result = new ResponseMessageDTO(Status.SUCCESS, "");
            } else {
                result = new ResponseMessageDTO(Status.FAILED, "E05");
            }
        } catch (Exception e) {
            logger.error("ERROR updateMerchantProduct: " + e.getMessage() + " at " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }
        return result;
    }

    @Override
    public Object getListMerchantProduct() {
        Object result;
        try {
            List<MerchantProductEntity> productEntityList = merchantProductRepository.getAllMerchantProduct();
            result = new ResponseObjectDTO(Status.SUCCESS, productEntityList);
        } catch (Exception e) {
            logger.error("ERROR getListMerchantProduct: " + e.getMessage() + " at " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }
        return result;
    }

    @Override
    public Object getMerchantProductById(String id) {
        Object result;
        try {
            Optional<MerchantProductEntity> optionalMerchantProduct = merchantProductRepository.getMerchantProductById(id);
            if (optionalMerchantProduct.isPresent()) {
                MerchantProductEntity merchantProduct = optionalMerchantProduct.get();
                result = new ResponseObjectDTO(Status.SUCCESS, merchantProduct);
            } else {
                result = new ResponseMessageDTO(Status.FAILED, "E05");
            }
        } catch (Exception e) {
            logger.error("ERROR getMerchantProductById: " + e.getMessage() + " at " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }
        return result;
    }

    @Override
    public ResponseMessageDTO deleteMerchantProduct(String id) {
        ResponseMessageDTO result;
        try {
            Optional<MerchantProductEntity> optionalMerchantProduct = merchantProductRepository.getMerchantProductById(id);
            if (optionalMerchantProduct.isPresent()) {
                MerchantProductEntity merchantProduct = optionalMerchantProduct.get();
                merchantProduct.setStatus(2);
                merchantProductRepository.save(merchantProduct);
            }
            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            logger.error("ERROR deleteMerchantProduct: " + e.getMessage() + " at " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }
        return result;
    }

    private String generateUniqueId() {
        String newId;
        do {
            newId = UUID.randomUUID().toString();
        } while (merchantProductRepository.existsById(newId));
        return newId;
    }

    private MerchantProductEntity updateFieldProduct(MerchantProductEntity productEntity, MerchantProductDTO productInsertDTO) {
        if (productInsertDTO.getCategoryId() != null && !productInsertDTO.getCategoryId().isEmpty()) {
            productEntity.setCategoryId(productInsertDTO.getCategoryId());
        }
        if (productInsertDTO.getName() != null && !productInsertDTO.getName().isEmpty()) {
            productEntity.setName(productInsertDTO.getName());
        }
        if (productInsertDTO.getUnit() != null && !productInsertDTO.getUnit().isEmpty()) {
            productEntity.setUnit(productInsertDTO.getUnit());
        }
        if (productInsertDTO.getTid() != null && !productInsertDTO.getTid().isEmpty()) {
            productEntity.setUnit(productInsertDTO.getUnit());
        }
        return productEntity;
    }

    private String handleImageUpload(MultipartFile file, String currentImgId) throws IOException {
        if (file != null && !file.isEmpty()) {
            String fileName = file.getOriginalFilename() != null ? StringUtils.cleanPath(file.getOriginalFilename()) : "default-file-name";
            ImageEntity existingImage = imageRepository.findImageByName(fileName);

            if (existingImage != null) {
                return existingImage.getId();
            } else {
                String uuid = UUID.randomUUID().toString();
                ImageEntity newImage = new ImageEntity(uuid, fileName, file.getBytes());
                try {
                    Thread thread = new Thread(() -> amazonS3Service.uploadFile(uuid, file));
                    thread.start();
                } catch (Exception e) {
                    logger.error("handleImageUpload: AmazonS3 ERROR: " + e.getMessage() + " at " + System.currentTimeMillis());
                }
                imageService.saveImage(newImage);
                return uuid;
            }
        }
        return currentImgId;
    }
}
