package com.vietqr.org.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vietqr.org.constant.Status;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.common.ResponseObjectDTO;
import com.vietqr.org.dto.customer.CustomerDataDTO;
import com.vietqr.org.dto.customer.CustomerDetailDTO;
import com.vietqr.org.dto.customer.CustomerInsertDTO;
import com.vietqr.org.dto.customer.CustomerUpdateDTO;
import com.vietqr.org.entity.CustomerEntity;
import com.vietqr.org.repository.CustomerRepository;
import com.vietqr.org.service.CustomerService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final Logger logger = Logger.getLogger(MerchantServiceImpl.class);
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public ResponseMessageDTO saveCustomer(CustomerInsertDTO customerInsertDTO) {
        ResponseMessageDTO result;
        try {
            CustomerEntity customerEntity = new CustomerEntity();
            String id = UUID.randomUUID().toString();
            customerEntity.setId(id);
            customerEntity.setMid(customerInsertDTO.getMid());
            customerEntity.setTid(customerInsertDTO.getTid());
            customerEntity.setUserId(customerInsertDTO.getUserId());
            customerEntity.setPhoneNo(customerInsertDTO.getPhoneNo());
            customerEntity.setStaffId(customerInsertDTO.getStaffId());
            customerEntity.setStatus(true);
            LocalDateTime now = LocalDateTime.now();
            long time = now.toEpochSecond(ZoneOffset.UTC);
            customerEntity.setTimeCreate(time);
            CustomerDataDTO customerDataDTO = new CustomerDataDTO();
            customerDataDTO.setName(customerInsertDTO.getName());
            customerDataDTO.setAddress(customerInsertDTO.getAddress());
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonData = objectMapper.writeValueAsString(customerDataDTO);
            customerEntity.setData(jsonData);
            customerRepository.save(customerEntity);
            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            logger.error("saveCustomer ERROR: " + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }
        return result;
    }

    @Override
    public ResponseMessageDTO updateCustomer(String id, CustomerUpdateDTO customerUpdateDTO) {
        ResponseMessageDTO result;
        try {
            Optional<CustomerEntity> optionalCustomer = customerRepository.findCustomerById(id);
            if (optionalCustomer.isPresent()) {
                CustomerEntity customerEntity = optionalCustomer.get();
                customerRepository.save(updateCustomerField(customerEntity, customerUpdateDTO));
            }
            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            logger.error("updateCustomer ERROR: " + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E191");
        }
        return result;
    }

    @Override
    public ResponseMessageDTO removeCustomer(String id) {
        ResponseMessageDTO result;
        try {
            Optional<CustomerEntity> optionalCustomer = customerRepository.findCustomerById(id);
            if (optionalCustomer.isPresent()) {
                CustomerEntity customerEntity = optionalCustomer.get();
                customerEntity.setStatus(false);
                customerRepository.save(customerEntity);
            }
            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            logger.error("removeCustomer ERROR: " + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E191");
        }
        return result;
    }

    @Override
    public Object customerInfo(String id) {
        Object result;
        CustomerDetailDTO customerDetailDTO = new CustomerDetailDTO();
        try {
            Optional<CustomerEntity> customerEntityOptional = customerRepository.findCustomerById(id);
            if (customerEntityOptional.isPresent()) {
                CustomerEntity customerEntity = customerEntityOptional.get();
                customerDetailDTO.setMid(customerEntity.getMid());
                customerDetailDTO.setTid(customerEntity.getTid());
                customerDetailDTO.setUserId(customerEntity.getUserId());
                customerDetailDTO.setPhoneNo(customerEntity.getPhoneNo());
                customerDetailDTO.setStaffId(customerEntity.getStaffId());
                customerDetailDTO.setTimeCreate(customerEntity.getTimeCreate());
                customerDetailDTO.setData(customerDetailDTO.getData());
            }
            result = new ResponseObjectDTO(Status.SUCCESS, customerDetailDTO);
        } catch (Exception e) {
            logger.error("customerInfo ERROR: " + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E191");
        }
        return result;
    }

    private CustomerEntity updateCustomerField(CustomerEntity customerEntity, CustomerUpdateDTO customerUpdateDTO) {
        if (customerUpdateDTO.getPhoneNo() != null) {
            customerEntity.setPhoneNo(customerUpdateDTO.getPhoneNo());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        CustomerDataDTO customerDataDTO;
        try {
            customerDataDTO = objectMapper.readValue(customerEntity.getData(), CustomerDataDTO.class);
            if (customerUpdateDTO.getName() != null) {
                customerDataDTO.setName(customerUpdateDTO.getName());
            }
            if (customerUpdateDTO.getAddress() != null) {
                customerDataDTO.setAddress(customerUpdateDTO.getAddress());
            }
            String jsonData = objectMapper.writeValueAsString(customerDataDTO);
            customerEntity.setData(jsonData);
        } catch (Exception e) {
            logger.error("updateCustomerField ERROR: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }
        return customerEntity;
    }
}
