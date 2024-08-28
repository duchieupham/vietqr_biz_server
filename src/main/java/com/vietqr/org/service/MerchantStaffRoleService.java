package com.vietqr.org.service;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.merchantstaffrole.MerchantStaffRoleInsertDTO;

public interface MerchantStaffRoleService {
    ResponseMessageDTO insertMerchantStaffRole(MerchantStaffRoleInsertDTO merchantStaffRoleInsertDTO);
}
