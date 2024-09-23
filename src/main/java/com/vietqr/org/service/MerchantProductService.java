package com.vietqr.org.service;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.merchantproduct.MerchantProductDTO;
import com.vietqr.org.dto.productprice.ProductPriceInsertProductDTO;
import org.springframework.web.multipart.MultipartFile;

public interface MerchantProductService {
    ResponseMessageDTO saveMerchantProduct(MultipartFile file, MerchantProductDTO productInsertDTO, ProductPriceInsertProductDTO productPriceInsertProductDTO, String token);

    ResponseMessageDTO updateMerchantProduct(String id, MerchantProductDTO productInsertDTO, MultipartFile file);

    Object getListMerchantProduct();

    Object getMerchantProductById(String id);

    ResponseMessageDTO deleteMerchantProduct(String id);
}
