package com.vietqr.org.service;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.merchantstaff.MerchantStaffInsertDTO;
import org.springframework.stereotype.Service;

@Service
public interface MerchantStaffService {
    ResponseMessageDTO insertMerchantStaffByForm(MerchantStaffInsertDTO dto);
}
