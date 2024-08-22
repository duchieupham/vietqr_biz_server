package com.vietqr.org.service.impl;

import com.vietqr.org.constant.Status;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.common.ResponseObjectDTO;
import com.vietqr.org.dto.terminal.*;
import com.vietqr.org.entity.TerminalEntity;
import com.vietqr.org.repository.TerminalRepository;
import com.vietqr.org.service.TerminalService;
import com.vietqr.org.utils.DateTimeUtil;
import com.vietqr.org.utils.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TerminalServiceImpl implements TerminalService {
    private static final Logger logger = Logger.getLogger(TerminalServiceImpl.class);

    @Autowired
    private TerminalRepository repo;

    @Override
    public ResponseMessageDTO insertTerminal(TerminalInsertDTO dto) {
        ResponseMessageDTO result = null;
        // TODO: Implement generate VietQR Code (when connect gRPC)
        try {
            TerminalEntity entity = new TerminalEntity(dto.getName(), dto.getAddress(), dto.getMid(), dto.getRawCode(), dto.getBankId());
            entity.setCode(generateTerminalCode(entity.getName()));
            UUID uuid = UUID.randomUUID();
            entity.setId(uuid.toString());
            entity.setNumOfStaff(0);
            entity.setTimeCreated(DateTimeUtil.getNowUTC());
            repo.save(entity);
            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("insertTerminal: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public Object getListOfTerminal(TerminalGetListDTO dto) {
        Object result = null;
        if (dto.getMid() != null && !dto.getMid().isEmpty() && dto.getUserId() != null && !dto.getUserId().isEmpty()) {
            try {
                List<ITerminalResultOfFindDTO> entities = repo.getListOfTerminal(dto.getMid());
                if (entities != null) {
                    result = new ResponseObjectDTO(Status.SUCCESS, entities);
                } else {
                    result = new ResponseMessageDTO(Status.FAILED, "E185");
                    logger.error("getTerminalsOfMerchant: List of terminal is null" + " at: " + System.currentTimeMillis());
                }
            } catch (Exception e) {
                result = new ResponseObjectDTO(Status.FAILED, "E05");
                logger.error("getTerminalsOfMerchant: " + e.getMessage() + " at: " + System.currentTimeMillis());
            }
        } else {
            result = new ResponseObjectDTO(Status.FAILED, "E46");
            logger.error("getTerminalsOfMerchant: Invalid request body" + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public Object getTerminalById(String id) {
        Object result = null;
        if (id != null && !id.isEmpty()) {
            try {
                TerminalEntity entity = repo.findTerminalById(id);
                if (entity != null) {
                    result = new ResponseObjectDTO(Status.SUCCESS, entity);
                } else {
                    result = new ResponseMessageDTO(Status.FAILED, "E186");
                    logger.error("getTerminal: Terminal is null" + " at: " + System.currentTimeMillis());
                }
            } catch (Exception e) {
                result = new ResponseObjectDTO(Status.FAILED, "E05");
                logger.error("getTerminal: " + e.getMessage() + " at: " + System.currentTimeMillis());
            }
        } else {
            result = new ResponseObjectDTO(Status.FAILED, "E46");
            logger.error("getTerminal: Invalid request body" + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public Object searchTerminals(TerminalFindDTO dto) {
        Object result = null;
        if (dto.getMid() != null && !dto.getMid().isEmpty()) {
            try {
                if (dto.getSearchTerm() != null) {
                    switch (dto.getType()) {
                        case 0: {
                            List<ITerminalResultOfFindDTO> findDTO = repo.findTerminalsByName(dto.getMid(), dto.getSearchTerm());
                            result = new ResponseObjectDTO(Status.SUCCESS, findDTO);
                            break;
                        }
                        case 1: {
                            List<ITerminalResultOfFindDTO> findDTO = repo.findTerminalsByCode(dto.getMid(), dto.getSearchTerm());
                            result = new ResponseObjectDTO(Status.SUCCESS, findDTO);
                            break;
                        }
                        case 2: {
                            List<ITerminalResultOfFindDTO> findDTO = repo.findTerminalsByRawCode(dto.getMid(), dto.getSearchTerm());
                            result = new ResponseObjectDTO(Status.SUCCESS, findDTO);
                            break;
                        }
                        case 3: {
                            List<ITerminalResultOfFindDTO> findDTO = repo.findTerminalsByAddress(dto.getMid(), dto.getSearchTerm());
                            result = new ResponseObjectDTO(Status.SUCCESS, findDTO);
                            break;
                        }
                        case 4: {
                            List<ITerminalResultOfFindDTO> findDTO = repo.findTerminalsByBankId(dto.getMid(), dto.getSearchTerm());
                            result = new ResponseObjectDTO(Status.SUCCESS, findDTO);
                            break;
                        }
                        case 5: {
                            List<ITerminalResultOfFindDTO> findDTO = repo.findTerminals(dto.getMid(), dto.getSearchTerm());
                            result = new ResponseObjectDTO(Status.SUCCESS, findDTO);
                            break;
                        }
                        default: {
                            result = new ResponseObjectDTO(Status.FAILED, "E46");
                            logger.error("searchTerminals: Invalid request body, type is invalid" + " at: " + System.currentTimeMillis());
                            break;
                        }
                    }
                } else {
                    result = new ResponseMessageDTO(Status.FAILED, "E46");
                    logger.error("searchTerminals: Invalid request body, search term is null" + " at: " + System.currentTimeMillis());
                }
            } catch (Exception e) {
                result = new ResponseObjectDTO(Status.FAILED, "E05");
                logger.error("searchTerminals: " + e.getMessage() + " at: " + System.currentTimeMillis());
            }
        } else {
            result = new ResponseObjectDTO(Status.FAILED, "E46");
            logger.error("searchTerminals: Invalid request body, mid is null or empty" + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public ResponseMessageDTO updateTerminal(TerminalUpdateDTO dto) {
        ResponseMessageDTO result = null;
        /*
        * TODO: Implement re-generate VietQR Code if update code (when connect gRPC).
        * TODO: Check user is belong to the merchant.
        * */

        try {
            TerminalEntity entity = repo.findTerminalById(dto.getId());

            if (entity.getName().equals(dto.getName().trim()) && entity.getAddress().equals(dto.getAddress().trim()) && entity.getRawCode().equals(dto.getRawCode().trim()) && entity.getBankId().equals(dto.getBankId().trim())) {
                result = new ResponseMessageDTO(Status.FAILED, "E46");
                logger.error("updateTerminal: Invalid request body at: " + System.currentTimeMillis());
            } else {
                if (!entity.getName().equals(dto.getName().trim())) {
                    entity.setName(dto.getName().trim());
                }
                if (!entity.getAddress().equals(dto.getAddress().trim())) {
                    entity.setAddress(dto.getAddress().trim());
                }
                if (!entity.getRawCode().equals(dto.getRawCode().trim())) {
                    entity.setRawCode(dto.getRawCode().trim());
                }
                if (!entity.getBankId().equals(dto.getBankId().trim())) {
                    entity.setBankId(dto.getBankId().trim());
                }
                repo.updateTerminal(entity.getId(), entity.getName(), entity.getAddress(), entity.getRawCode(), entity.getBankId());
                result = new ResponseMessageDTO(Status.SUCCESS, "");
            }

        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("insertTerminal: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public boolean isTerminalCodeExist(String code) {
        boolean result = true;
        if (code != null && !code.isEmpty()) {
            int count = repo.countTerminalByCode(code);
            if (count == 0) {
                result = false;
            }
        }
        return result;
    }

    private String generateTerminalCode(String name) {
        final byte LENGTH = 15;
        final byte CONST_LENGTH = 10;

        String result = name.toLowerCase().replaceAll("\\s+", "");
        if (result.length() > CONST_LENGTH) {
            String firstPart = result.substring(0, CONST_LENGTH);
            String randomString = StringUtil.generateRandomString(LENGTH - CONST_LENGTH);
            return firstPart + randomString;
        }
        return result + StringUtil.generateRandomString(LENGTH - result.length());
    }
}
