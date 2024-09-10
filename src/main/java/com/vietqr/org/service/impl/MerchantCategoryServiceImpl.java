package com.vietqr.org.service.impl;

import com.vietqr.org.constant.Status;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.common.ResponseObjectDTO;
import com.vietqr.org.dto.merchantcategory.InsertMerchantCategoryDTO;
import com.vietqr.org.entity.MerchantCategoryEntity;
import com.vietqr.org.repository.MerchantCategoryRepository;
import com.vietqr.org.service.MerchantCategoryService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MerchantCategoryServiceImpl implements MerchantCategoryService {

    private static final Logger logger = Logger.getLogger(MerchantCategoryServiceImpl.class);
    private final MerchantCategoryRepository merchantCategoryRepository;


    public MerchantCategoryServiceImpl(MerchantCategoryRepository merchantCategoryRepository) {
        this.merchantCategoryRepository = merchantCategoryRepository;
    }

    @Override
    public ResponseMessageDTO saveMerchantCategory(InsertMerchantCategoryDTO categoryDTO) {
        ResponseMessageDTO result;
        try {
            MerchantCategoryEntity merchantCategory = new MerchantCategoryEntity();
            String id = generateUniqueId();
            merchantCategory.setId(id);
            merchantCategory.setMid(categoryDTO.getMid());
            merchantCategory.setName(categoryDTO.getName());
            merchantCategory.setStatus(true);
            merchantCategoryRepository.save(merchantCategory);
            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            logger.error("ERROR saveMerchantCategory: " + e.getMessage() + " at " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }
        return result;
    }

    @Override
    public ResponseMessageDTO updateMerchantCategory(String id, InsertMerchantCategoryDTO categoryDTO) {
        ResponseMessageDTO result;
        try {
            Optional<MerchantCategoryEntity> optionalMerchantCategory = merchantCategoryRepository.findMerchantCategoryById(id);
            if (optionalMerchantCategory.isPresent()) {
                MerchantCategoryEntity merchantCategory = optionalMerchantCategory.get();
                if (categoryDTO.getMid() != null && !categoryDTO.getMid().isEmpty()) {
                    merchantCategory.setMid(categoryDTO.getMid());
                }
                if (categoryDTO.getName() != null && !categoryDTO.getName().isEmpty()) {
                    merchantCategory.setName(categoryDTO.getName());
                }
                merchantCategoryRepository.save(merchantCategory);
            }
            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            logger.error("ERROR updateMerchantCategory: " + e.getMessage() + " at " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }
        return result;
    }

    @Override
    public Object getListMerchantCategory() {
        Object result;
        try {
            List<MerchantCategoryEntity> merchantCategoryEntityList = merchantCategoryRepository.getAllMerchantCategory();
            result = new ResponseObjectDTO(Status.SUCCESS, merchantCategoryEntityList);
        } catch (Exception e) {
            logger.error("ERROR listMerchantCategory: " + e.getMessage() + " at " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }
        return result;
    }

    @Override
    public Object getMerchantCategoryById(String id) {
        Object result;
        try {
            Optional<MerchantCategoryEntity> merchantCategory = merchantCategoryRepository.findMerchantCategoryById(id);
            if (merchantCategory.isPresent()) {
                MerchantCategoryEntity merchantCategoryEntity = merchantCategory.get();
                result = new ResponseObjectDTO(Status.SUCCESS, merchantCategoryEntity);
            } else {
                result = new ResponseMessageDTO(Status.FAILED, "E05");
            }
        } catch (Exception e) {
            logger.error("ERROR getMerchantCategoryById: " + e.getMessage() + " at " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }
        return result;
    }

    @Override
    public ResponseMessageDTO deleteMerchantCategory(String id) {
        ResponseMessageDTO result;
        try {
            Optional<MerchantCategoryEntity> optionalMerchantCategory = merchantCategoryRepository.findMerchantCategoryById(id);
            if (optionalMerchantCategory.isPresent()) {
                MerchantCategoryEntity merchantCategory = optionalMerchantCategory.get();
                merchantCategory.setStatus(false);
                merchantCategoryRepository.save(merchantCategory);
            }
            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            logger.error("ERROR removeMerchantCategory: " + e.getMessage() + " at " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }
        return result;
    }

    private String generateUniqueId() {
        String newId;
        do {
            newId = UUID.randomUUID().toString();
        } while (merchantCategoryRepository.existsById(newId));
        return newId;
    }
}
