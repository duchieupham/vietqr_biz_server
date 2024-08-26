package com.vietqr.org.service;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.terminal.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public interface TerminalService {
    public ResponseMessageDTO insertTerminal(TerminalInsertDTO dto);
    public Object getListOfTerminal(TerminalGetListDTO dto);
    public Object getTerminalById(TerminalAuthDTO dto);
    public Object searchTerminals(TerminalFindDTO dto);
    public ResponseMessageDTO updateTerminal(String id, TerminalUpdateDTO dto);
    public ResponseMessageDTO deleteTerminalById(TerminalAuthDTO dto);
    public ResponseMessageDTO recoverTerminalById(TerminalAuthDTO dto);
    public Object getListOfTerminalDeleted(TerminalGetListDTO dto);
    public ResponseMessageDTO exportTerminalById(HttpServletResponse httpServletResponse, String id);
    public ResponseMessageDTO exportTerminalsByMid(HttpServletResponse httpServletResponse, String mid);
}
