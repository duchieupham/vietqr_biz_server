package com.vietqr.org.service;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.terminal.*;
import org.springframework.stereotype.Service;

@Service
public interface TerminalService {
    public ResponseMessageDTO insertTerminal(TerminalInsertDTO dto);
    public Object getListOfTerminal(TerminalGetListDTO dto);
    public Object getTerminalById(TerminalGetByIdDTO dto);
    public Object searchTerminals(TerminalFindDTO dto);
    public ResponseMessageDTO updateTerminal(TerminalUpdateDTO dto);
    public boolean isTerminalCodeExist(String code);
}
