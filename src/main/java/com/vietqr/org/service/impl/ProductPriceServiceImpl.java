package com.vietqr.org.service.impl;

import com.vietqr.org.constant.Status;
import com.vietqr.org.constant.UniqueError;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.common.ResponseObjectDTO;
import com.vietqr.org.dto.productprice.*;
import com.vietqr.org.entity.ProductPriceEntity;
import com.vietqr.org.repository.ProductPriceRepository;
import com.vietqr.org.service.ProductPriceService;
import com.vietqr.org.utils.DateTimeUtil;
import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {
    private static final Logger logger = Logger.getLogger(ProductPriceServiceImpl.class);
    private final String LOG_ERROR = "Failed at ProductPriceServiceImpl: ";
    private final ProductPriceRepository repo;

    public ProductPriceServiceImpl(ProductPriceRepository repo) {
        this.repo = repo;
    }

    @Override
    public ResponseMessageDTO insertProductPrice(ProductPriceInsertDTO dto) {
        ResponseMessageDTO result = null;
        try {
            ProductPriceEntity entity = new ProductPriceEntity(
                    dto.getData1(),
                    dto.getData2(),
                    dto.getTraceTransfer(),
                    dto.getAmount(),
                    dto.getProductId()
            );
            do {
                UUID uuid = UUID.randomUUID();
                if (repo.existsProductPriceById(uuid.toString()) != 1) {
                    entity.setId(uuid.toString());
                }
            } while (entity.getId().isEmpty());
            entity.setTimeCreated(DateTimeUtil.getNowUTC());
            entity.setTimeUpdated(DateTimeUtil.getNowUTC());
            repo.save(entity);
            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (DataIntegrityViolationException e) {
            String message = e.getMessage() == null ? "" : e.getMessage();
            if (message.contains(UniqueError.PRODUCTPRICE_PRODUCTID)) {
                result = new ResponseMessageDTO(Status.FAILED, "E208");
                logger.error(LOG_ERROR + "insertProductPrice: " + message + " at: " + System.currentTimeMillis());
            } else {
                result = new ResponseMessageDTO(Status.FAILED, "E05");
                logger.error(LOG_ERROR + "insertProductPrice: " + message + " at: " + System.currentTimeMillis());
            }
        } catch (Exception e) {
            logger.error(LOG_ERROR + "insertProductPrice: " + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }

        return result;
    }

    @Override
    public ResponseMessageDTO updateAmountProductPriceById(ProductPriceUpdateAmountDTO dto) {
        ResponseMessageDTO result = null;
        try {
            repo.updateAmountProductPriceById(dto.getId(), dto.getAmount(), DateTimeUtil.getNowUTC());
            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            logger.error(LOG_ERROR + "updateAmountProductPrice: " + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }

        return result;
    }

    @Override
    public Object findProductPriceById(String productPriceId) {
        Object result = null;
        try {
            IProductPriceDTO entity = repo.findProductPriceById(productPriceId);
            result = new ResponseObjectDTO(Status.SUCCESS, entity);
        } catch (Exception e) {
            logger.error(LOG_ERROR + "findProductPriceById: " + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }

        return result;
    }

    @Override
    public Object findProductPriceByProductId(String productId) {
        Object result = null;
        try {
            IProductPriceDTO entity = repo.findProductPriceByProductId(productId);
            result = new ResponseObjectDTO(Status.SUCCESS, entity);
        } catch (Exception e) {
            logger.error(LOG_ERROR + "findProductPriceByProductId: " + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }

        return result;
    }

    @Override
    public ResponseMessageDTO updateData1ProductPriceById(ProductPriceUpdateData1DTO dto) {
        ResponseMessageDTO result = null;
        try {
            repo.updateData1ProductPriceById(dto.getId(), dto.getData1(), DateTimeUtil.getNowUTC());
            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            logger.error(LOG_ERROR + "updateData1ProductPriceById: " + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }

        return result;
    }

    @Override
    public ResponseMessageDTO updateData2ProductPriceById(ProductPriceUpdateData2DTO dto) {
        ResponseMessageDTO result = null;
        try {
            repo.updateData2ProductPriceById(dto.getId(), dto.getData2(), DateTimeUtil.getNowUTC());
            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            logger.error(LOG_ERROR + "updateData2ProductPriceById: " + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }

        return result;
    }
}
