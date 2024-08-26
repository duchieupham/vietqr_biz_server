package com.vietqr.org.controller;

import com.vietqr.org.common.StatusResponse;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.merchant.MerchantRequestDTO;
import com.vietqr.org.service.MerchantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {

    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @PostMapping
    public ResponseEntity<ResponseMessageDTO> saveMerchant(@RequestBody MerchantRequestDTO merchantRequestDTO) {
        ResponseMessageDTO response = merchantService.insertMerchant(merchantRequestDTO);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @GetMapping
    public ResponseEntity<Object> getAllMerchant(@RequestParam String id) {
        Object response = merchantService.merchantInfo(id);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @PutMapping
    public ResponseEntity<ResponseMessageDTO> updateMerchant(@RequestParam String id,
                                                             @RequestBody MerchantRequestDTO merchantRequestDTO) {
        ResponseMessageDTO response = merchantService.updateMerchant(id, merchantRequestDTO);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseMessageDTO> deleteMerchant(@PathVariable String id) {
        ResponseMessageDTO response = merchantService.deleteMerchant(id);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @GetMapping("/list-delete")
    public ResponseEntity<Object> getListDeleteMerchant() {
        Object response = merchantService.getListDeleteMerchant();
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @PatchMapping
    public ResponseEntity<ResponseMessageDTO> recoverMerchant(@RequestParam String id) {
        ResponseMessageDTO response = merchantService.recoverMerchant(id);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @GetMapping("/export/{id}")
    public ResponseEntity<ResponseMessageDTO> exportMerchant(@PathVariable String id) {
        ResponseMessageDTO response = merchantService.exportMerchantToExcel(id);
        return new ResponseEntity<>(response, CheckStatusResponse.checkStatusResponseMessageDTO(response));
    }
}
