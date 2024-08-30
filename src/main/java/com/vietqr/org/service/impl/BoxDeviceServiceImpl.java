package com.vietqr.org.service.impl;

import com.vietqr.org.constant.Status;
import com.vietqr.org.constant.UniqueError;
import com.vietqr.org.dto.boxdevice.BoxDeviceFindMidDTO;
import com.vietqr.org.dto.boxdevice.BoxDeviceStatusDTO;
import com.vietqr.org.dto.boxdevice.BoxDeviceInsertDTO;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.common.ResponseObjectDTO;
import com.vietqr.org.entity.BoxDeviceEntity;
import com.vietqr.org.repository.BoxDeviceRepository;
import com.vietqr.org.repository.TerminalRepository;
import com.vietqr.org.service.BoxDeviceService;
import com.vietqr.org.utils.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
                entity.setDeviceCode(StringUtil.generateRandomString(15));
            } else {
                entity.setDeviceCode(dto.getDeviceCode().trim());
            }
            entity.setCertificate(dto.getCertificate().trim());
            repo.save(entity);
            terminalRepo.updateTerminalBDIdById(dto.getTid().trim(), entity.getId());

            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (DataIntegrityViolationException e) {
            String message = e.getMessage() == null ? "" : e.getMessage();
            if (message.contains(UniqueError.BOXDEVICE_DEVICECODE)) {
                result = new ResponseMessageDTO(Status.FAILED, "E194");
                logger.error("insertBoxDevice: " + e.getMessage() + " at: " + System.currentTimeMillis());
            } else if (message.contains(UniqueError.BOXDEVICE_CERTIFICATE)) {
                result = new ResponseMessageDTO(Status.FAILED, "E195");
                logger.error("insertBoxDevice: " + e.getMessage() + " at: " + System.currentTimeMillis());
            } else {
                result = new ResponseMessageDTO(Status.FAILED, "E05");
                logger.error("insertBoxDevice: " + e.getMessage() + " at: " + System.currentTimeMillis());
            }
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("insertBoxDevice: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public Object findBoxDeviceByMid(BoxDeviceFindMidDTO dto) {
        Object result = null;
        try {
            Optional<List<BoxDeviceEntity>> entities = repo.findBoxDeviceByMid(dto.getMid().trim());
            if (entities.isPresent()) {
                result = new ResponseObjectDTO(Status.SUCCESS, entities.get());
            } else {
                result = new ResponseMessageDTO(Status.FAILED, "E05");
                logger.error("findBoxDeviceByMid: List of Box device not found at: " + System.currentTimeMillis());
            }
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("findBoxDeviceByMid: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public ResponseMessageDTO activeBoxDeviceById(BoxDeviceStatusDTO dto) {
        ResponseMessageDTO result = null;
        try {
            Optional<BoxDeviceEntity> entity = repo.findBoxDeviceById(dto.getBoxDeviceId().trim());
            if (entity.isPresent()) {
                if (entity.get().getStatus() == 0) {
                    repo.updateBoxDeviceStatusById(dto.getBoxDeviceId().trim(), 1);
                    if (terminalRepo.isTerminalDisconnectBoxDevice(dto.getTid().trim()) == 0) {
                        terminalRepo.updateTerminalBDIdById(dto.getTid().trim(), dto.getBoxDeviceId().trim());
                    }
                    result = new ResponseMessageDTO(Status.SUCCESS, "");
                } else {
                    result = new ResponseMessageDTO(Status.FAILED, "E197");
                    logger.error("activeBoxDeviceById: Status of the box device is not inactive at: " + System.currentTimeMillis());
                }
            } else {
                result = new ResponseMessageDTO(Status.FAILED, "E05");
                logger.error("activeBoxDeviceById: Box device not found at: " + System.currentTimeMillis());
            }
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("activeBoxDeviceById: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public ResponseMessageDTO inactiveBoxDeviceById(BoxDeviceStatusDTO dto) {
        ResponseMessageDTO result = null;
        try {
            Optional<BoxDeviceEntity> entity = repo.findBoxDeviceById(dto.getBoxDeviceId().trim());
            if (entity.isPresent()) {
                if (entity.get().getStatus() == 1) {
                    repo.updateBoxDeviceStatusById(dto.getBoxDeviceId().trim(), 0);
                    terminalRepo.removeTerminalBDIdById(dto.getTid().trim());
                    result = new ResponseMessageDTO(Status.SUCCESS, "");
                } else {
                    result = new ResponseMessageDTO(Status.FAILED, "E196");
                    logger.error("inactiveBoxDeviceById: Status of the box device is not active at: " + System.currentTimeMillis());
                }
            } else {
                result = new ResponseMessageDTO(Status.FAILED, "E05");
                logger.error("inactiveBoxDeviceById: Box device not found at: " + System.currentTimeMillis());
            }
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("inactiveBoxDeviceById: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public ResponseMessageDTO deleteBoxDeviceById(BoxDeviceStatusDTO dto) {
        ResponseMessageDTO result = null;
        try {
            Optional<BoxDeviceEntity> entity = repo.findBoxDeviceById(dto.getBoxDeviceId().trim());
            if (entity.isPresent()) {
                switch (entity.get().getStatus()) {
                    case 0:
                        repo.updateBoxDeviceStatusById(dto.getBoxDeviceId().trim(), 2);
                        result = new ResponseMessageDTO(Status.SUCCESS, "");
                        break;
                    case 1:
                        repo.updateBoxDeviceStatusById(dto.getBoxDeviceId().trim(), 2);
                        terminalRepo.removeTerminalBDIdById(dto.getTid().trim());
                        result = new ResponseMessageDTO(Status.SUCCESS, "");
                        break;
                    default:
                        result = new ResponseMessageDTO(Status.FAILED, "E198");
                        logger.error("deleteBoxDeviceById: Status of the box device is deleted at: " + System.currentTimeMillis());
                        break;
                }
            } else {
                result = new ResponseMessageDTO(Status.FAILED, "E051");
                logger.error("deleteBoxDeviceById: Box device not found at: " + System.currentTimeMillis());
            }
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED,  e.getMessage());
            logger.error("deleteBoxDeviceById: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }
}
