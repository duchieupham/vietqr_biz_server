package com.vietqr.org.controller;

import com.vietqr.org.common.StatusResponse;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.merchantcategory.InsertMerchantCategoryDTO;
import com.vietqr.org.security.Authorized;
import com.vietqr.org.security.IdParam;
import com.vietqr.org.security.TypeParam;
import com.vietqr.org.service.MerchantCategoryService;
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
@RequestMapping("/api/merchant-categories")
public class MerchantCategoryController {

    private final MerchantCategoryService categoryService;

    public MerchantCategoryController(MerchantCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @Authorized("")
    public ResponseEntity<ResponseMessageDTO> insertMerchantCategory(@RequestParam @IdParam String id,
                                                                     @RequestParam @TypeParam int type,
                                                                     @RequestBody InsertMerchantCategoryDTO categoryDTO
                                                                     ) {
        ResponseMessageDTO response = categoryService.saveMerchantCategory(categoryDTO);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @PutMapping("/{cid}")
    @Authorized("")
    public ResponseEntity<ResponseMessageDTO> updateMerchantCategory(@RequestParam @IdParam String id,
                                                                     @RequestParam @TypeParam int type,
                                                                     @PathVariable(value = "cid") String cid,
                                                                     @RequestBody InsertMerchantCategoryDTO categoryDTO
                                                                     ) {
        ResponseMessageDTO response = categoryService.updateMerchantCategory(cid, categoryDTO);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @GetMapping
    @Authorized("")
    public ResponseEntity<Object> getAllMerchantCategory(@RequestParam @IdParam String id,
                                                         @RequestParam @TypeParam int type) {
        Object response = categoryService.getListMerchantCategory();
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @GetMapping("/{mid}")
    @Authorized("")
    public ResponseEntity<Object> getMerchantCategoryDetail(@RequestParam @IdParam String id,
                                                            @RequestParam @TypeParam int type,
                                                            @PathVariable String mid
                                                            ) {
        Object response = categoryService.getMerchantCategoryById(mid);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @PatchMapping("/{mid}")
    @Authorized("")
    public ResponseEntity<ResponseMessageDTO> removeMerchantCategory(@RequestParam @IdParam String id,
                                                                     @RequestParam @TypeParam int type,
                                                                     @PathVariable String mid
                                                                     ) {
        ResponseMessageDTO response = categoryService.deleteMerchantCategory(mid);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }
}
