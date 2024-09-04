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
    @Authorized("84ecfbc7-be43-46f8-bc51-123f44cf8714")
    public ResponseEntity<Object> getTerminalsByMid(
            @Validated @RequestParam String id,
            @Validated @RequestParam int type,
            @Validated @RequestBody String mid
    ) {
        Object response = terminalService.getTerminalsByMid(mid);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @GetMapping("/{id}")
    @Authorized("6e261c66-c001-4cd6-8691-4fb8aeb7b61d")
    public ResponseEntity<Object> getTerminalById(
            @Validated @RequestParam String id,
            @Validated @RequestParam int type,
            @Validated @PathVariable(value = "id") String tid
    ) {
        Object response = terminalService.getTerminalById(tid);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @GetMapping("/search")
    @Authorized("04daf42f-da84-4aa7-a114-a18cc8c181eb")
    public ResponseEntity<Object> searchTerminals(
            @Validated @RequestParam String id,
            @Validated @RequestParam int type,
            @Validated @RequestBody TerminalFindDTO dto
    ) {
        Object response = terminalService.searchTerminals(dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @PutMapping("/{id}")
    @Authorized("b23dbf56-2aa8-4bc0-b35b-83767e4e5b65")
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
    @Authorized("73aeadda-f909-4c8f-ac72-1aefcc20a2f4")
    public ResponseEntity<ResponseMessageDTO> deleteTerminalById(
            @Validated @RequestParam String id,
            @Validated @RequestParam int type,
            @Validated @PathVariable(value = "id") String tid
    ) {
        ResponseMessageDTO response = terminalService.deleteTerminalById(tid);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @GetMapping("/deleted")
    @Authorized("a1546626-0307-4aff-bc66-5acc430c1306")
    public ResponseEntity<Object> getTerminalsDeletedByMid(
            @Validated @RequestParam String id,
            @Validated @RequestParam int type,
            @Validated @RequestBody String mid
    ) {
        Object response = terminalService.getTerminalsDeletedByMid(mid);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @PatchMapping("/{id}/recover")
    @Authorized("ecc19b19-3e8c-4468-bb00-bfd69d7bcc42")
    public ResponseEntity<ResponseMessageDTO> recoverTerminalById(
            @Validated @RequestParam String id,
            @Validated @RequestParam int type,
            @Validated @PathVariable(value = "id") String tid
    ) {
        ResponseMessageDTO response = terminalService.recoverTerminalById(tid);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @GetMapping("/{id}/export")
    @Authorized("acaf0764-dbe0-4877-a9e6-2903a80704e9")
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
    @Authorized("1919c254-b51c-4e50-bc30-89c059116d63")
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
    @Authorized("03c88cd4-79c6-45f4-a1ef-a7cc6317dc06")
    public ResponseEntity<Object> importTerminals(
            @Validated @RequestParam String id,
            @Validated @RequestParam int type,
            InputStream is
    ) {
        Object response = terminalService.importTerminals(is);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @PostMapping("/transfer")
    @Authorized("ec4a923b-33d0-4cb7-b089-f018046a5f6d")
    public ResponseEntity<ResponseMessageDTO> transferTerminals(
            @Validated @RequestParam String id,
            @Validated @RequestParam int type,
            @Validated @RequestBody TerminalTransferDTO dto
    ) {
        ResponseMessageDTO response = terminalService.transferTerminals(dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }
}
