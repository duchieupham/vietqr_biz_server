package com.vietqr.org.service;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.customer.MerchantCustomerInsertDTO;
import com.vietqr.org.dto.customer.MerchantCustomerUpdateDTO;

public interface MerchantCustomerService {
    ResponseMessageDTO saveCustomer(MerchantCustomerInsertDTO merchantCustomerInsertDTO);
    ResponseMessageDTO updateCustomer(String id, MerchantCustomerUpdateDTO merchantCustomerUpdateDTO);
    ResponseMessageDTO removeCustomer(String id);
    Object customerInfo(String id);
    Object listCustomer();
}
