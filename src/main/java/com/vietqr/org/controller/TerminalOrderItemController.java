package com.vietqr.org.controller;

import com.vietqr.org.common.StatusResponse;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.terminalorderitem.TerminalOrderItemInsertDTO;
import com.vietqr.org.security.Authorized;
import com.vietqr.org.security.IdParam;
import com.vietqr.org.security.TypeParam;
import com.vietqr.org.service.TerminalOrderItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("api/terminal-order-item")
@RestController
public class TerminalOrderItemController {
    private final TerminalOrderItemService terminalOrderItemService;

    public TerminalOrderItemController(TerminalOrderItemService terminalOrderItemService) {
        this.terminalOrderItemService = terminalOrderItemService;
    }

    @PostMapping("/insert")
    @Authorized("")
    public ResponseEntity<ResponseMessageDTO> insertTerminalOrderItem(
            @Validated @RequestParam @IdParam String id,
            @Validated @RequestParam @TypeParam int type,
            @Validated @RequestBody TerminalOrderItemInsertDTO dto
    ) {
        ResponseMessageDTO response = terminalOrderItemService.insertTerminalOrderItem(dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

}
