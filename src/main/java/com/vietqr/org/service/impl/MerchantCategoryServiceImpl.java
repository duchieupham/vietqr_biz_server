package com.vietqr.org.service.impl;

import com.vietqr.org.constant.Status;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.common.ResponseObjectDTO;
import com.vietqr.org.dto.merchantcategory.IMerchantCategoryMidDTO;
import com.vietqr.org.dto.merchantcategory.IMerchantCategoryIdDTO;
import com.vietqr.org.dto.merchantcategory.MerchantCategoryDTO;
import com.vietqr.org.entity.MerchantCategoryEntity;
import com.vietqr.org.repository.MerchantCategoryRepository;
import com.vietqr.org.service.MerchantCategoryService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MerchantCategoryServiceImpl implements MerchantCategoryService {
    private static final Logger logger = Logger.getLogger(MerchantCategoryServiceImpl.class);
    private final MerchantCategoryRepository merchantCategoryRepository;


    public MerchantCategoryServiceImpl(MerchantCategoryRepository merchantCategoryRepository) {
        this.merchantCategoryRepository = merchantCategoryRepository;
    }

    @Override
    public ResponseMessageDTO insertMerchantCategory(MerchantCategoryDTO categoryDTO) {
        ResponseMessageDTO result;
        try {
            MerchantCategoryEntity merchantCategory = new MerchantCategoryEntity();
            String id = generateUniqueId();
            merchantCategory.setId(id);
            merchantCategory.setMid(categoryDTO.getMid());
            merchantCategory.setName(categoryDTO.getName());
            merchantCategory.setStatus(1);
            merchantCategoryRepository.save(merchantCategory);

            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            logger.error("ERROR saveMerchantCategory: " + e.getMessage() + " at " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }

        return result;
    }

    @Override
    public ResponseMessageDTO updateMerchantCategory(String id, MerchantCategoryDTO categoryDTO) {
        ResponseMessageDTO result;
        try {
            Optional<IMerchantCategoryIdDTO> optionalMerchantCategory = merchantCategoryRepository.findMerchantCategoryById(id.trim());
            if (optionalMerchantCategory.isPresent()) {
                IMerchantCategoryIdDTO dto = optionalMerchantCategory.get();
                MerchantCategoryEntity merchantCategory = new MerchantCategoryEntity(
                        id.trim(),
                        dto.getMid(),
                        dto.getName(),
                        dto.getStatus()
                );
                if (!categoryDTO.getMid().isEmpty()) {
                    merchantCategory.setMid(categoryDTO.getMid());
                }
                if (!categoryDTO.getName().isEmpty()) {
                    merchantCategory.setName(categoryDTO.getName());
                }
                merchantCategoryRepository.save(merchantCategory);
                result = new ResponseMessageDTO(Status.SUCCESS, "");
            } else {
                logger.error("ERROR updateMerchantCategory: Not found at " + System.currentTimeMillis());
                result = new ResponseMessageDTO(Status.FAILED, "E209");
            }
        } catch (Exception e) {
            logger.error("ERROR updateMerchantCategory: " + e.getMessage() + " at " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }

        return result;
    }

    @Override
    public Object getMerchantCategoryByMid(String mid) {
        Object result;
        try {
            List<IMerchantCategoryMidDTO> list = merchantCategoryRepository.findMerchantCategoryByMid(mid.trim());
            result = new ResponseObjectDTO(Status.SUCCESS, list);
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
            Optional<IMerchantCategoryIdDTO> merchantCategory = merchantCategoryRepository.findMerchantCategoryById(id.trim());
            if (merchantCategory.isPresent()) {
                result = new ResponseObjectDTO(Status.SUCCESS, merchantCategory.get());
            } else {
                logger.error("ERROR getMerchantCategoryById: Not found at " + System.currentTimeMillis());
                result = new ResponseMessageDTO(Status.FAILED, "E209");
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
            Optional<IMerchantCategoryIdDTO> optionalMerchantCategory = merchantCategoryRepository.findMerchantCategoryById(id.trim());
            if (optionalMerchantCategory.isPresent()) {
                if (optionalMerchantCategory.get().getStatus() != 0) {
                    MerchantCategoryEntity merchantCategory = new MerchantCategoryEntity(
                            id.trim(),
                            optionalMerchantCategory.get().getMid(),
                            optionalMerchantCategory.get().getName(),
                            0
                    );

                    merchantCategoryRepository.save(merchantCategory);
                    result = new ResponseMessageDTO(Status.SUCCESS, "");
                } else {
                    logger.error("ERROR updateMerchantCategory: Category was deleted at " + System.currentTimeMillis());
                    result = new ResponseMessageDTO(Status.FAILED, "E210");
                }
            } else {
                logger.error("ERROR updateMerchantCategory: Not found at " + System.currentTimeMillis());
                result = new ResponseMessageDTO(Status.FAILED, "E209");
            }
        } catch (Exception e) {
            logger.error("ERROR removeMerchantCategory: " + e.getMessage() + " at " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }

        return result;
    }

    @Override
    public ResponseMessageDTO deleteMerchantCategoryByMid(String mid) {
        ResponseMessageDTO result;
        try {
            List<IMerchantCategoryMidDTO> merchantCategory = merchantCategoryRepository.findMerchantCategoryByMid(mid.trim());
            if (!merchantCategory.isEmpty()) {
                List<MerchantCategoryEntity> list = merchantCategory
                        .stream()
                        .map((item) ->
                                new MerchantCategoryEntity(
                                        item.getId(),
                                        mid.trim(),
                                        item.getName(),
                                        0
                                )
                        ).collect(Collectors.toList());
                merchantCategoryRepository.saveAll(list);

                result = new ResponseMessageDTO(Status.SUCCESS, "");
            } else {
                logger.error("ERROR updateMerchantCategory: Not found at " + System.currentTimeMillis());
                result = new ResponseMessageDTO(Status.FAILED, "E209");
            }
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
