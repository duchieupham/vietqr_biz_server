package com.vietqr.org.service;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.terminalorder.TerminalOrderInsertDTO;

public interface TerminalOrderService {
    ResponseMessageDTO insertTerminalOder(TerminalOrderInsertDTO terminalOrderInsertDTO);
}
