package com.vietqr.org.controller;

import com.vietqr.org.common.StatusResponse;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.productprice.ProductPriceUpdateAmountDTO;
import com.vietqr.org.dto.productprice.ProductPriceDTO;
import com.vietqr.org.security.Authorized;
import com.vietqr.org.security.IdParam;
import com.vietqr.org.security.TypeParam;
import com.vietqr.org.service.ProductPriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/product-price")
public class ProductPriceController {
    private final ProductPriceService productPriceService;

    public ProductPriceController(ProductPriceService productPriceService) {
        this.productPriceService = productPriceService;
    }

    @PostMapping("/insert")
    @Authorized("29991b33-5830-48b6-ba31-3c2af8fae515")
    public ResponseEntity<ResponseMessageDTO> insertProductPrice(
            @Validated @RequestParam @IdParam String id,
            @Validated @RequestParam @TypeParam int type,
            @RequestHeader("Authorization") String token,
            @Validated @RequestBody ProductPriceDTO dto
    ) {
        ResponseMessageDTO response = productPriceService.insertProductPrice(dto, token);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @PatchMapping("/update-amount")
    @Authorized("04881702-1d56-48f4-9a12-4fb84fa67b4e")
    public ResponseEntity<ResponseMessageDTO> updateAmountProductPriceById(
            @Validated @RequestParam @IdParam String id,
            @Validated @RequestParam @TypeParam int type,
            @Validated @RequestBody ProductPriceUpdateAmountDTO dto
    ) {
        ResponseMessageDTO response = productPriceService.updateAmountProductPriceById(dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @GetMapping("/{id}")
    @Authorized("3d3b0c66-73ce-4563-b7ef-20895f29de10")
    public ResponseEntity<Object> getProductPriceById(
            @Validated @RequestParam @IdParam String id,
            @Validated @RequestParam @TypeParam int type,
            @Validated @PathVariable(value = "id") String productPriceId
    ) {
        Object response = productPriceService.findProductPriceById(productPriceId);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @GetMapping("/product/{id}")
    @Authorized("d24ad623-87a8-4701-a5e8-26163b8940d5")
    public ResponseEntity<Object> getProductPriceByProductId(
            @Validated @RequestParam @IdParam String id,
            @Validated @RequestParam @TypeParam int type,
            @Validated @PathVariable(value = "id") String productId
    ) {
        Object response = productPriceService.findProductPriceByProductId(productId);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @PutMapping("/update-data")
    @Authorized("5b37ef9b-2b08-4988-9e23-01a24e182f55")
    public ResponseEntity<ResponseMessageDTO> updateDataProductPriceById(
            @Validated @RequestParam @IdParam String id,
            @Validated @RequestParam @TypeParam int type,
            @RequestHeader("Authorization") String token,
            @Validated @RequestBody ProductPriceDTO dto
    ) {
        ResponseMessageDTO response = productPriceService.updateDataProductPriceById(dto, token);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @PutMapping("/product/update-data")
    @Authorized("6a276fc2-a8e1-44a3-b03e-583164cdcf81")
    public ResponseEntity<ResponseMessageDTO> updateDataProductPriceByProductId(
            @Validated @RequestParam @IdParam String id,
            @Validated @RequestParam @TypeParam int type,
            @RequestHeader("Authorization") String token,
            @Validated @RequestBody ProductPriceDTO dto
    ) {
        ResponseMessageDTO response = productPriceService.updateDataProductPriceByProductId(dto, token);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }
}
