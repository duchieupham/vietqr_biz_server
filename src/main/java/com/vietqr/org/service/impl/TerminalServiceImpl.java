package com.vietqr.org.service.impl;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.terminal.TerminalInsertDTO;
import com.vietqr.org.entity.TerminalEntity;
import com.vietqr.org.repository.TerminalRepository;
import com.vietqr.org.service.TerminalService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TerminalServiceImpl implements TerminalService {
    private static final Logger logger = Logger.getLogger(TerminalServiceImpl.class);

    @Autowired
    TerminalRepository repo;

    @Override
    public ResponseMessageDTO insertTerminal(TerminalInsertDTO dto) {
        ResponseMessageDTO result = null;
        try {
            TerminalEntity entity = new TerminalEntity(dto.getName(), dto.getAddress(), dto.getMid(), dto.getCode(), dto.getRawCode(), dto.getPublicId(), dto.getRefId(), dto.getBankId(), dto.getQrBoxId(), dto.isSub(), dto.getData1(), dto.getData2(), dto.getTraceTransfer());
            repo.save(entity);
            result = new ResponseMessageDTO("SUCCESS", "");
        }catch (Exception e){
            result = new ResponseMessageDTO("FAILED", "E05");
            logger.error("insertTerminal: "+ e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }
}
