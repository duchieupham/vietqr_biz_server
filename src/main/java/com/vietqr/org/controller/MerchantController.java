package com.vietqr.org.controller;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.common.ResponseObjectDTO;
import com.vietqr.org.dto.merchant.MerchantRequestDTO;
import com.vietqr.org.service.MerchantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {

    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @PostMapping
    public ResponseEntity<ResponseMessageDTO> saveMerchant(@RequestBody MerchantRequestDTO merchantRequestDTO) {
        ResponseMessageDTO responseMessageDTO = merchantService.insertMerchant(merchantRequestDTO);
        return new ResponseEntity<>(responseMessageDTO, checkStatusResponseMessageDTO(responseMessageDTO));
    }

    @GetMapping
    public ResponseEntity<ResponseObjectDTO> getAllMerchant(@RequestParam String id) {
        ResponseObjectDTO responseObjectDTO = merchantService.merchantInfo(id);
        return new ResponseEntity<>(responseObjectDTO, checkStatusResponseObjectDTO(responseObjectDTO));
    }

    @PutMapping
    public ResponseEntity<ResponseMessageDTO> updateMerchant(@RequestParam String id,
                                                             @RequestBody MerchantRequestDTO merchantRequestDTO) {
        ResponseMessageDTO responseMessageDTO = merchantService.updateMerchant(id, merchantRequestDTO);
        return new ResponseEntity<>(responseMessageDTO, checkStatusResponseMessageDTO(responseMessageDTO));
    }

    @DeleteMapping
    public ResponseEntity<ResponseMessageDTO> deleteMerchant(@RequestParam String id) {
        ResponseMessageDTO responseMessageDTO = merchantService.deleteMerchant(id);
        return new ResponseEntity<>(responseMessageDTO, checkStatusResponseMessageDTO(responseMessageDTO));
    }

    @GetMapping("/list-delete")
    public ResponseEntity<ResponseObjectDTO> getListDeleteMerchant() {
        ResponseObjectDTO responseObjectDTO = merchantService.getListDeleteMerchant();
        return new ResponseEntity<>(responseObjectDTO, checkStatusResponseObjectDTO(responseObjectDTO));
    }

    @PatchMapping
    public ResponseEntity<ResponseMessageDTO> restoreMerchant(@RequestParam String id) {
        ResponseMessageDTO responseMessageDTO = merchantService.recoverMerchant(id);
        return new ResponseEntity<>(responseMessageDTO, checkStatusResponseMessageDTO(responseMessageDTO));
    }

    private HttpStatus checkStatusResponseMessageDTO(ResponseMessageDTO responseMessageDTO) {
        HttpStatus httpStatus;
        if (responseMessageDTO.getStatus().equals("SUCCESS")) {
            httpStatus = HttpStatus.OK;
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return httpStatus;
    }

    private HttpStatus checkStatusResponseObjectDTO(ResponseObjectDTO responseObjectDTO) {
        HttpStatus httpStatus;
        if (responseObjectDTO.getStatus().equals("SUCCESS")) {
            httpStatus = HttpStatus.OK;
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return httpStatus;
    }
}
