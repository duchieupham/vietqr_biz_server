package com.vietqr.org.service;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.merchantstaff.MerchantStaffImportDTO;
import com.vietqr.org.dto.merchantstaff.MerchantStaffInsertDTO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@Service
public interface MerchantStaffService {
    ResponseMessageDTO insertMerchantStaffByForm(MerchantStaffInsertDTO dto);
    Object importMerchantStaff(InputStream is, MerchantStaffImportDTO dto);
    Object getExampleMerchantStaffExcel(HttpServletResponse httpServletResponse);
}
