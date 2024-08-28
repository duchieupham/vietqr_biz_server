package com.vietqr.org.controller;

import com.vietqr.org.common.StatusResponse;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.merchantrole.MerchantRoleInsertDTO;
import com.vietqr.org.service.MerchantRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/merchant-role")
public class MerchantRoleController {
    @Autowired
    private MerchantRoleService merchantRoleService;

    @PostMapping("/insert")
    ResponseEntity<ResponseMessageDTO> insertMerchantRole(@Validated @RequestBody MerchantRoleInsertDTO dto){
        ResponseMessageDTO response = merchantRoleService.insertMerchantRole(dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> getMerchantRoleById(@Validated @PathVariable String id){
        Object response = merchantRoleService.getMerchantRoleById(id);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }
}
