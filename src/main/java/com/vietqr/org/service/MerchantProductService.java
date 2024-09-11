package com.vietqr.org.service;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.merchantproduct.MerchantProductDTO;
import org.springframework.web.multipart.MultipartFile;

public interface MerchantProductService {
    ResponseMessageDTO saveMerchantProduct(MultipartFile file, MerchantProductDTO productInsertDTO);
    ResponseMessageDTO updateMerchantProduct(String id, MerchantProductDTO productInsertDTO);
    Object getListMerchantProduct();
    Object getMerchantProductById(String id);
    ResponseMessageDTO deleteMerchantProduct(String id);
}
