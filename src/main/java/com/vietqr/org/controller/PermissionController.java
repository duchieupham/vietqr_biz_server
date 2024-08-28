package com.vietqr.org.controller;

import com.vietqr.org.common.StatusResponse;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.permission.PermissionInsertDTO;
import com.vietqr.org.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @PostMapping("/insert")
    public ResponseEntity<ResponseMessageDTO> insertPermission(@Validated @RequestBody PermissionInsertDTO dto){
        ResponseMessageDTO response = permissionService.insertPermission(dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> getPermissionById(@Validated @PathVariable String id){
        Object response = permissionService.getPermissionById(id);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }
}
