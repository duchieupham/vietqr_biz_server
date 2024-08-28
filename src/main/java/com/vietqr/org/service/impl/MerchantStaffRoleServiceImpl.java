package com.vietqr.org.service.impl;

import com.vietqr.org.constant.Status;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.merchantstaffrole.MerchantStaffRoleInsertDTO;
import com.vietqr.org.entity.MerchantRoleEntity;
import com.vietqr.org.entity.MerchantStaffRoleEntity;
import com.vietqr.org.repository.MerchantRoleRepository;
import com.vietqr.org.repository.MerchantStaffRoleRepository;
import com.vietqr.org.service.MerchantStaffRoleService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MerchantStaffRoleServiceImpl implements MerchantStaffRoleService {

    private static final Logger logger = Logger.getLogger(MerchantStaffRoleServiceImpl.class);
    private final MerchantStaffRoleRepository merchantStaffRoleRepository;
    private final MerchantRoleRepository merchantRoleRepository;

    public MerchantStaffRoleServiceImpl(MerchantStaffRoleRepository merchantStaffRoleRepository, MerchantRoleRepository merchantRoleRepository) {
        this.merchantStaffRoleRepository = merchantStaffRoleRepository;
        this.merchantRoleRepository = merchantRoleRepository;
    }

    @Override
    public ResponseMessageDTO insertMerchantStaffRole(MerchantStaffRoleInsertDTO merchantStaffRoleInsertDTO) {
        ResponseMessageDTO result;
        try {
            MerchantStaffRoleEntity merchantStaffRole = new MerchantStaffRoleEntity();
            merchantStaffRole.setId(UUID.randomUUID().toString());
            merchantStaffRole.setMid(merchantStaffRoleInsertDTO.getMid());
            merchantStaffRole.setTid(merchantStaffRoleInsertDTO.getTid());
            merchantStaffRole.setStaffRoleName(merchantStaffRoleInsertDTO.getStaffRoleName());
            merchantStaffRole.setDefault(true);
            merchantStaffRole.setMerchantRoleId(merchantStaffRoleInsertDTO.getMerchantRoleId());
            MerchantRoleEntity merchantRoleEntity = merchantRoleRepository.findMerchantRoleById(merchantStaffRoleInsertDTO.getMerchantRoleId());
            merchantStaffRole.setPermissionGroupId(merchantRoleEntity.getPermissionGroupId());
            merchantStaffRole.setPermissionGroupId(merchantStaffRoleInsertDTO.getPermissionGroupId());
            merchantStaffRoleRepository.save(merchantStaffRole);
            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            logger.error("insertMerchantStaffRole ERROR: " + e.getMessage() + " at " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }
        return result;
    }
}
