package com.vietqr.org.service;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.terminal.TerminalFindDTO;
import com.vietqr.org.dto.terminal.TerminalGetListDTO;
import com.vietqr.org.dto.terminal.TerminalInsertDTO;
import com.vietqr.org.dto.terminal.TerminalUpdateDTO;
import org.springframework.stereotype.Service;

@Service
public interface TerminalService {
    public ResponseMessageDTO insertTerminal(TerminalInsertDTO dto);
    public Object getListOfTerminal(TerminalGetListDTO dto);
    public Object getTerminalById(String id);
    public Object searchTerminals(TerminalFindDTO dto);
    public ResponseMessageDTO updateTerminal(TerminalUpdateDTO dto);
    public boolean isTerminalCodeExist(String code);
}
