package com.vietqr.org.service.impl;

import com.vietqr.org.constant.Status;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.common.ResponseObjectDTO;
import com.vietqr.org.dto.terminal.TerminalGetListDTO;
import com.vietqr.org.dto.terminal.TerminalInsertDTO;
import com.vietqr.org.entity.TerminalEntity;
import com.vietqr.org.repository.TerminalRepository;
import com.vietqr.org.service.TerminalService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerminalServiceImpl implements TerminalService {
    private static final Logger logger = Logger.getLogger(TerminalServiceImpl.class);

    @Autowired
    private TerminalRepository repo;

    @Override
    public ResponseMessageDTO insertTerminal(TerminalInsertDTO dto) {
        ResponseMessageDTO result = null;
        try {
            TerminalEntity entity = new TerminalEntity(dto.getName(), dto.getAddress(), dto.getMid(), dto.getCode(), dto.getRawCode(), dto.getPublicId(), dto.getRefId(), dto.getBankId(), dto.getQrBoxId(), dto.isSub(), dto.getData1(), dto.getData2(), dto.getTraceTransfer());
            repo.save(entity);
            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("insertTerminal: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public Object getListOfTerminal(TerminalGetListDTO dto) {
        Object result = null;
        if (dto.getMid() != null && !dto.getMid().isEmpty() && dto.getUserId() != null && !dto.getUserId().isEmpty()) {
            try {
                List<TerminalEntity> entities = repo.getListOfTerminal(dto.getMid(), dto.getUserId());
                if (entities != null) {
                    result = new ResponseObjectDTO(Status.SUCCESS, entities);
                } else {
                    result = new ResponseMessageDTO(Status.FAILED, "E185");
                    logger.error("getTerminalsOfMerchant: List of terminal is null" + " at: " + System.currentTimeMillis());
                }
            } catch (Exception e) {
                result = new ResponseObjectDTO(Status.FAILED, "E05");
                logger.error("getTerminalsOfMerchant: " + e.getMessage() + " at: " + System.currentTimeMillis());
            }
        } else {
            result = new ResponseObjectDTO(Status.FAILED, "E46");
            logger.error("getTerminalsOfMerchant: Invalid request body" + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public Object getTerminalById(String id) {
        Object result = null;
        if (id != null && !id.isEmpty()) {
            try {
                TerminalEntity entity = repo.getTerminalById(id);
                if (entity != null) {
                    result = new ResponseObjectDTO(Status.SUCCESS, entity);
                } else {
                    result = new ResponseMessageDTO(Status.FAILED, "E186");
                    logger.error("getTerminal: Terminal is null" + " at: " + System.currentTimeMillis());
                }
            } catch (Exception e) {
                result = new ResponseObjectDTO(Status.FAILED, "E05");
                logger.error("getTerminal: " + e.getMessage() + " at: " + System.currentTimeMillis());
            }
        } else {
            result = new ResponseObjectDTO(Status.FAILED, "E46");
            logger.error("getTerminal: Invalid request body" + " at: " + System.currentTimeMillis());
        }

        return result;
    }
}
