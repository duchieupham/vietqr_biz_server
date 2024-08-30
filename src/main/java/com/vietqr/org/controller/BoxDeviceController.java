package com.vietqr.org.controller;

import com.vietqr.org.common.StatusResponse;
import com.vietqr.org.dto.boxdevice.BoxDeviceFindMidDTO;
import com.vietqr.org.dto.boxdevice.BoxDeviceStatusDTO;
import com.vietqr.org.dto.boxdevice.BoxDeviceInsertDTO;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.service.BoxDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/box-device")
public class BoxDeviceController {
    @Autowired
    private BoxDeviceService boxDeviceService;

    @PostMapping("/insert")
    ResponseEntity<ResponseMessageDTO> insertBoxDevice(@Validated @RequestBody BoxDeviceInsertDTO dto) {
        ResponseMessageDTO response = boxDeviceService.insertBoxDevice(dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @GetMapping("/mid")
    ResponseEntity<Object> getBoxDeviceByMid(@Validated @RequestBody BoxDeviceFindMidDTO dto) {
        Object response = boxDeviceService.findBoxDeviceByMid(dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @PatchMapping("/active")
    ResponseEntity<ResponseMessageDTO> activeBoxDeviceById(@Validated @RequestBody BoxDeviceStatusDTO dto) {
        ResponseMessageDTO response = boxDeviceService.activeBoxDeviceById(dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @PatchMapping("/inactive")
    ResponseEntity<ResponseMessageDTO> inactiveBoxDeviceById(@Validated @RequestBody BoxDeviceStatusDTO dto) {
        ResponseMessageDTO response = boxDeviceService.inactiveBoxDeviceById(dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @PatchMapping("/delete")
    ResponseEntity<ResponseMessageDTO> deleteBoxDeviceById(@Validated @RequestBody BoxDeviceStatusDTO dto) {
        ResponseMessageDTO response = boxDeviceService.deleteBoxDeviceById(dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }
}
