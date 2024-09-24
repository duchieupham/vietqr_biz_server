package com.vietqr.org.service.impl;

import com.vietqr.org.constant.Status;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.common.ResponseObjectDTO;
import com.vietqr.org.dto.productpricehistory.IProductPriceHistoryDTO;
import com.vietqr.org.dto.productpricehistory.IProductPriceHistoryStatisticDTO;
import com.vietqr.org.dto.productpricehistory.ProductPriceHistoryDTO;
import com.vietqr.org.entity.ProductPriceHistoryEntity;
import com.vietqr.org.repository.ProductPriceHistoryRepository;
import com.vietqr.org.service.ProductPriceHistoryService;
import com.vietqr.org.service.ProductPriceService;
import com.vietqr.org.utils.DateTimeUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductPriceHistoryServiceImpl implements ProductPriceHistoryService {
    private static final Logger logger = Logger.getLogger(ProductPriceHistoryServiceImpl.class);
    private final String LOG_ERROR = "Failed at ProductPriceHistoryServiceImpl: ";
    private final ProductPriceHistoryRepository repo;

    public ProductPriceHistoryServiceImpl(ProductPriceHistoryRepository repo) {
        this.repo = repo;
    }

    @Override
    public ResponseMessageDTO insertProductPriceHistory(ProductPriceHistoryDTO dto) {
        ResponseMessageDTO result = null;
        try {
            String id = generateUniqueId();
            ProductPriceHistoryEntity entity = new ProductPriceHistoryEntity(id, dto.getProductId(), dto.getCreatedAt(), dto.getAmount());
            repo.save(entity);
            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            logger.error(LOG_ERROR + "insertProductPriceHistory: " + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }

        return result;
    }

    @Override
    public Object statisticProductPriceHistory(String productId) {
        Object result = null;
        try {
            List<IProductPriceHistoryStatisticDTO> statisticalList = repo.statisticProductPriceHistory(productId.trim());
            if (statisticalList != null) {
                result = new ResponseObjectDTO(Status.SUCCESS, statisticalList);
            } else {
                logger.error(LOG_ERROR + "statisticProductPriceHistory: Product not found at: " + System.currentTimeMillis());
                result = new ResponseMessageDTO(Status.FAILED, "E213");
            }
        } catch (Exception e) {
            logger.error(LOG_ERROR + "statisticProductPriceHistory: " + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }

        return result;
    }

    @Override
    public Object findProductPriceHistoryById(String pphId) {
        Object result = null;
        try {
            Optional<IProductPriceHistoryDTO> dto = repo.findProductPriceHistoryById(pphId.trim());
            if (dto.isPresent()) {
                result = new ResponseObjectDTO(Status.SUCCESS, dto);
            } else {
                logger.error(LOG_ERROR + "getProductPriceHistoryById: Not found at: " + System.currentTimeMillis());
                result = new ResponseMessageDTO(Status.FAILED, "E214");
            }
        } catch (Exception e) {
            logger.error(LOG_ERROR + "getProductPriceHistoryById: " + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }

        return result;
    }

    private String generateUniqueId() {
        String uuid;
        do {
            uuid = UUID.randomUUID().toString();
        } while (repo.existsById(uuid));

        return uuid;
    }
}
