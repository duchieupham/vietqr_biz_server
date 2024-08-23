package com.vietqr.org.controller;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.common.ResponseObjectDTO;
import com.vietqr.org.dto.merchant.MerchantRequestDTO;
import com.vietqr.org.service.MerchantService;
import com.vietqr.org.common.CheckStatusResponse;
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
    public ResponseEntity<ResponseObjectDTO> getAllMerchant(@RequestParam String id) {
        ResponseObjectDTO responseObjectDTO = merchantService.merchantInfo(id);
        return new ResponseEntity<>(responseObjectDTO, CheckStatusResponse.checkStatusResponseObjectDTO(responseObjectDTO));
    }

    @PutMapping
    public ResponseEntity<ResponseMessageDTO> updateMerchant(@RequestParam String id,
                                                             @RequestBody MerchantRequestDTO merchantRequestDTO) {
        ResponseMessageDTO responseMessageDTO = merchantService.updateMerchant(id, merchantRequestDTO);
        return new ResponseEntity<>(responseMessageDTO, CheckStatusResponse.checkStatusResponseMessageDTO(responseMessageDTO));
    }

    @DeleteMapping
    public ResponseEntity<ResponseMessageDTO> deleteMerchant(@RequestParam String id) {
        ResponseMessageDTO responseMessageDTO = merchantService.deleteMerchant(id);
        return new ResponseEntity<>(responseMessageDTO, CheckStatusResponse.checkStatusResponseMessageDTO(responseMessageDTO));
    }

    @GetMapping("/list-delete")
    public ResponseEntity<ResponseObjectDTO> getListDeleteMerchant() {
        ResponseObjectDTO responseObjectDTO = merchantService.getListDeleteMerchant();
        return new ResponseEntity<>(responseObjectDTO, CheckStatusResponse.checkStatusResponseObjectDTO(responseObjectDTO));
    }

    @PatchMapping
    public ResponseEntity<ResponseMessageDTO> restoreMerchant(@RequestParam String id) {
        ResponseMessageDTO responseMessageDTO = merchantService.recoverMerchant(id);
        return new ResponseEntity<>(responseMessageDTO, CheckStatusResponse.checkStatusResponseMessageDTO(responseMessageDTO));
    }
}
