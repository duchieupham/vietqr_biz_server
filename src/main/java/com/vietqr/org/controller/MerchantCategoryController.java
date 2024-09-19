package com.vietqr.org.controller;

import com.vietqr.org.common.StatusResponse;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.merchantcategory.MerchantCategoryDTO;
import com.vietqr.org.security.Authorized;
import com.vietqr.org.security.IdParam;
import com.vietqr.org.security.TypeParam;
import com.vietqr.org.service.MerchantCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/merchant-categories")
public class MerchantCategoryController {

    private final MerchantCategoryService categoryService;

    public MerchantCategoryController(MerchantCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @Authorized("")
    public ResponseEntity<ResponseMessageDTO> insertMerchantCategory(
            @RequestParam @IdParam String id,
            @RequestParam @TypeParam int type,
            @RequestBody MerchantCategoryDTO categoryDTO
    ) {
        ResponseMessageDTO response = categoryService.insertMerchantCategory(categoryDTO);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @PutMapping("/{id}")
    @Authorized("")
    public ResponseEntity<ResponseMessageDTO> updateMerchantCategory(
            @RequestParam @IdParam String id,
            @RequestParam @TypeParam int type,
            @PathVariable(value = "id") String cid,
            @RequestBody MerchantCategoryDTO categoryDTO
    ) {
        ResponseMessageDTO response = categoryService.updateMerchantCategory(cid, categoryDTO);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @GetMapping("/merchant/{mid}")
    @Authorized("")
    public ResponseEntity<Object> getMerchantCategoryByMid(
            @RequestParam @IdParam String id,
            @RequestParam @TypeParam int type,
            @PathVariable(value = "mid") String mid
    ) {
        Object response = categoryService.getMerchantCategoryByMid(mid);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @GetMapping("/{id}")
    @Authorized("")
    public ResponseEntity<Object> getMerchantCategoryDetail(
            @RequestParam @IdParam String id,
            @RequestParam @TypeParam int type,
            @PathVariable(value = "id") String mcId
    ) {
        Object response = categoryService.getMerchantCategoryById(mcId);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @PatchMapping("/{id}")
    @Authorized("")
    public ResponseEntity<ResponseMessageDTO> deleteMerchantCategory(
            @RequestParam @IdParam String id,
            @RequestParam @TypeParam int type,
            @PathVariable(value = "id") String mcId
    ) {
        ResponseMessageDTO response = categoryService.deleteMerchantCategory(mcId);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @PatchMapping("/merchant/{mid}")
    @Authorized("")
    public ResponseEntity<ResponseMessageDTO> deleteMerchantCategoryByMid(
            @RequestParam @IdParam String id,
            @RequestParam @TypeParam int type,
            @PathVariable(value = "mid") String mid
    ) {
        ResponseMessageDTO response = categoryService.deleteMerchantCategoryByMid(mid);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }
}
