package com.vietqr.org.service.impl;

import com.vietqr.org.constant.Status;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.common.ResponseObjectDTO;
import com.vietqr.org.dto.permission.PermissionInsertDTO;
import com.vietqr.org.entity.PermissionEntity;
import com.vietqr.org.repository.PermissionRepository;
import com.vietqr.org.service.PermissionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PermissionServiceImpl implements PermissionService {
    private static final Logger logger = Logger.getLogger(PermissionServiceImpl.class);

    @Autowired
    private PermissionRepository repo;

    @Override
    public ResponseMessageDTO insertPermission(PermissionInsertDTO dto) {
        ResponseMessageDTO result = null;
        try {
            PermissionEntity entity = new PermissionEntity(dto.getName().trim(), dto.getDescription().trim(), dto.getCategory(), dto.getColor().trim());
            do {
                UUID uuid = UUID.randomUUID();
                if(!repo.existsById(uuid.toString())){
                    entity.setId(uuid.toString());
                }
            }while (entity.getId().isEmpty());
            repo.save(entity);

            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("insertPermission: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public Object getPermissionById(String id) {
        Object result = null;
        try {
            PermissionEntity entity = repo.findPermissionById(id.trim());
            if (entity != null) {
                result = new ResponseObjectDTO(Status.SUCCESS, entity);
            } else {
                result = new ResponseMessageDTO(Status.FAILED, "E192");
                logger.error("getPermissionById: The permission not found at: " + System.currentTimeMillis());
            }
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("getPermissionById: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

}
