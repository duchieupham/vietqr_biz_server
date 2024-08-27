package com.vietqr.org.controller;

import com.vietqr.org.common.StatusResponse;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.customer.CustomerInsertDTO;
import com.vietqr.org.dto.customer.CustomerUpdateDTO;
import com.vietqr.org.service.CustomerService;
import org.springframework.http.ResponseEntity;
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
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<ResponseMessageDTO> insertCustomer(@RequestBody CustomerInsertDTO customerInsertDTO) {
        ResponseMessageDTO response = customerService.saveCustomer(customerInsertDTO);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @PutMapping
    public ResponseEntity<ResponseMessageDTO> updateCustomer(@RequestParam String id, @RequestBody CustomerUpdateDTO customerUpdateDTO) {
        ResponseMessageDTO response = customerService.updateCustomer(id, customerUpdateDTO);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseMessageDTO> deleteCustomer(@PathVariable String id) {
        ResponseMessageDTO response = customerService.removeCustomer(id);
        return new ResponseEntity<>(response, StatusResponse.getStatusResponseMessage(response));
    }
}
