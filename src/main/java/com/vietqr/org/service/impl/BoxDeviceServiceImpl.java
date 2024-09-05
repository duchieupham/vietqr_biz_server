package com.vietqr.org.service.impl;

import com.vietqr.org.constant.Status;
import com.vietqr.org.constant.UniqueError;
import com.vietqr.org.dto.boxdevice.BoxDeviceInsertDTO;
import com.vietqr.org.dto.boxdevice.BoxDeviceStatusDTO;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.common.ResponseObjectDTO;
import com.vietqr.org.entity.BoxDeviceEntity;
import com.vietqr.org.repository.BoxDeviceRepository;
import com.vietqr.org.repository.TerminalRepository;
import com.vietqr.org.service.BoxDeviceService;
import com.vietqr.org.utils.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BoxDeviceServiceImpl implements BoxDeviceService {
    private static final Logger logger = Logger.getLogger(BoxDeviceServiceImpl.class);

    private final BoxDeviceRepository repo;

    private final TerminalRepository terminalRepo;

    public BoxDeviceServiceImpl(BoxDeviceRepository repo, TerminalRepository terminalRepo) {
        this.repo = repo;
        this.terminalRepo = terminalRepo;
    }

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
                entity.setDeviceCode(dto.getDeviceCode());
            }
            entity.setCertificate(dto.getCertificate());
            repo.save(entity);
            terminalRepo.updateTerminalBDIdById(dto.getTid(), entity.getId());

            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (DataIntegrityViolationException e) {
            String message = e.getMessage() == null ? "" : e.getMessage();
            if (message.contains(UniqueError.BOXDEVICE_DEVICECODE)) {
                result = new ResponseMessageDTO(Status.FAILED, "E194");
                logger.error("insertBoxDevice: ERROR: " + e.getMessage() + " at: " + System.currentTimeMillis());
            } else if (message.contains(UniqueError.BOXDEVICE_CERTIFICATE)) {
                result = new ResponseMessageDTO(Status.FAILED, "E195");
                logger.error("insertBoxDevice: ERROR: " + e.getMessage() + " at: " + System.currentTimeMillis());
            } else {
                result = new ResponseMessageDTO(Status.FAILED, "E05");
                logger.error("insertBoxDevice: ERROR: " + e.getMessage() + " at: " + System.currentTimeMillis());
            }
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("insertBoxDevice: ERROR: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public Object findBoxDeviceByMid(String mid) {
        Object result = null;
        try {
            Optional<List<BoxDeviceEntity>> entities = repo.findBoxDeviceByMid(mid.trim());
            if (entities.isPresent()) {
                result = new ResponseObjectDTO(Status.SUCCESS, entities.get());
            } else {
                result = new ResponseMessageDTO(Status.FAILED, "E05");
                logger.error("findBoxDeviceByMid: ERROR: List of Box device are not found at: " + System.currentTimeMillis());
            }
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("findBoxDeviceByMid: ERROR: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public Object findBoxDeviceByTid(String tid) {
        Object result = null;
        try {
            Optional<BoxDeviceEntity> entity = repo.findBoxDeviceByTid(tid.trim());
            if (entity.isPresent()) {
                result = new ResponseObjectDTO(Status.SUCCESS, entity.get());
            } else {
                result = new ResponseMessageDTO(Status.FAILED, "E05");
                logger.error("findBoxDeviceByTid: ERROR: List of Box device are not found at: " + System.currentTimeMillis());
            }
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("findBoxDeviceByTid: ERROR: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public ResponseMessageDTO activeBoxDeviceById(BoxDeviceStatusDTO dto) {
        ResponseMessageDTO result = null;
        try {
            Optional<BoxDeviceEntity> entity = repo.findBoxDeviceById(dto.getBoxDeviceId());
            if (entity.isPresent()) {
                if (entity.get().getStatus() == 0) {
                    repo.updateBoxDeviceStatusById(dto.getBoxDeviceId(), 1);
                    if (terminalRepo.isTerminalDisconnectBoxDevice(dto.getTid()) == 0) {
                        terminalRepo.updateTerminalBDIdById(dto.getTid(), dto.getBoxDeviceId());
                    }
                    result = new ResponseMessageDTO(Status.SUCCESS, "");
                } else {
                    result = new ResponseMessageDTO(Status.FAILED, "E197");
                    logger.error("activeBoxDeviceById: ERROR: Status of the box device is not inactive at: " + System.currentTimeMillis());
                }
            } else {
                result = new ResponseMessageDTO(Status.FAILED, "E05");
                logger.error("activeBoxDeviceById: ERROR: Box device is not found at: " + System.currentTimeMillis());
            }
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("activeBoxDeviceById: ERROR: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public ResponseMessageDTO inactiveBoxDeviceById(BoxDeviceStatusDTO dto) {
        ResponseMessageDTO result = null;
        try {
            Optional<BoxDeviceEntity> entity = repo.findBoxDeviceById(dto.getBoxDeviceId());
            if (entity.isPresent()) {
                if (entity.get().getStatus() == 1) {
                    repo.updateBoxDeviceStatusById(dto.getBoxDeviceId(), 0);
                    terminalRepo.removeTerminalBDIdById(dto.getTid());
                    result = new ResponseMessageDTO(Status.SUCCESS, "");
                } else {
                    result = new ResponseMessageDTO(Status.FAILED, "E196");
                    logger.error("inactiveBoxDeviceById: ERROR: Status of the box device is not active at: " + System.currentTimeMillis());
                }
            } else {
                result = new ResponseMessageDTO(Status.FAILED, "E05");
                logger.error("inactiveBoxDeviceById: ERROR: Box device is not found at: " + System.currentTimeMillis());
            }
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("inactiveBoxDeviceById: ERROR: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public ResponseMessageDTO deleteBoxDeviceById(BoxDeviceStatusDTO dto) {
        ResponseMessageDTO result = null;
        try {
            Optional<BoxDeviceEntity> entity = repo.findBoxDeviceById(dto.getBoxDeviceId());
            if (entity.isPresent()) {
                switch (entity.get().getStatus()) {
                    case 0:
                        repo.updateBoxDeviceStatusById(dto.getBoxDeviceId(), 2);
                        result = new ResponseMessageDTO(Status.SUCCESS, "");
                        break;
                    case 1:
                        repo.updateBoxDeviceStatusById(dto.getBoxDeviceId(), 2);
                        terminalRepo.removeTerminalBDIdById(dto.getTid());
                        result = new ResponseMessageDTO(Status.SUCCESS, "");
                        break;
                    default:
                        result = new ResponseMessageDTO(Status.FAILED, "E198");
                        logger.error("deleteBoxDeviceById: ERROR: The box device is deleted at: " + System.currentTimeMillis());
                        break;
                }
            } else {
                result = new ResponseMessageDTO(Status.FAILED, "E051");
                logger.error("deleteBoxDeviceById: ERROR: Box device is not found at: " + System.currentTimeMillis());
            }
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, e.getMessage());
            logger.error("deleteBoxDeviceById: ERROR: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }
}
