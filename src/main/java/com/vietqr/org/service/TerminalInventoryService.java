package com.vietqr.org.service;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.terminalinventory.TerminalInventoryInsertDTO;
import com.vietqr.org.dto.terminalinventory.TerminalInventoryUpdateExpDTO;
import com.vietqr.org.dto.terminalinventory.TerminalInventoryUpdateQuantityDTO;
import org.springframework.stereotype.Service;

@Service
public interface TerminalInventoryService {
    ResponseMessageDTO insertTerminalInventory(TerminalInventoryInsertDTO dto);

    ResponseMessageDTO updateQuantityTerminalInventoryById(TerminalInventoryUpdateQuantityDTO dto);

    Object findTerminalInventoryById(String terminalInventoryId);

    Object findTerminalInventoryByTid(String tid);

    ResponseMessageDTO updateExpTerminalInventoryById(TerminalInventoryUpdateExpDTO dto);
}
