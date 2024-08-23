package com.vietqr.org.controller;

import com.vietqr.org.common.CheckStatusResponse;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.terminal.*;
import com.vietqr.org.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/terminals")
public class TerminalController {
    @Autowired
    TerminalService terminalService;

    @PostMapping("/insert")
    public ResponseEntity<ResponseMessageDTO> insertTerminal(@Validated @RequestBody TerminalInsertDTO dto){
        ResponseMessageDTO responseMessageDTO = terminalService.insertTerminal(dto);
        return new ResponseEntity<>(responseMessageDTO, CheckStatusResponse.checkStatusResponseMessageDTO(responseMessageDTO));
    }

    @GetMapping("/")
    public ResponseEntity<Object> getListOfTerminal(@Validated @RequestBody TerminalGetListDTO dto){
        Object responseObjectDTO = terminalService.getListOfTerminal(dto);
        return new ResponseEntity<>(responseObjectDTO, CheckStatusResponse.checkStatusResponseObjectDTO(responseObjectDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTerminalById(@Validated @PathVariable(value = "id") String id, @Validated @RequestBody String userId){
        Object responseObjectDTO = terminalService.getTerminalById(new TerminalAuthDTO(id,userId));
        return new ResponseEntity<>(responseObjectDTO, CheckStatusResponse.checkStatusResponseObjectDTO(responseObjectDTO));
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchTerminals(@Validated @RequestBody TerminalFindDTO dto){
        Object responseObjectDTO = terminalService.searchTerminals(dto);
        return new ResponseEntity<>(responseObjectDTO, CheckStatusResponse.checkStatusResponseObjectDTO(responseObjectDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessageDTO> updateTerminalById(@Validated @PathVariable(value = "id") String id, @Validated @RequestBody TerminalUpdateDTO dto){
        ResponseMessageDTO responseMessageDTO = terminalService.updateTerminal(id, dto);
        return new ResponseEntity<>(responseMessageDTO, CheckStatusResponse.checkStatusResponseMessageDTO(responseMessageDTO));
    }

    @PutMapping("/{id}/delete")
    public ResponseEntity<ResponseMessageDTO> deleteTerminalById(@Validated @PathVariable(value = "id") String id, @Validated @RequestBody String userId){
        ResponseMessageDTO responseMessageDTO = terminalService.deleteTerminalById(new TerminalAuthDTO(id,userId));
        return new ResponseEntity<>(responseMessageDTO, CheckStatusResponse.checkStatusResponseMessageDTO(responseMessageDTO));
    }

    @GetMapping("/deleted")
    public ResponseEntity<Object> getListOfTerminalDeleted(@Validated @RequestBody TerminalGetListDTO dto){
        Object responseObjectDTO = terminalService.getListOfTerminalDeleted(dto);
        return new ResponseEntity<>(responseObjectDTO, CheckStatusResponse.checkStatusResponseObjectDTO(responseObjectDTO));
    }

    @PutMapping("/{id}/recover")
    public ResponseEntity<ResponseMessageDTO> recoverTerminalById(@Validated @PathVariable(value = "id") String id, @Validated @RequestBody String userId){
        ResponseMessageDTO responseMessageDTO = terminalService.recoverTerminalById(new TerminalAuthDTO(id,userId));
        return new ResponseEntity<>(responseMessageDTO, CheckStatusResponse.checkStatusResponseMessageDTO(responseMessageDTO));
    }
}
