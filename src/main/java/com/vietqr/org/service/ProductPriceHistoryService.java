package com.vietqr.org.service;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.productpricehistory.ProductPriceHistoryDTO;
import org.springframework.stereotype.Service;

@Service
public interface ProductPriceHistoryService {
    ResponseMessageDTO insertProductPriceHistory(ProductPriceHistoryDTO dto);

    Object statisticProductPriceHistory(String productId);

    Object findProductPriceHistoryById(String pphId);
}
