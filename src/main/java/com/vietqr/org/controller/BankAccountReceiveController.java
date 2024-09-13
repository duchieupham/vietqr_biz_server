package com.vietqr.org.controller;

import com.example.grpc.bankaccountreceive.BankAccountReceive;
import com.vietqr.org.common.StatusResponse;
import com.vietqr.org.constant.Status;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.common.ResponseObjectDTO;
import com.vietqr.org.grpc.client.BankAccountReceiveClient;
import com.vietqr.org.grpc.client.BankAccountReceiveDTO;
import com.vietqr.org.security.Authorized;
import com.vietqr.org.security.IdParam;
import com.vietqr.org.security.TypeParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/grpc/bank")
public class BankAccountReceiveController {
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
