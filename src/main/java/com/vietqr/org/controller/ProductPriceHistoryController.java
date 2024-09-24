package com.vietqr.org.controller;

import com.vietqr.org.common.StatusResponse;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.productpricehistory.ProductPriceHistoryDTO;
import com.vietqr.org.security.Authorized;
import com.vietqr.org.security.IdParam;
import com.vietqr.org.security.TypeParam;
import com.vietqr.org.service.ProductPriceHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/product-price-history")
public class ProductPriceHistoryController {
    private final ProductPriceHistoryService productPriceHistoryService;

    public ProductPriceHistoryController(ProductPriceHistoryService productPriceHistoryService) {
        this.productPriceHistoryService = productPriceHistoryService;
    }

    @PostMapping("/insert")
    @Authorized("cab3364a-76ff-4fa1-9541-43ab8c4a157b")
    public ResponseEntity<ResponseMessageDTO> insertProductPriceHistory(
            @Validated @RequestParam @IdParam String id,
            @Validated @RequestParam @TypeParam int type,
            @Validated @RequestBody ProductPriceHistoryDTO dto
    ) {
        ResponseMessageDTO response = productPriceHistoryService.insertProductPriceHistory(dto);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @GetMapping("/product/{id}")
    @Authorized("de7d8129-1ac0-405a-839f-34e57822ffb6")
    public ResponseEntity<Object> statisticProductPriceHistory(
            @Validated @RequestParam @IdParam String id,
            @Validated @RequestParam @TypeParam int type,
            @Validated @PathVariable(value = "id") String productId
    ) {
        Object response = productPriceHistoryService.statisticProductPriceHistory(productId);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @GetMapping("/{id}")
    @Authorized("01e5e659-584d-457f-8023-9b1400884fd0")
    public ResponseEntity<Object> getProductPriceHistoryById(
            @Validated @RequestParam @IdParam String id,
            @Validated @RequestParam @TypeParam int type,
            @Validated @PathVariable(value = "id") String pphId
    ) {
        Object response = productPriceHistoryService.findProductPriceHistoryById(pphId);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }
}
