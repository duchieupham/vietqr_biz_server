package com.vietqr.org.controller;

import com.vietqr.org.common.StatusResponse;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.terminalorder.TerminalOrderInsertDTO;
import com.vietqr.org.security.IdParam;
import com.vietqr.org.security.TypeParam;
import com.vietqr.org.service.TerminalOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/terminal-order")
public class TerminalOrderController {
    private final TerminalOrderService terminalOrderService;

    public TerminalOrderController(TerminalOrderService terminalOrderService) {
        this.terminalOrderService = terminalOrderService;
    }

    @PostMapping()
    public ResponseEntity<ResponseMessageDTO> saveTerminalOrder(
            @RequestParam @IdParam String id,
            @RequestParam @TypeParam int type,
            @RequestBody TerminalOrderInsertDTO terminalOrderInsertDTO
    ) {
        ResponseMessageDTO response = terminalOrderService.insertTerminalOrder(terminalOrderInsertDTO);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTerminalOrderInfoById(
            @RequestParam @IdParam String id,
            @RequestParam @TypeParam int type,
            @PathVariable("id") String toId
    ) {
        Object response = terminalOrderService.findTerminalOrderInfoById(toId);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Object> updateTerminalOrderStatusById(
            @RequestParam @IdParam String id,
            @RequestParam @TypeParam int type,
            @RequestParam @TypeParam int status,
            @PathVariable("id") String toId
    ) {
        ResponseMessageDTO response = terminalOrderService.updateTerminalOrderStatusById(toId, status);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }
}
