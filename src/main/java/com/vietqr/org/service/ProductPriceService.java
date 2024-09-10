package com.vietqr.org.service;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.productprice.ProductPriceInsertDTO;
import com.vietqr.org.dto.productprice.ProductPriceUpdateAmountDTO;
import com.vietqr.org.dto.productprice.ProductPriceUpdateData1DTO;
import com.vietqr.org.dto.productprice.ProductPriceUpdateData2DTO;
import org.springframework.stereotype.Service;

@Service
public interface ProductPriceService {
    ResponseMessageDTO insertProductPrice(ProductPriceInsertDTO dto);

    ResponseMessageDTO updateAmountProductPriceById(ProductPriceUpdateAmountDTO dto);

    Object findProductPriceById(String productPriceId);

    Object findProductPriceByProductId(String productId);

    ResponseMessageDTO updateData1ProductPriceById(ProductPriceUpdateData1DTO dto);

    ResponseMessageDTO updateData2ProductPriceById(ProductPriceUpdateData2DTO dto);
}
