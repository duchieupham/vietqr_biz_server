package com.vietqr.org.service.impl;

import com.vietqr.org.constant.Status;
import com.vietqr.org.constant.UniqueError;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.common.ResponseObjectDTO;
import com.vietqr.org.dto.productprice.*;
import com.vietqr.org.dto.productpricehistory.ProductPriceHistoryDTO;
import com.vietqr.org.entity.ProductPriceEntity;
import com.vietqr.org.grpc.client.qrgenerator.QRGeneratorClient;
import com.vietqr.org.grpc.client.qrgenerator.RequestSemiDynamicQRDTO;
import com.vietqr.org.grpc.client.qrgenerator.SemiDynamicQRDTO;
import com.vietqr.org.repository.ProductPriceRepository;
import com.vietqr.org.service.ProductPriceHistoryService;
import com.vietqr.org.service.ProductPriceService;
import com.vietqr.org.utils.DateTimeUtil;
import com.vietqr.org.utils.GeneratorUtil;
import com.vietqr.org.utils.MmsUtil;
import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {
    private static final Logger logger = Logger.getLogger(ProductPriceServiceImpl.class);
    private final String LOG_ERROR = "Failed at ProductPriceServiceImpl: ";
    private final ProductPriceRepository repo;
    private final QRGeneratorClient qrGeneratorClient;
    private final ProductPriceHistoryService productPriceHistoryService;

    public ProductPriceServiceImpl(ProductPriceRepository repo, QRGeneratorClient qrGeneratorClient, ProductPriceHistoryService productPriceHistoryService) {
        this.repo = repo;
        this.qrGeneratorClient = qrGeneratorClient;
        this.productPriceHistoryService = productPriceHistoryService;
    }

    @Override
    public ResponseMessageDTO insertProductPrice(ProductPriceDTO dto, String token) {
        ResponseMessageDTO result = null;
        try {
            ProductPriceEntity entity = new ProductPriceEntity(
                    dto.getAmount(),
                    // product_id
                    dto.getId()
            );
            entity.setId(GeneratorUtil.generateUniqueId(repo));
            SemiDynamicQRDTO semiDynamicQR = qrGeneratorClient.generateSemiDynamicQR(new RequestSemiDynamicQRDTO(
                    dto.getAmount(),
                    dto.getContent(),
                    dto.getBankAccount(),
                    dto.getBankCode(),
                    dto.getTransType(),
                    dto.getServiceCode(),
                    token
            ));
            if (MmsUtil.isMmsActive(semiDynamicQR.getContent())) {
                entity.setData1("");
                entity.setData2(semiDynamicQR.getQrCode());
            } else {
                entity.setData1(semiDynamicQR.getQrCode());
                entity.setData2("");
            }
            entity.setTraceTransfer(semiDynamicQR.getTraceTransfer());
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
        /*
         * TODO: handle bank account to change qr
         *  */
        ResponseMessageDTO result = null;
        try {
            Optional<IProductPriceDTO> entity = repo.findProductPriceById(dto.getId());
            if (entity.isPresent()) {
                if (entity.get().getAmount() != dto.getAmount()) {
                    repo.updateAmountProductPriceById(dto.getId(), dto.getAmount(), DateTimeUtil.getNowUTC());
                    Thread thread = new Thread(() ->
                            productPriceHistoryService.insertProductPriceHistory(
                                    new ProductPriceHistoryDTO(
                                            entity.get().getProductId(),
                                            entity.get().getAmount(),
                                            entity.get().getTimeUpdated()
                                    )
                            )
                    );
                    thread.start();
                    result = new ResponseMessageDTO(Status.SUCCESS, "");
                } else {
                    logger.error(LOG_ERROR + "findProductPriceById: Amount not change at: " + System.currentTimeMillis());
                    result = new ResponseMessageDTO(Status.FAILED, "E212");
                }
            } else {
                logger.error(LOG_ERROR + "findProductPriceById: Not found at: " + System.currentTimeMillis());
                result = new ResponseMessageDTO(Status.FAILED, "E211");
            }
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
            Optional<IProductPriceDTO> entity = repo.findProductPriceById(productPriceId);
            if (entity.isPresent()) {
                result = new ResponseObjectDTO(Status.SUCCESS, entity.get());
            } else {
                logger.error(LOG_ERROR + "findProductPriceById: Not found at: " + System.currentTimeMillis());
                result = new ResponseMessageDTO(Status.FAILED, "E211");
            }
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
            Optional<IProductPriceDTO> entity = repo.findProductPriceByProductId(productId);
            if (entity.isPresent()) {
                result = new ResponseObjectDTO(Status.SUCCESS, entity.get());
            } else {
                logger.error(LOG_ERROR + "findProductPriceByProductId: Not found at: " + System.currentTimeMillis());
                result = new ResponseMessageDTO(Status.FAILED, "E211");
            }
        } catch (Exception e) {
            logger.error(LOG_ERROR + "findProductPriceByProductId: " + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }

        return result;
    }

    @Override
    public ResponseMessageDTO updateDataProductPriceById(ProductPriceDTO dto, String token) {
        /*
         * TODO: handle bank account to compare
         *  */
        ResponseMessageDTO result = null;
        try {
            SemiDynamicQRDTO semiDynamicQR = qrGeneratorClient.generateSemiDynamicQR(new RequestSemiDynamicQRDTO(
                    dto.getAmount(),
                    dto.getContent(),
                    dto.getBankAccount(),
                    dto.getBankCode(),
                    dto.getTransType(),
                    dto.getServiceCode(),
                    token
            ));

            if (MmsUtil.isMmsActive(semiDynamicQR.getContent())) {
                repo.updateData2ProductPriceById(dto.getId(), semiDynamicQR.getQrCode(), semiDynamicQR.getTraceTransfer(), DateTimeUtil.getNowUTC());
            } else {
                repo.updateData1ProductPriceById(dto.getId(), semiDynamicQR.getQrCode(), semiDynamicQR.getTraceTransfer(), DateTimeUtil.getNowUTC());
            }

            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            logger.error(LOG_ERROR + "updateDataProductPriceById: " + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }

        return result;
    }

    @Override
    public ResponseMessageDTO updateDataProductPriceByProductId(ProductPriceDTO dto, String token) {
        /*
         * TODO: handle bank account to compare
         *  */
        ResponseMessageDTO result = null;
        try {
            SemiDynamicQRDTO semiDynamicQR = qrGeneratorClient.generateSemiDynamicQR(new RequestSemiDynamicQRDTO(
                    dto.getAmount(),
                    dto.getContent(),
                    dto.getBankAccount(),
                    dto.getBankCode(),
                    dto.getTransType(),
                    dto.getServiceCode(),
                    token
            ));

            if (MmsUtil.isMmsActive(semiDynamicQR.getContent())) {
                repo.updateData2ProductPriceByProductId(dto.getId(), semiDynamicQR.getQrCode(), semiDynamicQR.getTraceTransfer(), DateTimeUtil.getNowUTC());
            } else {
                repo.updateData1ProductPriceByProductId(dto.getId(), semiDynamicQR.getQrCode(), semiDynamicQR.getTraceTransfer(), DateTimeUtil.getNowUTC());
            }

            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            logger.error(LOG_ERROR + "updateDataProductPriceById: " + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }

        return result;
    }
}
