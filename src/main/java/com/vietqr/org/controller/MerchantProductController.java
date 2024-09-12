package com.vietqr.org.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vietqr.org.common.StatusResponse;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.merchantproduct.MerchantProductDTO;
import com.vietqr.org.security.Authorized;
import com.vietqr.org.security.IdParam;
import com.vietqr.org.security.TypeParam;
import com.vietqr.org.service.MerchantProductService;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/merchant-product")
public class MerchantProductController {
    private final MerchantProductService merchantProductService;

    public MerchantProductController(MerchantProductService merchantProductService) {
        this.merchantProductService = merchantProductService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Authorized("")
    public ResponseEntity<ResponseMessageDTO> insertMerchantProduct(
            @RequestParam @IdParam String id,
            @RequestParam @TypeParam int type,
            @RequestPart("merchantProductDTO") String merchantProductDTOString,
            @RequestPart MultipartFile file) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        MerchantProductDTO merchantProductDTO = objectMapper.readValue(merchantProductDTOString, MerchantProductDTO.class);
        ResponseMessageDTO response = merchantProductService.saveMerchantProduct(file, merchantProductDTO);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @PutMapping("/{pid}")
    @Authorized("")
    public ResponseEntity<ResponseMessageDTO> updateMerchantProduct(@RequestParam @IdParam String id,
                                                                    @RequestParam @TypeParam int type,
                                                                    @PathVariable String pid,
                                                                    @RequestBody MerchantProductDTO merchantProductDTO) {
        ResponseMessageDTO response = merchantProductService.updateMerchantProduct(pid, merchantProductDTO);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @GetMapping
    @Authorized("")
    public ResponseEntity<Object> getAllMerchantProduct(@RequestParam @IdParam String id,
                                                        @RequestParam @TypeParam int type) {
        Object response = merchantProductService.getListMerchantProduct();
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @GetMapping("/{pid}")
    @Authorized("")
    public ResponseEntity<Object> getMerchantProductDetail(@RequestParam @IdParam String id,
                                                           @RequestParam @TypeParam int type,
                                                           @PathVariable String pid) {
        Object response = merchantProductService.getMerchantProductById(pid);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @PatchMapping("/{pid}")
    @Authorized("")
    public ResponseEntity<ResponseMessageDTO> removeMerchantProduct(@RequestParam @IdParam String id,
                                                                    @RequestParam @TypeParam int type,
                                                                    @PathVariable String pid) {
        ResponseMessageDTO response = merchantProductService.deleteMerchantProduct(pid);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }
}
