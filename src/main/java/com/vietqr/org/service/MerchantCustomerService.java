package com.vietqr.org.service;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.merchantcustomer.MerchantCustomerInsertDTO;
import com.vietqr.org.dto.merchantcustomer.MerchantCustomerUpdateDTO;

public interface MerchantCustomerService {
    ResponseMessageDTO saveCustomer(MerchantCustomerInsertDTO merchantCustomerInsertDTO);
    ResponseMessageDTO updateCustomer(String id, MerchantCustomerUpdateDTO merchantCustomerUpdateDTO);
    ResponseMessageDTO removeCustomer(String id);
    Object getCustomerInfo(String id);
    Object listCustomer();
    ResponseMessageDTO customersPayForOrders(String terminalOderId, String customerId);
}
