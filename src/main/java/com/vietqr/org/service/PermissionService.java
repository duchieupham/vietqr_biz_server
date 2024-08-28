package com.vietqr.org.service;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.permission.PermissionInsertDTO;
import org.springframework.stereotype.Service;

@Service
public interface PermissionService {
    ResponseMessageDTO insertPermission(PermissionInsertDTO dto);
    Object getPermissionById(String id);
}
