package com.vietqr.org.controller;

import com.vietqr.org.common.StatusResponse;
import com.vietqr.org.dto.boxdevice.BoxDeviceInsertDTO;
import com.vietqr.org.dto.boxdevice.BoxDeviceStatusDTO;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.security.Authorized;
import com.vietqr.org.security.IdParam;
import com.vietqr.org.security.TypeParam;
import com.vietqr.org.service.BoxDeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/box-device")
public class BoxDeviceController {
    private final BoxDeviceService boxDeviceService;

    public BoxDeviceController(BoxDeviceService boxDeviceService) {
        this.boxDeviceService = boxDeviceService;
    }

    @PostMapping("/insert")
    @Authorized("81877454-77fc-4186-a3ec-e574470310c2")
    ResponseEntity<ResponseMessageDTO> insertBoxDevice(
            @Validated @RequestParam @IdParam String id,
            @Validated @RequestParam @TypeParam int type,
            @Validated @RequestBody BoxDeviceInsertDTO dto
    ) {
        ResponseMessageDTO response = boxDeviceService.insertBoxDevice(dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @GetMapping("/mid")
    @Authorized("119d2624-4921-47d1-92f4-92008a6cd2b4")
    ResponseEntity<Object> getBoxDeviceByMid(
            @Validated @RequestParam @IdParam String id,
            @Validated @RequestParam @TypeParam int type,
            @Validated @RequestBody String mid
    ) {
        Object response = boxDeviceService.findBoxDeviceByMid(mid);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @GetMapping("/tid")
    @Authorized("92021d6f-76e7-4963-a00a-86e6bc182295")
    ResponseEntity<Object> getBoxDeviceByTid(
            @Validated @RequestParam @IdParam String id,
            @Validated @RequestParam @TypeParam int type,
            @Validated @RequestBody String tid
    ) {
        Object response = boxDeviceService.findBoxDeviceByTid(tid);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @PatchMapping("/active")
    @Authorized("c5636da6-acee-41d0-be44-11296c734445")
    ResponseEntity<ResponseMessageDTO> activeBoxDeviceById(
            @Validated @RequestParam @IdParam String id,
            @Validated @RequestParam @TypeParam int type,
            @Validated @RequestBody BoxDeviceStatusDTO dto
    ) {
        ResponseMessageDTO response = boxDeviceService.activeBoxDeviceById(dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @PatchMapping("/inactive")
    @Authorized("c96d333b-c547-4652-a6cf-267c39b09ab7")
    ResponseEntity<ResponseMessageDTO> inactiveBoxDeviceById(
            @Validated @RequestParam @IdParam String id,
            @Validated @RequestParam @TypeParam int type,
            @Validated @RequestBody BoxDeviceStatusDTO dto
    ) {
        ResponseMessageDTO response = boxDeviceService.inactiveBoxDeviceById(dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @PatchMapping("/delete")
    @Authorized("6cf008cc-0adf-4473-a4e4-08a64459b5f1")
    ResponseEntity<ResponseMessageDTO> deleteBoxDeviceById(
            @Validated @RequestParam @IdParam String id,
            @Validated @RequestParam @TypeParam int type,
            @Validated @RequestBody BoxDeviceStatusDTO dto
    ) {
        ResponseMessageDTO response = boxDeviceService.deleteBoxDeviceById(dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }
}
