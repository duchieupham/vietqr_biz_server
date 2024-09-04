package com.vietqr.org.controller;

import com.vietqr.org.common.StatusResponse;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.terminal.*;
import com.vietqr.org.security.Authorized;
import com.vietqr.org.service.TerminalService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@RestController
@CrossOrigin
@RequestMapping("/api/terminals")
public class TerminalController {
    private final TerminalService terminalService;

    public TerminalController(TerminalService terminalService) {
        this.terminalService = terminalService;
    }

    @PostMapping("/insert")
    @Authorized("6c31c106-d8fd-4910-8148-aaafde33bbec")
    public ResponseEntity<ResponseMessageDTO> insertTerminal(
            @Validated @RequestParam String id,
            @Validated @RequestParam int type,
            @Validated @RequestBody TerminalInsertDTO dto
    ) {
        ResponseMessageDTO response = terminalService.insertTerminal(dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @GetMapping("/")
    @Authorized("")
    public ResponseEntity<Object> getListOfTerminal(
            @Validated @RequestParam String id,
            @Validated @RequestParam int type,
            @Validated @RequestBody String mid
    ) {
        Object response = terminalService.getListOfTerminal(mid);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @GetMapping("/{id}")
    @Authorized("")
    public ResponseEntity<Object> getTerminalById(
            @Validated @RequestParam String id,
            @Validated @RequestParam int type,
            @Validated @PathVariable(value = "id") String tid
    ) {
        Object response = terminalService.getTerminalById(tid);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @GetMapping("/search")
    @Authorized("")
    public ResponseEntity<Object> searchTerminals(
            @Validated @RequestParam String id,
            @Validated @RequestParam int type,
            @Validated @RequestBody TerminalFindDTO dto
    ) {
        Object response = terminalService.searchTerminals(dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @PutMapping("/{id}")
    @Authorized("")
    public ResponseEntity<ResponseMessageDTO> updateTerminalById(
            @Validated @RequestParam String id,
            @Validated @RequestParam int type,
            @Validated @PathVariable(value = "id") String tid,
            @Validated @RequestBody TerminalUpdateDTO dto
    ) {
        ResponseMessageDTO response = terminalService.updateTerminal(tid, dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @PatchMapping("/{id}/delete")
    @Authorized("")
    public ResponseEntity<ResponseMessageDTO> deleteTerminalById(
            @Validated @RequestParam String id,
            @Validated @RequestParam int type,
            @Validated @PathVariable(value = "id") String tid
    ) {
        ResponseMessageDTO response = terminalService.deleteTerminalById(tid);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @GetMapping("/deleted")
    @Authorized("")
    public ResponseEntity<Object> getListOfTerminalDeleted(
            @Validated @RequestParam String id,
            @Validated @RequestParam int type,
            @Validated @RequestBody String mid
    ) {
        Object response = terminalService.getListOfTerminalDeleted(mid);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @PatchMapping("/{id}/recover")
    @Authorized("")
    public ResponseEntity<ResponseMessageDTO> recoverTerminalById(
            @Validated @RequestParam String id,
            @Validated @RequestParam int type,
            @Validated @PathVariable(value = "id") String tid
    ) {
        ResponseMessageDTO response = terminalService.recoverTerminalById(tid);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @GetMapping("/{id}/export")
    @Authorized("")
    public ResponseEntity<ResponseMessageDTO> exportTerminalById(
            @Validated @RequestParam String id,
            @Validated @RequestParam int type,
            @Validated @PathVariable(value = "id") String tid,
            HttpServletResponse httpServletResponse
    ) {
        ResponseMessageDTO response = terminalService.exportTerminalById(httpServletResponse, tid);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @GetMapping("/export")
    @Authorized("")
    public ResponseEntity<ResponseMessageDTO> exportTerminalsByMid(
            @Validated @RequestParam String id,
            @Validated @RequestParam int type,
            @Validated @RequestBody String mid,
            HttpServletResponse httpServletResponse
    ) {
        ResponseMessageDTO response = terminalService.exportTerminalsByMid(httpServletResponse, id);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @PostMapping("/import")
    @Authorized("")
    public ResponseEntity<Object> importTerminals(
            @Validated @RequestParam String id,
            @Validated @RequestParam int type,
            InputStream is
    ) {
        Object response = terminalService.importTerminals(is);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @PostMapping("/transfer")
    @Authorized("")
    public ResponseEntity<ResponseMessageDTO> transferTerminals(
            @Validated @RequestParam String id,
            @Validated @RequestParam int type,
            @Validated @RequestBody TerminalTransferDTO dto
    ) {
        ResponseMessageDTO response = terminalService.transferTerminals(dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }
}
