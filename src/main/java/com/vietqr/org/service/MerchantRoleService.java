package com.vietqr.org.service;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.merchantrole.MerchantRoleInsertDTO;
import org.springframework.stereotype.Service;

@Service
public interface MerchantRoleService {
    ResponseMessageDTO insertMerchantRole(MerchantRoleInsertDTO dto);
    Object getMerchantRoleById(String id);
}
