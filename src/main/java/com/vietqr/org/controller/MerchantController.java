package com.vietqr.org.controller;

import com.vietqr.org.common.StatusResponse;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.merchant.MerchantRequestDTO;
import com.vietqr.org.security.Authorized;
import com.vietqr.org.security.IdParam;
import com.vietqr.org.security.TypeParam;
import com.vietqr.org.service.MerchantService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

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
    @Authorized("4c78ec77-8a2a-4d49-b0e6-6768a65b0846")
    public ResponseEntity<Object> getMerchantDetail(@RequestParam @IdParam String id,
                                                    @RequestParam @TypeParam int type,
                                                    @RequestParam String mid
                                                    ) {
        Object response = merchantService.merchantInfo(mid);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @PutMapping
    @Authorized("947e16c4-92e0-4200-bb5d-4d84305566a9")
    public ResponseEntity<ResponseMessageDTO> updateMerchant(@RequestParam @IdParam String id,
                                                             @RequestParam @TypeParam int type,
                                                             @RequestParam String mid,
                                                             @RequestBody MerchantRequestDTO merchantRequestDTO
                                                             ) {
        ResponseMessageDTO response = merchantService.updateMerchant(mid, merchantRequestDTO);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @PatchMapping("/{mid}")
    @Authorized("b1e837eb-e391-43ce-bd39-269d5558fac3")
    public ResponseEntity<ResponseMessageDTO> deleteMerchant(@RequestParam @IdParam String id,
                                                             @RequestParam @TypeParam int type,
                                                             @PathVariable String mid
                                                             ) {
        ResponseMessageDTO response = merchantService.deleteMerchant(mid);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @GetMapping("/list-delete")
    @Authorized("00be8a60-d50d-4953-8428-5766a90d4d89")
    public ResponseEntity<Object> getListDeleteMerchant(@RequestParam @IdParam String id,
                                                        @RequestParam @TypeParam int type) {
        Object response = merchantService.getListDeleteMerchant();
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @PatchMapping
    @Authorized("cb07c43d-782e-490f-a2d9-fb940b2de901")
    public ResponseEntity<ResponseMessageDTO> recoverMerchant(@RequestParam @IdParam String id,
                                                              @RequestParam @TypeParam int type,
                                                              @RequestParam String mid) {
        ResponseMessageDTO response = merchantService.recoverMerchant(mid);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @GetMapping("/export/{mid}")
    @Authorized("7f45fdc2-9928-4c11-9220-326b85d838b3")
    public ResponseEntity<ResponseMessageDTO> exportMerchant(@RequestParam @IdParam String id,
                                                             @RequestParam @TypeParam int type,
                                                             @PathVariable String mid, HttpServletResponse httpServletResponse
                                                             ) {
        ResponseMessageDTO response = merchantService.exportMerchantToExcel(httpServletResponse, mid);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @PostMapping("/transfer")
    @Authorized("65de83e7-6238-4117-8b16-20ba6f07bad7")
    public ResponseEntity<ResponseMessageDTO> merchantDataTransfer(@RequestParam @IdParam String id,
                                                                   @RequestParam @TypeParam int type,
                                                                   @RequestParam String oldMid, @RequestParam String newMid
                                                                   ) {
        ResponseMessageDTO response = merchantService.merchantDataTransfer(oldMid, newMid);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseMessageDTO> importMerchant(@RequestParam("file") MultipartFile file) {
        ResponseMessageDTO response = merchantService.importMerchantFromExcel(file);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }
}
