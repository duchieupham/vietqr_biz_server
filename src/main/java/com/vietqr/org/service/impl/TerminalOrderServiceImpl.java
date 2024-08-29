package com.vietqr.org.service.impl;

import com.vietqr.org.constant.Status;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.terminalorder.TerminalOrderInsertDTO;
import com.vietqr.org.entity.TerminalOrderEntity;
import com.vietqr.org.repository.TerminalOrderRepository;
import com.vietqr.org.service.TerminalOrderService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
public class TerminalOrderServiceImpl implements TerminalOrderService {
    private static final Logger logger = Logger.getLogger(TerminalOrderServiceImpl.class);
    private final TerminalOrderRepository terminalOrderRepository;

    public TerminalOrderServiceImpl(TerminalOrderRepository terminalOrderRepository) {
        this.terminalOrderRepository = terminalOrderRepository;
    }

    @Override
    public ResponseMessageDTO insertTerminalOder(TerminalOrderInsertDTO terminalOrderInsertDTO) {
        ResponseMessageDTO result;
        try {
            TerminalOrderEntity terminalOrderEntity = new TerminalOrderEntity();
            terminalOrderEntity.setId(UUID.randomUUID().toString());
            LocalDateTime now = LocalDateTime.now();
            long time = now.toEpochSecond(ZoneOffset.UTC);
            terminalOrderEntity.setTimeCreate(time);
            terminalOrderEntity.setTimePaid(terminalOrderInsertDTO.getTimePaid());
            terminalOrderEntity.setStatus(true);
            terminalOrderEntity.setTotalAmount(terminalOrderInsertDTO.getTotalAmount());
            terminalOrderEntity.setVat(terminalOrderInsertDTO.getVat());
            terminalOrderEntity.setVatAmount(terminalOrderInsertDTO.getVatAmount());
            terminalOrderEntity.setDiscountAmount(terminalOrderInsertDTO.getDiscountAmount());
            terminalOrderEntity.setCode(terminalOrderInsertDTO.getCode());
            terminalOrderEntity.setStaffId(terminalOrderInsertDTO.getStaffId());
            terminalOrderEntity.setCustomerId(terminalOrderInsertDTO.getCustomerId());
            terminalOrderEntity.setTid(terminalOrderInsertDTO.getTid());
            terminalOrderRepository.save(terminalOrderEntity);
            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            logger.error("insertTerminalOder ERROR: " + e.getMessage() + " at " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }
        return result;
    }
}
