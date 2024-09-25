package com.vietqr.org.service.impl;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.terminalorderitem.TerminalOrderItemInsertDTO;
import com.vietqr.org.repository.TerminalOrderItemRepository;
import com.vietqr.org.service.TerminalOrderItemService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class TerminalOrderItemServiceImpl implements TerminalOrderItemService {
    private static final Logger logger = Logger.getLogger(TerminalOrderItemServiceImpl.class);
    private final String LOG_ERROR = "Failed at TerminalOrderItemServiceImpl: ";

    private final TerminalOrderItemRepository repo;

    public TerminalOrderItemServiceImpl(TerminalOrderItemRepository repo) {
        this.repo = repo;
    }

    @Override
    public ResponseMessageDTO insertTerminalOrderItem(TerminalOrderItemInsertDTO dto) {
        return null;
    }
}
