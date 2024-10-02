package com.vietqr.org.service.impl;

import com.vietqr.org.constant.Status;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.common.ResponseObjectDTO;
import com.vietqr.org.dto.terminalorder.ITerminalOrderInfoDTO;
import com.vietqr.org.dto.terminalorder.TerminalOrderInfoDTO;
import com.vietqr.org.dto.terminalorder.TerminalOrderInsertDTO;
import com.vietqr.org.dto.terminalorderitem.ITerminalOrderItemDTO;
import com.vietqr.org.dto.terminalorderitem.TerminalOrderItemInsertDTO;
import com.vietqr.org.entity.TerminalOrderEntity;
import com.vietqr.org.repository.TerminalOrderItemRepository;
import com.vietqr.org.repository.TerminalOrderRepository;
import com.vietqr.org.service.TerminalOrderItemService;
import com.vietqr.org.service.TerminalOrderService;
import com.vietqr.org.utils.DateTimeUtil;
import com.vietqr.org.utils.GeneratorUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TerminalOrderServiceImpl implements TerminalOrderService {
    private static final Logger logger = Logger.getLogger(TerminalOrderServiceImpl.class);
    private final TerminalOrderRepository terminalOrderRepository;

    private final TerminalOrderItemService terminalOrderItemService;

    private final TerminalOrderItemRepository terminalOrderItemRepository;

    public TerminalOrderServiceImpl(TerminalOrderRepository terminalOrderRepository, TerminalOrderItemService terminalOrderItemService, TerminalOrderItemRepository terminalOrderItemRepository) {
        this.terminalOrderRepository = terminalOrderRepository;
        this.terminalOrderItemService = terminalOrderItemService;
        this.terminalOrderItemRepository = terminalOrderItemRepository;
    }

    @Override
    public ResponseMessageDTO insertTerminalOrder(TerminalOrderInsertDTO terminalOrderInsertDTO) {
        ResponseMessageDTO result;
        try {
            String orderId = GeneratorUtil.generateUniqueId(terminalOrderRepository);
            AtomicLong amountDiscount = new AtomicLong(0);
            AtomicLong amountVat = new AtomicLong(0);

            amountDiscount.set(
                    terminalOrderInsertDTO.getTerminalOrderItemList()
                            .stream()
                            .mapToLong(TerminalOrderItemInsertDTO::getDiscount)
                            .sum()
            );

            amountVat.set(
                    terminalOrderInsertDTO.getTerminalOrderItemList()
                            .stream()
                            .mapToLong(TerminalOrderItemInsertDTO::getVat)
                            .sum()
            );
            for (TerminalOrderItemInsertDTO item : terminalOrderInsertDTO.getTerminalOrderItemList()) {
                item.setOrderId(orderId);
                ResponseMessageDTO itemResult = terminalOrderItemService.insertTerminalOrderItem(item);

                // rollback and return error
                if (itemResult.getStatus().equals(Status.FAILED)) {
                    return itemResult;
                }
            }

            TerminalOrderEntity terminalOrderEntity = new TerminalOrderEntity(
                    orderId,
                    DateTimeUtil.getNowUTC(),
                    0,
                    2,
                    terminalOrderInsertDTO.getTotalAmount(),
                    terminalOrderInsertDTO.getVat(),
                    amountVat.get(),
                    amountDiscount.get(),
                    terminalOrderInsertDTO.getCode().trim(),
                    terminalOrderInsertDTO.getStaffId().trim(),
                    terminalOrderInsertDTO.getCustomerId().trim(),
                    terminalOrderInsertDTO.getTid().trim()
            );

            result = new ResponseMessageDTO(Status.SUCCESS, "");


            terminalOrderRepository.save(terminalOrderEntity);
        } catch (Exception e) {
            logger.error("insertTerminalOder ERROR: " + e.getMessage() + " at " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }
        return result;
    }

    @Override
    public Object findTerminalOrderInfoById(String toId) {
        Object result;
        try {
            Optional<ITerminalOrderInfoDTO> info = terminalOrderRepository.findTerminalOderInfoById(toId);
            List<ITerminalOrderItemDTO> items = terminalOrderItemRepository.findTerminalOrderItemByOrderId(toId);
            if (info.isPresent()) {
                TerminalOrderInfoDTO data = new TerminalOrderInfoDTO(
                        info.get().getTid(),
                        info.get().getCustomerId(),
                        info.get().getStaffId(),
                        info.get().getTotalAmount(),
                        info.get().getVatAmount(),
                        info.get().getTimeCreated(),
                        info.get().getTimePaid(),
                        info.get().getStatus(),
                        items
                );
                result = new ResponseObjectDTO(Status.SUCCESS, data);
            } else {
                result = new ResponseMessageDTO(Status.FAILED, "");
            }
        } catch (Exception e) {
            logger.error("findTerminalOrderInfoById ERROR: " + e.getMessage() + " at " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }
        return result;
    }

    @Override
    public ResponseMessageDTO updateTerminalOrderStatusById(String toId, int status) {
        ResponseMessageDTO result;
        try {
            switch (status) {
                case 0:
                case 3:
                    terminalOrderRepository.updateTerminalOderStatusById(toId, status);
                    result = new ResponseMessageDTO(Status.SUCCESS, "");
                    break;
                case 1:
                    terminalOrderRepository.updateTerminalOderPaidById(toId, DateTimeUtil.getNowUTC());
                    result = new ResponseMessageDTO(Status.SUCCESS, "");
                    break;
                default:
                    result = new ResponseMessageDTO(Status.FAILED, "");
            }

        } catch (Exception e) {
            logger.error("updateTerminalOrderStatusById ERROR: " + e.getMessage() + " at " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }
        return result;
    }
}
