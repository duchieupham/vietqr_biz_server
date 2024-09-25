package com.vietqr.org.service;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.terminalorderitem.TerminalOrderItemInsertDTO;
import org.springframework.stereotype.Service;

@Service
public interface TerminalOrderItemService {
    ResponseMessageDTO insertTerminalOrderItem(TerminalOrderItemInsertDTO dto);
}
