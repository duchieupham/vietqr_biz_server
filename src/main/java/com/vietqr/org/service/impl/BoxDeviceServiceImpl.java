package com.vietqr.org.service.impl;

import com.vietqr.org.constant.Status;
import com.vietqr.org.constant.UniqueError;
import com.vietqr.org.dto.boxdevice.BoxDeviceInsertDTO;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.entity.BoxDeviceEntity;
import com.vietqr.org.repository.BoxDeviceRepository;
import com.vietqr.org.repository.TerminalRepository;
import com.vietqr.org.service.BoxDeviceService;
import com.vietqr.org.utils.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BoxDeviceServiceImpl implements BoxDeviceService {
    private static final Logger logger = Logger.getLogger(BoxDeviceServiceImpl.class);

    @Autowired
    private BoxDeviceRepository repo;

    @Autowired
    private TerminalRepository terminalRepo;

    @Override
    public ResponseMessageDTO insertBoxDevice(BoxDeviceInsertDTO dto) {
        ResponseMessageDTO result = null;
        try {
            BoxDeviceEntity entity = new BoxDeviceEntity();
            do {
                UUID uuid = UUID.randomUUID();
                if (!repo.existsById(uuid.toString())) {
                    entity.setId(uuid.toString());
                }
            } while (entity.getId().isEmpty());
            if (dto.getDeviceCode() == null) {
                dto.setDeviceCode(StringUtil.generateRandomString(15));
            } else {
                entity.setDeviceCode(dto.getDeviceCode().trim());
            }
            entity.setCertificate(dto.getCertificate().trim());
            repo.save(entity);
            terminalRepo.updateTerminalBoxDeviceById(entity.getId(), dto.getDeviceCode());

            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (DataIntegrityViolationException e) {
            String message = e.getMessage() == null ? "" : e.getMessage();
            if (message.contains(UniqueError.BOXDEVICE_DEVICECODE)) {
                result = new ResponseMessageDTO(Status.FAILED, "E194");
                logger.error("insertBoxDevice: " + e.getMessage() + " at: " + System.currentTimeMillis());
            } else if (message.contains(UniqueError.BOXDEVICE_CERTIFICATE)) {
                result = new ResponseMessageDTO(Status.FAILED, "E195");
                logger.error("insertBoxDevice: " + e.getMessage() + " at: " + System.currentTimeMillis());
            }
            else {
                result = new ResponseMessageDTO(Status.FAILED, "E05");
                logger.error("insertBoxDevice: " + e.getMessage() + " at: " + System.currentTimeMillis());
            }
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("insertBoxDevice: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }
}
