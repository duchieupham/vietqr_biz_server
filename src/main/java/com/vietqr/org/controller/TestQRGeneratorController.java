package com.vietqr.org.controller;

import com.vietqr.org.constant.Status;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.common.ResponseObjectDTO;
import com.vietqr.org.grpc.client.qrgenerator.QRGeneratorClient;
import com.vietqr.org.grpc.client.qrgenerator.RequestStaticQRDTO;
import com.vietqr.org.grpc.client.qrgenerator.StaticQRDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/grpc/qr")
public class TestQRGeneratorController {
    @Autowired
    private QRGeneratorClient qrGeneratorClient;

    @PostMapping("/static")
    ResponseEntity<Object> getBankAccountReceiveByBankId(
            @RequestHeader("Authorization") String token,
            @Validated @RequestBody RequestStaticQRDTO dto
    ) {
        try {
            dto.setToken(token);
            StaticQRDTO data = qrGeneratorClient.generateStaticQR(dto);
            return new ResponseEntity<>(new ResponseObjectDTO(Status.SUCCESS, data), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessageDTO(Status.FAILED, "E05"), HttpStatus.BAD_REQUEST);
        }
    }
}
