package com.vietqr.org.controller;

import com.vietqr.org.common.StatusResponse;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.terminalinventory.TerminalInventoryInsertDTO;
import com.vietqr.org.dto.terminalinventory.TerminalInventoryUpdateExpDTO;
import com.vietqr.org.dto.terminalinventory.TerminalInventoryUpdateQuantityDTO;
import com.vietqr.org.security.Authorized;
import com.vietqr.org.service.TerminalInventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/terminal-inventory")
public class TerminalInventoryController {
    private final TerminalInventoryService terminalInventoryService;

    public TerminalInventoryController(TerminalInventoryService terminalInventoryService) {
        this.terminalInventoryService = terminalInventoryService;
    }

    @PostMapping("/insert")
    @Authorized("")
    public ResponseEntity<ResponseMessageDTO> insertTerminalInventory(
            @Validated @RequestParam String id,
            @Validated @RequestParam int type,
            @Validated @RequestBody TerminalInventoryInsertDTO dto
    ) {
        ResponseMessageDTO response = terminalInventoryService.insertTerminalInventory(dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @PatchMapping("/update-amount")
    @Authorized("")
    public ResponseEntity<ResponseMessageDTO> updateQuantityTerminalInventoryById(
            @Validated @RequestParam String id,
            @Validated @RequestParam int type,
            @Validated @RequestBody TerminalInventoryUpdateQuantityDTO dto
    ) {
        ResponseMessageDTO response = terminalInventoryService.updateQuantityTerminalInventoryById(dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @GetMapping("/{id}")
    @Authorized("")
    public ResponseEntity<Object> getTerminalInventoryById(
            @Validated @RequestParam String id,
            @Validated @RequestParam int type,
            @Validated @PathVariable(value = "id") String terminalInventoryId
    ) {
        Object response = terminalInventoryService.findTerminalInventoryById(terminalInventoryId);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @GetMapping("/tid/{id}")
    @Authorized("")
    public ResponseEntity<Object> getTerminalInventoryByTid(
            @Validated @RequestParam String id,
            @Validated @RequestParam int type,
            @Validated @PathVariable(value = "id") String tid
    ) {
        Object response = terminalInventoryService.findTerminalInventoryByTid(tid);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @PutMapping("/exp")
    @Authorized("")
    public ResponseEntity<ResponseMessageDTO> updateExpTerminalInventoryById(
            @Validated @RequestParam String id,
            @Validated @RequestParam int type,
            @Validated @RequestBody TerminalInventoryUpdateExpDTO dto
    ) {
        ResponseMessageDTO response = terminalInventoryService.updateExpTerminalInventoryById(dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }
}
