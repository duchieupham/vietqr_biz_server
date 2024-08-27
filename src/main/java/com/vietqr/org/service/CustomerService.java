package com.vietqr.org.service;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.customer.CustomerInsertDTO;
import com.vietqr.org.dto.customer.CustomerUpdateDTO;

public interface CustomerService {
    ResponseMessageDTO saveCustomer(CustomerInsertDTO customerInsertDTO);
    ResponseMessageDTO updateCustomer(String id, CustomerUpdateDTO customerUpdateDTO);
    ResponseMessageDTO removeCustomer(String id);
}
