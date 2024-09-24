package com.vietqr.org.service;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.productprice.ProductPriceUpdateAmountDTO;
import com.vietqr.org.dto.productprice.ProductPriceDTO;
import org.springframework.stereotype.Service;

@Service
public interface ProductPriceService {
    ResponseMessageDTO insertProductPrice(ProductPriceDTO dto, String token);

    ResponseMessageDTO updateAmountProductPriceById(ProductPriceUpdateAmountDTO dto);

    Object findProductPriceById(String productPriceId);

    Object findProductPriceByProductId(String productId);

    ResponseMessageDTO updateDataProductPriceById(ProductPriceDTO dto, String token);

    ResponseMessageDTO updateDataProductPriceByProductId(ProductPriceDTO dto, String token);
}
