package com.vietqr.org.service.impl;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.common.ResponseObjectDTO;
import com.vietqr.org.dto.merchant.MerchantRequestDTO;
import com.vietqr.org.dto.merchant.MerchantResponseDTO;
import com.vietqr.org.entity.MerchantEntity;
import com.vietqr.org.repository.MerchantRepository;
import com.vietqr.org.service.MerchantService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.UUID;

@Service
public class MerchantServiceImpl implements MerchantService {
    private final MerchantRepository merchantRepository;

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
            } else {
                merchantEntity.setName(merchantRequestDTO.getName());
            }
            if (merchantRepository.existsByName(shortName)) {
                return new ResponseMessageDTO("FAILED", "E05");
            }
            merchantEntity.setFullName(merchantRequestDTO.getFullName());
            merchantEntity.setAddress(merchantRequestDTO.getAddress());
            merchantEntity.setNationalId(merchantRequestDTO.getNationalId());
            merchantEntity.setBusinessSector(merchantRequestDTO.getBusinessSector());
            merchantEntity.setBusinessType(merchantRequestDTO.getBusinessType());
            merchantEntity.setStatus(true);
            LocalDateTime now = LocalDateTime.now();
            long time = now.toEpochSecond(ZoneOffset.UTC);
            merchantEntity.setTimeCreate(time);
            merchantEntity.setPublishId(UUID.randomUUID().toString());
            merchantEntity.setMaster(false);
            merchantEntity.setRefId("");
            merchantRepository.save(merchantEntity);
            result = new ResponseMessageDTO("SUCCESS", "");
        } catch (Exception e) {
            System.out.println("Insert Merchant ERROR: " + e.getMessage());
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
            System.out.println("Merchant info ERROR: " + e.getMessage());
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
            System.out.println("Update merchant ERROR: " + e.getMessage());
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
                merchantRepository.save(merchantEntity);
            }
            result = new ResponseMessageDTO("SUCCESS", "");
        } catch (Exception e) {
            System.out.println("Delete merchant ERROR" + e.getMessage());
            result = new ResponseMessageDTO("FAILED", "E05");
        }
        return result;
    }

    private String generateShortName(String fullName) {
        SecureRandom random = new SecureRandom();
        String[] words = fullName.split(" ");
        StringBuilder shortName = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                int index = random.nextInt(words.length);
                shortName.append(word.charAt(index));
            }
        }
        return shortName.toString();
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