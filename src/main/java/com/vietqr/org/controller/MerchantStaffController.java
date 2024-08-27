package com.vietqr.org.controller;

import com.vietqr.org.common.StatusResponse;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.merchantstaff.MerchantStaffInsertDTO;
import com.vietqr.org.service.MerchantStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/merchant-staffs")
public class MerchantStaffController {
    @Autowired
    private MerchantStaffService merchantStaffService;

    @PostMapping("/insert-form" )
    public ResponseEntity<ResponseMessageDTO> insertMerchantStaffByForm(@Validated @RequestBody MerchantStaffInsertDTO dto){
        ResponseMessageDTO response = merchantStaffService.insertMerchantStaffByForm(dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }
}
