package com.vietqr.org.controller;

import com.vietqr.org.common.StatusResponse;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.customer.MerchantCustomerInsertDTO;
import com.vietqr.org.dto.customer.MerchantCustomerUpdateDTO;
import com.vietqr.org.service.MerchantCustomerService;
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
@RequestMapping("/api/customers")
public class MerchantCustomerController {
    private final MerchantCustomerService customerService;

    public MerchantCustomerController(MerchantCustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<ResponseMessageDTO> insertCustomer(@RequestBody MerchantCustomerInsertDTO merchantCustomerInsertDTO) {
        ResponseMessageDTO response = customerService.saveCustomer(merchantCustomerInsertDTO);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @PutMapping
    public ResponseEntity<ResponseMessageDTO> updateCustomer(@RequestParam String id, @RequestBody MerchantCustomerUpdateDTO merchantCustomerUpdateDTO) {
        ResponseMessageDTO response = customerService.updateCustomer(id, merchantCustomerUpdateDTO);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseMessageDTO> deleteCustomer(@PathVariable String id) {
        ResponseMessageDTO response = customerService.removeCustomer(id);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> customerDetail(@PathVariable String id) {
        Object response = customerService.customerInfo(id);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }

    @GetMapping
    public ResponseEntity<Object> listCustomer() {
        Object response = customerService.listCustomer();
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseObject(response));
    }
}
