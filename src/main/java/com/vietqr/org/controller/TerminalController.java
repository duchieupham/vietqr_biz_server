package com.vietqr.org.controller;

import com.vietqr.org.common.StatusResponse;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.terminal.*;
import com.vietqr.org.security.Authorized;
import com.vietqr.org.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Object> getListOfTerminal(@Validated @RequestBody TerminalGetListDTO dto) {
        Object response = terminalService.getListOfTerminal(dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTerminalById(@Validated @PathVariable(value = "id") String id, @Validated @RequestBody String userId) {
        Object response = terminalService.getTerminalById(new TerminalAuthDTO(id, userId));
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchTerminals(@Validated @RequestBody TerminalFindDTO dto) {
        Object response = terminalService.searchTerminals(dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessageDTO> updateTerminalById(@Validated @PathVariable(value = "id") String id, @Validated @RequestBody TerminalUpdateDTO dto) {
        ResponseMessageDTO response = terminalService.updateTerminal(id, dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @PatchMapping("/{id}/delete")
    public ResponseEntity<ResponseMessageDTO> deleteTerminalById(@Validated @PathVariable(value = "id") String id, @Validated @RequestBody String userId) {
        ResponseMessageDTO response = terminalService.deleteTerminalById(new TerminalAuthDTO(id, userId));
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @GetMapping("/deleted")
    public ResponseEntity<Object> getListOfTerminalDeleted(@Validated @RequestBody TerminalGetListDTO dto) {
        Object response = terminalService.getListOfTerminalDeleted(dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @PatchMapping("/{id}/recover")
    public ResponseEntity<ResponseMessageDTO> recoverTerminalById(@Validated @PathVariable(value = "id") String id, @Validated @RequestBody String userId) {
        ResponseMessageDTO response = terminalService.recoverTerminalById(new TerminalAuthDTO(id, userId));
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @GetMapping("/{id}/export")
    public ResponseEntity<ResponseMessageDTO> exportTerminalById(@Validated @PathVariable(value = "id") String id, HttpServletResponse httpServletResponse) {
        ResponseMessageDTO response = terminalService.exportTerminalById(httpServletResponse, id);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @GetMapping("/export")
    public ResponseEntity<ResponseMessageDTO> exportTerminalsByMid(@Validated @RequestBody String mid, HttpServletResponse httpServletResponse) {
        ResponseMessageDTO response = terminalService.exportTerminalsByMid(httpServletResponse, mid);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @PostMapping("/import")
    public ResponseEntity<Object> importTerminals(InputStream is) {
        Object response = terminalService.importTerminals(is);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @PostMapping("/transfer")
    public ResponseEntity<ResponseMessageDTO> transferTerminals(@Validated @RequestBody TerminalTransferDTO dto) {
        ResponseMessageDTO response = terminalService.transferTerminals(dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }
}
