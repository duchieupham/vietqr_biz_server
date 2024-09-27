package com.vietqr.org.service;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.merchant.MerchantRequestDTO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

public interface MerchantService {
    ResponseMessageDTO insertMerchant(MerchantRequestDTO merchantRequestDTO);
    Object merchantInfo(String id);
    ResponseMessageDTO updateMerchant(String id, MerchantRequestDTO merchantRequestDTO);
    ResponseMessageDTO deleteMerchant(String id);
    Object getListDeleteMerchant();
    ResponseMessageDTO recoverMerchant(String id);
    ResponseMessageDTO exportMerchantToExcel(HttpServletResponse httpServletResponse, String id);
    ResponseMessageDTO merchantDataTransfer(String oldMid, String newMid);
    ResponseMessageDTO importMerchantFromExcel(MultipartFile file);
}