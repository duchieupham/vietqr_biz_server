package com.vietqr.org.service.impl;

import com.vietqr.org.constant.Status;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.common.ResponseObjectDTO;
import com.vietqr.org.dto.merchantproduct.MerchantProductDTO;
import com.vietqr.org.entity.MerchantProductEntity;
import com.vietqr.org.repository.MerchantProductRepository;
import com.vietqr.org.service.MerchantProductService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MerchantProductServiceImpl implements MerchantProductService {
    private static final Logger logger = Logger.getLogger(MerchantProductServiceImpl.class);
    private final MerchantProductRepository merchantProductRepository;

    public MerchantProductServiceImpl(MerchantProductRepository merchantProductRepository) {
        this.merchantProductRepository = merchantProductRepository;
    }

    @Override
    public ResponseMessageDTO saveMerchantProduct(MerchantProductDTO productInsertDTO) {
        ResponseMessageDTO result;
        try {
            MerchantProductEntity productEntity = new MerchantProductEntity();
            String id = generateUniqueId();
            productEntity.setId(id);
            productEntity.setImgId(productInsertDTO.getImgId());
            productEntity.setCategoryId(productInsertDTO.getCategoryId());
            productEntity.setName(productInsertDTO.getName());
            productEntity.setUnit(productInsertDTO.getUnit());
            productEntity.setStatus(0);
            productEntity.setTid(productInsertDTO.getTid());
            merchantProductRepository.save(productEntity);
            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            logger.error("ERROR saveMerchantProduct: " + e.getMessage() + " at " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }
        return result;
    }

    @Override
    public ResponseMessageDTO updateMerchantProduct(String id, MerchantProductDTO productInsertDTO) {
        ResponseMessageDTO result;
        try {
            Optional<MerchantProductEntity> optionalMerchantProduct = merchantProductRepository.getMerchantProductById(id);
            if (optionalMerchantProduct.isPresent()) {
                MerchantProductEntity merchantProduct = optionalMerchantProduct.get();
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
        if (productInsertDTO.getImgId() != null && !productInsertDTO.getImgId().isEmpty()) {
            productEntity.setImgId(productInsertDTO.getImgId());
        }
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
}
