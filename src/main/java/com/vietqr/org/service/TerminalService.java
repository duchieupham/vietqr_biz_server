package com.vietqr.org.service;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.terminal.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@Service
public interface TerminalService {
    public ResponseMessageDTO insertTerminal(TerminalInsertDTO dto);
    public Object getListOfTerminal(String mid);
    public Object getTerminalById(String tid);
    public Object searchTerminals(TerminalFindDTO dto);
    public ResponseMessageDTO updateTerminal(String id, TerminalUpdateDTO dto);
    public ResponseMessageDTO deleteTerminalById(String tid);
    public ResponseMessageDTO recoverTerminalById(String tid);
    public Object getListOfTerminalDeleted(String tid);
    public ResponseMessageDTO exportTerminalById(HttpServletResponse httpServletResponse, String id);
    public ResponseMessageDTO exportTerminalsByMid(HttpServletResponse httpServletResponse, String mid);
    public Object importTerminals(InputStream is);
    public ResponseMessageDTO transferTerminals(TerminalTransferDTO dto);
}
