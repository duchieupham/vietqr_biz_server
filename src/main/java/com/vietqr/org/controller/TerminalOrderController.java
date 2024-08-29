package com.vietqr.org.controller;

import com.vietqr.org.common.StatusResponse;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.terminalorder.TerminalOrderInsertDTO;
import com.vietqr.org.service.TerminalOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/terminal-oder")
public class TerminalOrderController {
    private final TerminalOrderService terminalOderService;

    public TerminalOrderController(TerminalOrderService terminalOderService) {
        this.terminalOderService = terminalOderService;
    }

    @PostMapping()
    public ResponseEntity<ResponseMessageDTO> saveTerminalOder(@RequestBody TerminalOrderInsertDTO terminalOrderInsertDTO) {
        ResponseMessageDTO response = terminalOderService.insertTerminalOder(terminalOrderInsertDTO);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }
}
