package com.vietqr.org.controller;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class ExampleController {
    @GetMapping("status")
    public ResponseEntity<ResponseMessageDTO> getAnnualFeeList() {
        ResponseMessageDTO result = null;
        HttpStatus httpStatus = null;
        try {
            result = new ResponseMessageDTO("SUCCESS", "");
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            result = new ResponseMessageDTO("FAILED", "E05");
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(result, httpStatus);
    }
}
