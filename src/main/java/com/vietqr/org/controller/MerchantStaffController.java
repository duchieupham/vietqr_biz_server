package com.vietqr.org.controller;

import com.vietqr.org.common.StatusResponse;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.merchantstaff.MerchantStaffImportDTO;
import com.vietqr.org.dto.merchantstaff.MerchantStaffInsertDTO;
import com.vietqr.org.service.MerchantStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@RestController
@CrossOrigin
@RequestMapping("/api/merchant-staff")
public class MerchantStaffController {
    @Autowired
    private MerchantStaffService merchantStaffService;

    @PostMapping("/insert-form" )
    public ResponseEntity<ResponseMessageDTO> insertMerchantStaffByForm(@Validated @RequestBody MerchantStaffInsertDTO dto){
        ResponseMessageDTO response = merchantStaffService.insertMerchantStaffByForm(dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @PostMapping("/import")
    public ResponseEntity<Object> importMerchantStaff(InputStream is, MerchantStaffImportDTO dto){
        Object response = merchantStaffService.importMerchantStaff(is, dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @GetMapping("/example-excel")
    public ResponseEntity<Object> getExampleMerchantStaffExcel(HttpServletResponse httpServletResponse){
        Object response = merchantStaffService.getExampleMerchantStaffExcel(httpServletResponse);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }
}
