package com.vietqr.org.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vietqr.org.constant.Status;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.common.ResponseObjectDTO;
import com.vietqr.org.dto.customer.MerchantCustomerDataDTO;
import com.vietqr.org.dto.customer.MerchantCustomerDetailDTO;
import com.vietqr.org.dto.customer.MerchantCustomerInsertDTO;
import com.vietqr.org.dto.customer.MerchantCustomerUpdateDTO;
import com.vietqr.org.entity.MerchantCustomerEntity;
import com.vietqr.org.repository.CustomerRepository;
import com.vietqr.org.service.MerchantCustomerService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MerchantCustomerServiceImpl implements MerchantCustomerService {
    private static final Logger logger = Logger.getLogger(MerchantCustomerServiceImpl.class);
    private final CustomerRepository customerRepository;

    public MerchantCustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public ResponseMessageDTO saveCustomer(MerchantCustomerInsertDTO merchantCustomerInsertDTO) {
        ResponseMessageDTO result;
        try {
            MerchantCustomerEntity merchantCustomerEntity = new MerchantCustomerEntity();
            String id = UUID.randomUUID().toString();
            merchantCustomerEntity.setId(id);
            merchantCustomerEntity.setMid(merchantCustomerInsertDTO.getMid());
            merchantCustomerEntity.setTid(merchantCustomerInsertDTO.getTid());
            merchantCustomerEntity.setUserId(merchantCustomerInsertDTO.getUserId());
            merchantCustomerEntity.setPhoneNo(merchantCustomerInsertDTO.getPhoneNo());
            merchantCustomerEntity.setStaffId(merchantCustomerInsertDTO.getStaffId());
            merchantCustomerEntity.setStatus(true);
            LocalDateTime now = LocalDateTime.now();
            long time = now.toEpochSecond(ZoneOffset.UTC);
            merchantCustomerEntity.setTimeCreate(time);
            MerchantCustomerDataDTO merchantCustomerDataDTO = new MerchantCustomerDataDTO();
            merchantCustomerDataDTO.setName(merchantCustomerInsertDTO.getName());
            merchantCustomerDataDTO.setAddress(merchantCustomerInsertDTO.getAddress());
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonData = objectMapper.writeValueAsString(merchantCustomerDataDTO);
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
    public ResponseMessageDTO updateCustomer(String id, MerchantCustomerUpdateDTO merchantCustomerUpdateDTO) {
        ResponseMessageDTO result;
        try {
            Optional<MerchantCustomerEntity> optionalCustomer = customerRepository.findCustomerById(id);
            if (optionalCustomer.isPresent()) {
                MerchantCustomerEntity merchantCustomerEntity = optionalCustomer.get();
                customerRepository.save(updateCustomerField(merchantCustomerEntity, merchantCustomerUpdateDTO));
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
        MerchantCustomerDetailDTO merchantCustomerDetailDTO = new MerchantCustomerDetailDTO();
        try {
            Optional<MerchantCustomerEntity> customerEntityOptional = customerRepository.findCustomerById(id);
            if (customerEntityOptional.isPresent()) {
                MerchantCustomerEntity merchantCustomerEntity = customerEntityOptional.get();
                converterCustomerEntityToCustomerDetailDTO(merchantCustomerEntity, merchantCustomerDetailDTO);
            }
            result = new ResponseObjectDTO(Status.SUCCESS, merchantCustomerDetailDTO);
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
            List<MerchantCustomerDetailDTO> merchantCustomerDetailDTOS = customerEntities.stream().map(merchantCustomerEntity -> {
                MerchantCustomerDetailDTO merchantCustomerDetailDTO = new MerchantCustomerDetailDTO();
                return converterCustomerEntityToCustomerDetailDTO(merchantCustomerEntity, merchantCustomerDetailDTO);
            }).collect(Collectors.toList());
            result = new ResponseObjectDTO(Status.SUCCESS, merchantCustomerDetailDTOS);
        } catch (Exception e) {
            logger.error("listCustomer ERROR: " + e.getMessage() + " at " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }
        return result;
    }

    private MerchantCustomerEntity updateCustomerField(MerchantCustomerEntity merchantCustomerEntity, MerchantCustomerUpdateDTO merchantCustomerUpdateDTO) {
        if (merchantCustomerUpdateDTO.getPhoneNo() != null) {
            merchantCustomerEntity.setPhoneNo(merchantCustomerUpdateDTO.getPhoneNo());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        MerchantCustomerDataDTO merchantCustomerDataDTO;
        try {
            merchantCustomerDataDTO = objectMapper.readValue(merchantCustomerEntity.getData(), MerchantCustomerDataDTO.class);
            if (merchantCustomerUpdateDTO.getName() != null) {
                merchantCustomerDataDTO.setName(merchantCustomerUpdateDTO.getName());
            }
            if (merchantCustomerUpdateDTO.getAddress() != null) {
                merchantCustomerDataDTO.setAddress(merchantCustomerUpdateDTO.getAddress());
            }
            String jsonData = objectMapper.writeValueAsString(merchantCustomerDataDTO);
            merchantCustomerEntity.setData(jsonData);
        } catch (Exception e) {
            logger.error("updateCustomerField ERROR: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }
        return merchantCustomerEntity;
    }

    private MerchantCustomerDetailDTO converterCustomerEntityToCustomerDetailDTO(MerchantCustomerEntity merchantCustomerEntity, MerchantCustomerDetailDTO merchantCustomerDetailDTO) {
        merchantCustomerDetailDTO.setMid(merchantCustomerEntity.getMid());
        merchantCustomerDetailDTO.setTid(merchantCustomerEntity.getTid());
        merchantCustomerDetailDTO.setUserId(merchantCustomerEntity.getUserId());
        merchantCustomerDetailDTO.setPhoneNo(merchantCustomerEntity.getPhoneNo());
        merchantCustomerDetailDTO.setStaffId(merchantCustomerEntity.getStaffId());
        merchantCustomerDetailDTO.setTimeCreate(merchantCustomerEntity.getTimeCreate());
        merchantCustomerDetailDTO.setData(merchantCustomerEntity.getData());
        return merchantCustomerDetailDTO;
    }
}
