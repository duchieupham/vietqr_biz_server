package com.vietqr.org.service;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.customer.CustomerInsertDTO;

public interface CustomerService {
    ResponseMessageDTO saveCustomer(CustomerInsertDTO customerInsertDTO);
}
