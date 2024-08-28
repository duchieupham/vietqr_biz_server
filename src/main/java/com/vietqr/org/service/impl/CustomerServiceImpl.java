package com.vietqr.org.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vietqr.org.constant.Status;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.common.ResponseObjectDTO;
import com.vietqr.org.dto.customer.CustomerDataDTO;
import com.vietqr.org.dto.customer.CustomerDetailDTO;
import com.vietqr.org.dto.customer.CustomerInsertDTO;
import com.vietqr.org.dto.customer.CustomerUpdateDTO;
import com.vietqr.org.entity.MerchantCustomerEntity;
import com.vietqr.org.repository.CustomerRepository;
import com.vietqr.org.service.CustomerService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
            MerchantCustomerEntity merchantCustomerEntity = new MerchantCustomerEntity();
            String id = UUID.randomUUID().toString();
            merchantCustomerEntity.setId(id);
            merchantCustomerEntity.setMid(customerInsertDTO.getMid());
            merchantCustomerEntity.setTid(customerInsertDTO.getTid());
            merchantCustomerEntity.setUserId(customerInsertDTO.getUserId());
            merchantCustomerEntity.setPhoneNo(customerInsertDTO.getPhoneNo());
            merchantCustomerEntity.setStaffId(customerInsertDTO.getStaffId());
            merchantCustomerEntity.setStatus(true);
            LocalDateTime now = LocalDateTime.now();
            long time = now.toEpochSecond(ZoneOffset.UTC);
            merchantCustomerEntity.setTimeCreate(time);
            CustomerDataDTO customerDataDTO = new CustomerDataDTO();
            customerDataDTO.setName(customerInsertDTO.getName());
            customerDataDTO.setAddress(customerInsertDTO.getAddress());
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonData = objectMapper.writeValueAsString(customerDataDTO);
            merchantCustomerEntity.setData(jsonData);
            customerRepository.save(merchantCustomerEntity);
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
            Optional<MerchantCustomerEntity> optionalCustomer = customerRepository.findCustomerById(id);
            if (optionalCustomer.isPresent()) {
                MerchantCustomerEntity merchantCustomerEntity = optionalCustomer.get();
                customerRepository.save(updateCustomerField(merchantCustomerEntity, customerUpdateDTO));
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
            Optional<MerchantCustomerEntity> optionalCustomer = customerRepository.findCustomerById(id);
            if (optionalCustomer.isPresent()) {
                MerchantCustomerEntity merchantCustomerEntity = optionalCustomer.get();
                merchantCustomerEntity.setStatus(false);
                customerRepository.save(merchantCustomerEntity);
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
            Optional<MerchantCustomerEntity> customerEntityOptional = customerRepository.findCustomerById(id);
            if (customerEntityOptional.isPresent()) {
                MerchantCustomerEntity merchantCustomerEntity = customerEntityOptional.get();
                converterCustomerEntityToCustomerDetailDTO(merchantCustomerEntity, customerDetailDTO);
            }
            result = new ResponseObjectDTO(Status.SUCCESS, customerDetailDTO);
        } catch (Exception e) {
            logger.error("customerInfo ERROR: " + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E191");
        }
        return result;
    }

    @Override
    public Object listCustomer() {
        Object result;
        try {
            List<MerchantCustomerEntity> customerEntities = customerRepository.findAllCustomer();
            List<CustomerDetailDTO> customerDetailDTOS = customerEntities.stream().map(merchantCustomerEntity -> {
                CustomerDetailDTO customerDetailDTO = new CustomerDetailDTO();
                return converterCustomerEntityToCustomerDetailDTO(merchantCustomerEntity, customerDetailDTO);
            }).collect(Collectors.toList());
            result = new ResponseObjectDTO(Status.SUCCESS, customerDetailDTOS);
        } catch (Exception e) {
            logger.error("listCustomer ERROR: " + e.getMessage() + " at " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }
        return result;
    }

    private MerchantCustomerEntity updateCustomerField(MerchantCustomerEntity merchantCustomerEntity, CustomerUpdateDTO customerUpdateDTO) {
        if (customerUpdateDTO.getPhoneNo() != null) {
            merchantCustomerEntity.setPhoneNo(customerUpdateDTO.getPhoneNo());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        CustomerDataDTO customerDataDTO;
        try {
            customerDataDTO = objectMapper.readValue(merchantCustomerEntity.getData(), CustomerDataDTO.class);
            if (customerUpdateDTO.getName() != null) {
                customerDataDTO.setName(customerUpdateDTO.getName());
            }
            if (customerUpdateDTO.getAddress() != null) {
                customerDataDTO.setAddress(customerUpdateDTO.getAddress());
            }
            String jsonData = objectMapper.writeValueAsString(customerDataDTO);
            merchantCustomerEntity.setData(jsonData);
        } catch (Exception e) {
            logger.error("updateCustomerField ERROR: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }
        return merchantCustomerEntity;
    }

    private CustomerDetailDTO converterCustomerEntityToCustomerDetailDTO(MerchantCustomerEntity merchantCustomerEntity, CustomerDetailDTO customerDetailDTO) {
        customerDetailDTO.setMid(merchantCustomerEntity.getMid());
        customerDetailDTO.setTid(merchantCustomerEntity.getTid());
        customerDetailDTO.setUserId(merchantCustomerEntity.getUserId());
        customerDetailDTO.setPhoneNo(merchantCustomerEntity.getPhoneNo());
        customerDetailDTO.setStaffId(merchantCustomerEntity.getStaffId());
        customerDetailDTO.setTimeCreate(merchantCustomerEntity.getTimeCreate());
        customerDetailDTO.setData(merchantCustomerEntity.getData());
        return customerDetailDTO;
    }
}
