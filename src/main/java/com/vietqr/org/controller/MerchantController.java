package com.vietqr.org.controller;

import com.vietqr.org.common.CheckStatusResponse;
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
        ResponseMessageDTO responseMessageDTO = merchantService.insertMerchant(merchantRequestDTO);
        return new ResponseEntity<>(responseMessageDTO, CheckStatusResponse.checkStatusResponseMessageDTO(responseMessageDTO));
    }

    @GetMapping
    public ResponseEntity<Object> getAllMerchant(@RequestParam String id) {
        Object response = merchantService.merchantInfo(id);
        return new ResponseEntity<>(response, CheckStatusResponse.checkStatusResponseObjectDTO(response));
    }

    @PutMapping
    public ResponseEntity<ResponseMessageDTO> updateMerchant(@RequestParam String id,
                                                             @RequestBody MerchantRequestDTO merchantRequestDTO) {
        ResponseMessageDTO response = merchantService.updateMerchant(id, merchantRequestDTO);
        return new ResponseEntity<>(response, CheckStatusResponse.checkStatusResponseMessageDTO(response));
    }

    @DeleteMapping
    public ResponseEntity<ResponseMessageDTO> deleteMerchant(@RequestParam String id) {
        ResponseMessageDTO responseMessageDTO = merchantService.deleteMerchant(id);
        return new ResponseEntity<>(responseMessageDTO, CheckStatusResponse.checkStatusResponseMessageDTO(responseMessageDTO));
    }

    @GetMapping("/list-delete")
    public ResponseEntity<Object> getListDeleteMerchant() {
        Object response = merchantService.getListDeleteMerchant();
        return new ResponseEntity<>(response, CheckStatusResponse.checkStatusResponseObjectDTO(response));
    }

    @PatchMapping
    public ResponseEntity<ResponseMessageDTO> restoreMerchant(@RequestParam String id) {
        ResponseMessageDTO response = merchantService.recoverMerchant(id);
        return new ResponseEntity<>(response, CheckStatusResponse.checkStatusResponseMessageDTO(response));
    }
}
