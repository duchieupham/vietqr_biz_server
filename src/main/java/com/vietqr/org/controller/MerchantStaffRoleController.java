package com.vietqr.org.controller;

import com.vietqr.org.common.StatusResponse;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.merchantstaffrole.MerchantStaffRoleInsertDTO;
import com.vietqr.org.service.MerchantStaffRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/merchant-staff-role")
public class MerchantStaffRoleController {

    private final MerchantStaffRoleService merchantStaffRoleService;

    public MerchantStaffRoleController(MerchantStaffRoleService merchantStaffRoleService) {
        this.merchantStaffRoleService = merchantStaffRoleService;
    }

    @PostMapping
    public ResponseEntity<ResponseMessageDTO> saveMerchantStaffRole(@RequestBody MerchantStaffRoleInsertDTO merchantStaffRoleInsertDTO) {
        ResponseMessageDTO response = merchantStaffRoleService.insertMerchantStaffRole(merchantStaffRoleInsertDTO);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }
}
