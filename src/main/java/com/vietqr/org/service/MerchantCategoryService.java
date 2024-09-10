package com.vietqr.org.service;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.merchantcategory.InsertMerchantCategoryDTO;

public interface MerchantCategoryService {
    ResponseMessageDTO saveMerchantCategory(InsertMerchantCategoryDTO categoryDTO);
    ResponseMessageDTO updateMerchantCategory(String id, InsertMerchantCategoryDTO categoryDTO);
    Object getListMerchantCategory();
    Object getMerchantCategoryById(String id);
    ResponseMessageDTO deleteMerchantCategory(String id);
}
