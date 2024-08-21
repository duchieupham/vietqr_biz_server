package com.vietqr.org.controller;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.terminal.TerminalGetListDTO;
import com.vietqr.org.dto.terminal.TerminalInsertDTO;
import com.vietqr.org.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/terminal")
public class TerminalController {
    @Autowired
    TerminalService terminalService;

    @PostMapping("/insert")
    public ResponseEntity<ResponseMessageDTO> insertTerminal(@Validated @RequestBody TerminalInsertDTO dto){
        ResponseMessageDTO result = terminalService.insertTerminal(dto);
        if("SUCCESS".equals(result.getStatus())){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getListOfTerminal(@Validated @RequestBody TerminalGetListDTO dto){
        Object result = terminalService.getListOfTerminal(dto);
        if(result instanceof ResponseMessageDTO){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
