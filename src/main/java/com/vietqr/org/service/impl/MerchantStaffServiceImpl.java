package com.vietqr.org.service.impl;

import com.vietqr.org.constant.Status;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.merchantstaff.MerchantStaffInsertDTO;
import com.vietqr.org.entity.MerchantStaffEntity;
import com.vietqr.org.repository.MerchantStaffRepository;
import com.vietqr.org.service.MerchantStaffService;
import com.vietqr.org.utils.DateTimeUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MerchantStaffServiceImpl implements MerchantStaffService {
    private static final Logger logger = Logger.getLogger(MerchantStaffServiceImpl.class);

    @Autowired
    private MerchantStaffRepository repo;

    @Override
    public ResponseMessageDTO insertMerchantStaffByForm(MerchantStaffInsertDTO dto) {
        ResponseMessageDTO result = null;
        try {
            MerchantStaffEntity entity = new MerchantStaffEntity(dto.getMid().trim(), dto.getTid().trim(), dto.getUserId().trim(), dto.getData().trim(), dto.getPermissionGroupId().trim(), dto.getMerchantStaffRoleId().trim(), dto.getStaffRoleName().trim());
            do {
                UUID uuid = UUID.randomUUID();
                if (!isMerchantStaffIdExists(uuid.toString())) {
                    entity.setId(uuid.toString());
                }
            } while (entity.getId().isEmpty());
            entity.setStatus(false);
            entity.setTimeCreated(DateTimeUtil.getNowUTC());
            entity.setTimeUpdated(DateTimeUtil.getNowUTC());
            repo.save(entity);

            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("insertMerchantStaff: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    private boolean isMerchantStaffIdExists(String id) {
        return repo.countMerchantStaffById(id) != 0;
    }
}
