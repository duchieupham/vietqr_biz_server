package com.vietqr.org.controller;

import com.vietqr.org.common.StatusResponse;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.productprice.ProductPriceInsertDTO;
import com.vietqr.org.dto.productprice.ProductPriceUpdateAmountDTO;
import com.vietqr.org.dto.productprice.ProductPriceUpdateData1DTO;
import com.vietqr.org.dto.productprice.ProductPriceUpdateData2DTO;
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
    @Authorized("")
    public ResponseEntity<ResponseMessageDTO> insertProductPrice(
            @Validated @RequestParam @IdParam String id,
            @Validated @RequestParam @TypeParam int type,
            @Validated @RequestBody ProductPriceInsertDTO dto
    ) {
        ResponseMessageDTO response = productPriceService.insertProductPrice(dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @PatchMapping("/update-amount")
    @Authorized("")
    public ResponseEntity<ResponseMessageDTO> updateAmountProductPriceById(
            @Validated @RequestParam @IdParam String id,
            @Validated @RequestParam @TypeParam int type,
            @Validated @RequestBody ProductPriceUpdateAmountDTO dto
    ) {
        ResponseMessageDTO response = productPriceService.updateAmountProductPriceById(dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @GetMapping("/{id}")
    @Authorized("")
    public ResponseEntity<Object> getProductPriceById(
            @Validated @RequestParam @IdParam String id,
            @Validated @RequestParam @TypeParam int type,
            @Validated @PathVariable(value = "id") String productPriceId
    ) {
        Object response = productPriceService.findProductPriceById(productPriceId);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @GetMapping("/product/{id}")
    @Authorized("")
    public ResponseEntity<Object> getProductPriceByProductId(
            @Validated @RequestParam @IdParam String id,
            @Validated @RequestParam @TypeParam int type,
            @Validated @PathVariable(value = "id") String productId
    ) {
        Object response = productPriceService.findProductPriceByProductId(productId);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @PatchMapping("/update-data1")
    @Authorized("")
    public ResponseEntity<ResponseMessageDTO> updateData1ProductPriceById(
            @Validated @RequestParam @IdParam String id,
            @Validated @RequestParam @TypeParam int type,
            @Validated @RequestBody ProductPriceUpdateData1DTO dto
    ) {
        ResponseMessageDTO response = productPriceService.updateData1ProductPriceById(dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @PatchMapping("/update-data2")
    @Authorized("")
    public ResponseEntity<ResponseMessageDTO> updateData2ProductPriceById(
            @Validated @RequestParam @IdParam String id,
            @Validated @RequestParam @TypeParam int type,
            @Validated @RequestBody ProductPriceUpdateData2DTO dto
    ) {
        ResponseMessageDTO response = productPriceService.updateData2ProductPriceById(dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }
}
