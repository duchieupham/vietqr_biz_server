package com.vietqr.org.service;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.merchant.MerchantRequestDTO;

public interface MerchantService {
    ResponseMessageDTO insertMerchant(MerchantRequestDTO merchantRequestDTO);
    Object merchantInfo(String id);
    ResponseMessageDTO updateMerchant(String id, MerchantRequestDTO merchantRequestDTO);
    ResponseMessageDTO deleteMerchant(String id);
    Object getListDeleteMerchant();
    ResponseMessageDTO recoverMerchant(String id);
    ResponseMessageDTO exportMerchantToExcel(String id);
}