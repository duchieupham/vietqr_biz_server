package com.vietqr.org.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vietqr.org.constant.Status;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.customer.CustomerInfoDTO;
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
            CustomerInfoDTO customerInfoDTO = new CustomerInfoDTO();
            customerInfoDTO.setName(customerInsertDTO.getName());
            customerInfoDTO.setAddress(customerInsertDTO.getAddress());
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonData = null;
            try {
                jsonData = objectMapper.writeValueAsString(customerInfoDTO);
            } catch (JsonProcessingException e) {
                logger.error("saveCustomer ERROR: " + e.getMessage());
            }
            customerEntity.setData(jsonData);
            customerRepository.save(customerEntity);
            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            logger.error("saveCustomer ERROR: " + e.getMessage());
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
            logger.error("updateCustomer ERROR: " + e.getMessage());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
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
            logger.error("removeCustomer ERROR: " + e.getMessage());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }
        return result;
    }

    private CustomerEntity updateCustomerField(CustomerEntity customerEntity, CustomerUpdateDTO customerUpdateDTO) {
        if (customerUpdateDTO.getPhoneNo() != null) {
            customerEntity.setPhoneNo(customerUpdateDTO.getPhoneNo());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        CustomerInfoDTO customerInfoDTO;
        try {
            customerInfoDTO = objectMapper.readValue(customerEntity.getData(), CustomerInfoDTO.class);
            if (customerUpdateDTO.getName() != null) {
                customerInfoDTO.setName(customerUpdateDTO.getName());
            }
            if (customerUpdateDTO.getAddress() != null) {
                customerInfoDTO.setAddress(customerUpdateDTO.getAddress());
            }
            String jsonData = objectMapper.writeValueAsString(customerInfoDTO);
            customerEntity.setData(jsonData);
        } catch (Exception e) {
            logger.error("updateCustomerField ERROR: " + e.getMessage());
        }
        return customerEntity;
    }
}
