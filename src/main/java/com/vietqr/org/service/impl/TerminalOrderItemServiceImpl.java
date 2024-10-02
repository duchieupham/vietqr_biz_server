package com.vietqr.org.service.impl;

import com.vietqr.org.constant.Status;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.common.ResponseObjectDTO;
import com.vietqr.org.dto.productprice.IProductPriceDTO;
import com.vietqr.org.dto.terminalorderitem.ITerminalOrderItemDTO;
import com.vietqr.org.dto.terminalorderitem.ITerminalOrderItemIdDTO;
import com.vietqr.org.dto.terminalorderitem.TerminalOrderItemInsertDTO;
import com.vietqr.org.entity.TerminalOrderItemEntity;
import com.vietqr.org.repository.ProductPriceRepository;
import com.vietqr.org.repository.SystemDefaultRepository;
import com.vietqr.org.repository.TerminalOrderItemRepository;
import com.vietqr.org.service.TerminalOrderItemService;
import com.vietqr.org.utils.GeneratorUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TerminalOrderItemServiceImpl implements TerminalOrderItemService {
    private static final Logger logger = Logger.getLogger(TerminalOrderItemServiceImpl.class);
    private final String LOG_ERROR = "Failed at TerminalOrderItemServiceImpl: ";

    private final TerminalOrderItemRepository repo;

    private final SystemDefaultRepository systemDefaultRepository;

    private final ProductPriceRepository productPriceRepository;

    public TerminalOrderItemServiceImpl(TerminalOrderItemRepository repo, SystemDefaultRepository systemDefaultRepository, ProductPriceRepository productPriceRepository) {
        this.repo = repo;
        this.systemDefaultRepository = systemDefaultRepository;
        this.productPriceRepository = productPriceRepository;
    }

    @Override
    public ResponseMessageDTO insertTerminalOrderItem(TerminalOrderItemInsertDTO dto) {
        ResponseMessageDTO result = null;
        try {
            Optional<IProductPriceDTO> optional = productPriceRepository.findProductPriceByProductId(dto.getProductId());
            double systemVat = systemDefaultRepository.getSystemVat();
            if (optional.isPresent()) {
                TerminalOrderItemEntity entity = new TerminalOrderItemEntity(
                        GeneratorUtil.generateUniqueId(repo),
                        dto.getOrderId(),
                        dto.getProductId(),
                        dto.getQuantity(),
                        systemVat,
                        dto.getDiscount(),
                        optional.get().getAmount()
                );
                long vat = Math.round(systemVat * entity.getQuantity() * entity.getAmount());
                long total = entity.getAmount() * entity.getQuantity() + vat - entity.getDiscountAmount();

                entity.setVatAmount(vat);
                entity.setTotalAmount(total);

                repo.save(entity);
                result = new ResponseMessageDTO(Status.SUCCESS, "");
            } else {
                logger.error(LOG_ERROR + "insertTerminalOrderItem: at: " + System.currentTimeMillis());
                result = new ResponseMessageDTO(Status.FAILED, "E05");
            }
        } catch (Exception e) {
            logger.error(LOG_ERROR + "insertTerminalOrderItem: " + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }

        return result;
    }

    @Override
    public Object findTerminalOrderItemById(String toiId) {
        Object result = null;
        try {
            Optional<ITerminalOrderItemIdDTO> optional = repo.findTerminalOrderItemById(toiId);
            if (optional.isPresent()) {
                result = new ResponseObjectDTO(Status.SUCCESS, optional.get());
            } else {
                logger.error(LOG_ERROR + "findTerminalOrderItemById: at: " + System.currentTimeMillis());
                result = new ResponseMessageDTO(Status.FAILED, "E05");
            }
        } catch (Exception e) {
            logger.error(LOG_ERROR + "findTerminalOrderItemById: " + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }

        return result;
    }

    @Override
    public Object findTerminalOrderItemByOrderId(String toId) {
        Object result = null;
        try {
            List<ITerminalOrderItemDTO> list = repo.findTerminalOrderItemByOrderId(toId);
            result = new ResponseObjectDTO(Status.SUCCESS, list);
        } catch (Exception e) {
            logger.error(LOG_ERROR + "findTerminalOrderItemById: " + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }

        return result;
    }
}
