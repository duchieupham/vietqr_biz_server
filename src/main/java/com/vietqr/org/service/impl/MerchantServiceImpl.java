package com.vietqr.org.service.impl;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.common.ResponseObjectDTO;
import com.vietqr.org.dto.merchant.MerchantRequestDTO;
import com.vietqr.org.dto.merchant.MerchantResponseDTO;
import com.vietqr.org.entity.MerchantEntity;
import com.vietqr.org.repository.MerchantRepository;
import com.vietqr.org.service.MerchantService;
import com.vietqr.org.utils.DateTimeUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MerchantServiceImpl implements MerchantService {
    private final MerchantRepository merchantRepository;
    private static final Logger logger = Logger.getLogger(MerchantServiceImpl.class);

    public MerchantServiceImpl(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    @Override
    public ResponseMessageDTO insertMerchant(MerchantRequestDTO merchantRequestDTO) {
        ResponseMessageDTO result;
        try {
            String id = UUID.randomUUID().toString();
            MerchantEntity merchantEntity = new MerchantEntity();
            merchantEntity.setId(id);
            String shortName = merchantRequestDTO.getName();
            if (shortName == null || shortName.isEmpty()) {
                shortName = generateShortName(merchantRequestDTO.getFullName());
                merchantEntity.setName(shortName);
            } else if (merchantRepository.existsByName(shortName) == 0){
                merchantEntity.setName(merchantRequestDTO.getName());
            } else {
                return new ResponseMessageDTO("FAILED", "E05");
            }
            merchantEntity.setFullName(merchantRequestDTO.getFullName());
            merchantEntity.setAddress(merchantRequestDTO.getAddress());
            int nationalIdCount = merchantRepository.existsByNationalId(merchantRequestDTO.getNationalId());
            if (nationalIdCount > 0) {
                return new ResponseMessageDTO("FAILED", "E05");
            }
            merchantEntity.setNationalId(merchantRequestDTO.getNationalId());
            merchantEntity.setBusinessSector(merchantRequestDTO.getBusinessSector());
            merchantEntity.setBusinessType(merchantRequestDTO.getBusinessType());
            merchantEntity.setStatus(true);
            LocalDateTime now = LocalDateTime.now();
            long time = now.toEpochSecond(ZoneOffset.UTC);
            merchantEntity.setTimeCreate(time);
            merchantEntity.setPublishId(generateUniquePublishId());
            merchantEntity.setMaster(false);
            merchantEntity.setRefId("");
            merchantRepository.save(merchantEntity);
            result = new ResponseMessageDTO("SUCCESS", "");
        } catch (Exception e) {
            logger.error("insertMerchant ERROR: " + e.getMessage());
            result = new ResponseMessageDTO("FAILED", "E05");
        }
        return result;
    }

    @Override
    public ResponseObjectDTO merchantInfo(String id) {
        ResponseObjectDTO result;
        MerchantResponseDTO merchantResponseDTO = new MerchantResponseDTO();
        try {
            Optional<MerchantEntity> merchantEntityOptional = merchantRepository.findMerchantById(id);
            if (merchantEntityOptional.isPresent()) {
                MerchantEntity merchantEntity = merchantEntityOptional.get();
                merchantResponseDTO.setFullName(merchantEntity.getFullName());
                merchantResponseDTO.setName(merchantEntity.getName());
                merchantResponseDTO.setAddress(merchantEntity.getAddress());
                merchantResponseDTO.setBusinessSector(merchantEntity.getBusinessSector());
                merchantResponseDTO.setBusinessType(merchantEntity.getBusinessType());
                merchantResponseDTO.setServiceType(merchantEntity.getServiceType());
            }
            result = new ResponseObjectDTO("SUCCESS", merchantResponseDTO);
        } catch (Exception e) {
            logger.error("merchantInfo ERROR: " + e.getMessage());
            result = new ResponseObjectDTO("FAILED", null);
        }
        return result;
    }

    @Override
    public ResponseMessageDTO updateMerchant(String id, MerchantRequestDTO merchantRequestDTO) {
        ResponseMessageDTO result;
        try {
            Optional<MerchantEntity> merchantEntityOptional = merchantRepository.findMerchantById(id);
            if (merchantEntityOptional.isPresent()) {
                MerchantEntity merchantEntity = merchantEntityOptional.get();
                merchantRepository.save(updateMerchantFields(merchantEntity, merchantRequestDTO));
            }
            result = new ResponseMessageDTO("SUCCESS", "");
        } catch (Exception e) {
            logger.error("updateMerchant ERROR: " + e.getMessage());
            result = new ResponseMessageDTO("FAILED", "E05");
        }
        return result;
    }

    @Override
    public ResponseMessageDTO deleteMerchant(String id) {
        ResponseMessageDTO result;
        try {
            Optional<MerchantEntity> merchantEntityOptional = merchantRepository.findMerchantById(id);
            if (merchantEntityOptional.isPresent()) {
                MerchantEntity merchantEntity = merchantEntityOptional.get();
                merchantEntity.setStatus(false);
                merchantEntity.setTimeUpdatedStatus(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
                merchantRepository.save(merchantEntity);
            }
            result = new ResponseMessageDTO("SUCCESS", "");
        } catch (Exception e) {
            logger.error("deleteMerchant ERROR" + e.getMessage());
            result = new ResponseMessageDTO("FAILED", "E05");
        }
        return result;
    }

    @Override
    public ResponseObjectDTO getListDeleteMerchant() {
        ResponseObjectDTO result;
        try {
            List<MerchantEntity> merchantEntityList = merchantRepository.findDeletedMerchants(DateTimeUtil.getTimeUTCNMonthsAgo(6));
            List<MerchantResponseDTO> merchantResponseDTOList = merchantEntityList.stream().map(merchantEntity -> {
                MerchantResponseDTO merchantResponseDTO = new MerchantResponseDTO();
                merchantResponseDTO.setName(merchantEntity.getName());
                merchantResponseDTO.setFullName(merchantEntity.getFullName());
                merchantResponseDTO.setAddress(merchantEntity.getAddress());
                merchantResponseDTO.setNationalId(merchantEntity.getNationalId());
                merchantResponseDTO.setBusinessSector(merchantEntity.getBusinessSector());
                merchantResponseDTO.setServiceType(merchantEntity.getServiceType());
                merchantResponseDTO.setBusinessType(merchantEntity.getBusinessType());
                return merchantResponseDTO;
            }).collect(Collectors.toList());
            result = new ResponseObjectDTO("SUCCESS", merchantResponseDTOList);
        } catch (Exception e) {
            logger.error("getListDeleteMerchant ERROR: " + e.getMessage());
            result = new ResponseObjectDTO("FAILED", null);
        }
        return result;
    }

    @Override
    public ResponseMessageDTO recoverMerchant(String id) {
        ResponseMessageDTO result;
        try {
            Optional<MerchantEntity> merchantEntityOptional = merchantRepository.findMerchantById(id);
            if (merchantEntityOptional.isPresent()) {
                MerchantEntity merchantEntity = merchantEntityOptional.get();
                merchantEntity.setTimeUpdatedStatus(0);
                merchantEntity.setStatus(true);
                merchantRepository.save(merchantEntity);
            }
            result = new ResponseMessageDTO("SUCCESS", "");
        } catch (Exception e) {
            logger.error("restoreMerchant ERROR: " + e.getMessage());
            result = new ResponseMessageDTO("FAILED", "E05");
        }
        return result;
    }

    private String generateShortName(String fullName) {
        SecureRandom random = new SecureRandom();
        String shortName;
        int exist;
        do {
            StringBuilder shortNameBuilder = new StringBuilder();
            String[] words = fullName.split(" ");
            for (String word : words) {
                if (!word.isEmpty()) {
                    int index = random.nextInt(word.length());
                    shortNameBuilder.append(word.charAt(index));
                }
            }
            shortName = shortNameBuilder.toString();
            exist = merchantRepository.existsByName(shortName);
        } while (exist == 1);
        return shortName;
    }

    private String generateUniquePublishId() {
        SecureRandom random = new SecureRandom();
        String publishId;
        int exist;
        do {
            StringBuilder stringBuilder = new StringBuilder("MER");
            for (int i = 0; i < 8; i++) {
                stringBuilder.append(random.nextInt(10));
            }
            publishId = stringBuilder.toString();
            exist = merchantRepository.existsByPublishId(publishId);
        } while (exist == 1);
        return publishId;
    }

    private MerchantEntity updateMerchantFields(MerchantEntity merchantEntity, MerchantRequestDTO merchantRequestDTO) {
        if (merchantRequestDTO.getName() != null) {
            merchantEntity.setName(merchantRequestDTO.getName());
        }
        if (merchantRequestDTO.getFullName() != null) {
            merchantEntity.setFullName(merchantRequestDTO.getFullName());
        }
        if (merchantRequestDTO.getAddress() != null) {
            merchantEntity.setAddress(merchantRequestDTO.getAddress());
        }
        if (merchantRequestDTO.getNationalId() != null) {
            merchantEntity.setNationalId(merchantRequestDTO.getNationalId());
        }
        if (merchantRequestDTO.getBusinessSector() != null) {
            merchantEntity.setBusinessSector(merchantRequestDTO.getBusinessSector());
        }
        if (merchantRequestDTO.getBusinessType() != null) {
            merchantEntity.setBusinessType(merchantRequestDTO.getBusinessType());
        }
        return merchantEntity;
    }
}