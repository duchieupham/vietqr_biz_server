package com.vietqr.org.controller;

import com.vietqr.org.constant.Status;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.common.ResponseObjectDTO;
import com.vietqr.org.grpc.client.bankaccountreceive.BankAccountReceiveClient;
import com.vietqr.org.grpc.client.bankaccountreceive.BankAccountReceiveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/grpc/bank")
public class TestBankAccountReceiveController {
    @Autowired
    private BankAccountReceiveClient bankAccountReceiveClient;

    @GetMapping("/{id}")
    ResponseEntity<Object> getBankAccountReceiveByBankId(
            @Validated @PathVariable(value = "id") String bankId
    ) {
        try {
            BankAccountReceiveDTO data = bankAccountReceiveClient.getBankAccountReceiveByBankId(bankId);
            return new ResponseEntity<>(new ResponseObjectDTO(Status.SUCCESS, data), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessageDTO(Status.FAILED, "E05"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/{id}")
    ResponseEntity<Object> getBankAccountReceiveByUserId(
            @Validated @PathVariable(value = "id") String userId
    ) {
        try {
            List<BankAccountReceiveDTO> data = bankAccountReceiveClient.getBankAccountReceiveByUserId(userId);
            return new ResponseEntity<>(new ResponseObjectDTO(Status.SUCCESS, data), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessageDTO(Status.FAILED, "E05"), HttpStatus.BAD_REQUEST);
        }
    }
}