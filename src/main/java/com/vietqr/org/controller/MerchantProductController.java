package com.vietqr.org.controller;

import com.vietqr.org.common.StatusResponse;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.merchantproduct.MerchantProductDTO;
import com.vietqr.org.security.Authorized;
import com.vietqr.org.service.MerchantProductService;
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

@RestController
@RequestMapping("/api/merchant-product")
public class MerchantProductController {
    private final MerchantProductService merchantProductService;

    public MerchantProductController(MerchantProductService merchantProductService) {
        this.merchantProductService = merchantProductService;
    }

    @PostMapping
    @Authorized("")
    public ResponseEntity<ResponseMessageDTO> insertMerchantProduct(@RequestParam String id,
                                                                    @RequestParam int type,
                                                                    @RequestBody MerchantProductDTO merchantProductDTO) {
        ResponseMessageDTO response = merchantProductService.saveMerchantProduct(merchantProductDTO);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @PutMapping("/{pid}")
    @Authorized("")
    public ResponseEntity<ResponseMessageDTO> updateMerchantProduct(@RequestParam String id,
                                                                    @RequestParam int type,
                                                                    @PathVariable String pid,
                                                                    @RequestBody MerchantProductDTO merchantProductDTO) {
        ResponseMessageDTO response = merchantProductService.updateMerchantProduct(pid, merchantProductDTO);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @GetMapping
    @Authorized("")
    public ResponseEntity<Object> getAllMerchantProduct(@RequestParam String id,
                                                        @RequestParam int type) {
        Object response = merchantProductService.getListMerchantProduct();
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @GetMapping("/{pid}")
    @Authorized("")
    public ResponseEntity<Object> getMerchantProductDetail(@RequestParam String id,
                                                           @RequestParam int type,
                                                           @PathVariable String pid) {
        Object response = merchantProductService.getMerchantProductById(pid);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @PatchMapping("/{pid}")
    @Authorized("")
    public ResponseEntity<ResponseMessageDTO> removeMerchantProduct(@RequestParam String id,
                                                                    @RequestParam int type,
                                                                    @PathVariable String pid) {
        ResponseMessageDTO response = merchantProductService.deleteMerchantProduct(pid);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }
}
