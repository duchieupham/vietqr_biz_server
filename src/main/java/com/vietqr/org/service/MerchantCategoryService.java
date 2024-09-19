package com.vietqr.org.service;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.merchantcategory.MerchantCategoryDTO;

public interface MerchantCategoryService {
    ResponseMessageDTO insertMerchantCategory(MerchantCategoryDTO categoryDTO);
    ResponseMessageDTO updateMerchantCategory(String id, MerchantCategoryDTO categoryDTO);
    Object getMerchantCategoryByMid(String mid);
    Object getMerchantCategoryById(String id);
    ResponseMessageDTO deleteMerchantCategory(String id);
    ResponseMessageDTO deleteMerchantCategoryByMid(String mid);
}
