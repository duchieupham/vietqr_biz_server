package com.vietqr.org.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vietqr.org.constant.Status;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.common.ResponseObjectDTO;
import com.vietqr.org.dto.merchantrole.MerchantRoleInsertDTO;
import com.vietqr.org.entity.MerchantRoleEntity;
import com.vietqr.org.repository.MerchantRoleRepository;
import com.vietqr.org.service.MerchantRoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MerchantRoleServiceImpl implements MerchantRoleService {
    private static final Logger logger = Logger.getLogger(MerchantRoleServiceImpl.class);

    @Autowired
    private MerchantRoleRepository repo;

    @Override
    public ResponseMessageDTO insertMerchantRole(MerchantRoleInsertDTO dto) {
        ResponseMessageDTO result = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String permissionGroupId = mapper.writeValueAsString(dto.getListPermissionId());
            MerchantRoleEntity entity = new MerchantRoleEntity(dto.getName().trim(), dto.getDescription().trim(), permissionGroupId);
            do {
                UUID uuid = UUID.randomUUID();
                if (repo.existsById(uuid.toString())) {
                    entity.setId(uuid.toString());
                }
            } while (entity.getId().isEmpty());
            repo.save(entity);
            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("insertMerchantRole: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public Object getMerchantRoleById(String id) {
        Object result = null;
        try {
            MerchantRoleEntity entity = repo.findMerchantRoleById(id.trim());
            if (entity != null) {
                result = new ResponseObjectDTO(Status.SUCCESS, entity);
            } else {
                result = new ResponseMessageDTO(Status.FAILED, "E193");
                logger.error("getMerchantRoleById: The merchant role not found at: " + System.currentTimeMillis());
            }
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("getMerchantRoleById: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }
}
