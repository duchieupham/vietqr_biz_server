package com.vietqr.org.service.impl;

import com.vietqr.org.constant.Status;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.common.ResponseObjectDTO;
import com.vietqr.org.dto.terminalinventory.ITerminalInventoryDTO;
import com.vietqr.org.dto.terminalinventory.TerminalInventoryInsertDTO;
import com.vietqr.org.dto.terminalinventory.TerminalInventoryUpdateExpDTO;
import com.vietqr.org.dto.terminalinventory.TerminalInventoryUpdateQuantityDTO;
import com.vietqr.org.entity.TerminalInventoryEntity;
import com.vietqr.org.repository.TerminalInventoryRepository;
import com.vietqr.org.service.TerminalInventoryService;
import com.vietqr.org.utils.DateTimeUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TerminalInventoryServiceImpl implements TerminalInventoryService {
    private static final Logger logger = Logger.getLogger(TerminalInventoryServiceImpl.class);
    private final String LOG_ERROR = "Failed at TerminalInventoryServiceImpl: ";
    private final TerminalInventoryRepository repo;

    public TerminalInventoryServiceImpl(TerminalInventoryRepository repo) {
        this.repo = repo;
    }

    @Override
    public ResponseMessageDTO insertTerminalInventory(TerminalInventoryInsertDTO dto) {
        ResponseMessageDTO result = null;
        try {
            if (dto.getExp() < dto.getMfg()) {
                TerminalInventoryEntity entity = new TerminalInventoryEntity(
                        dto.getProductId(),
                        dto.getTid(),
                        dto.getQuantity(),
                        dto.getExp(),
                        dto.getMfg()
                );
                do {
                    UUID uuid = UUID.randomUUID();
                    if (repo.existsTerminalInventoryById(uuid.toString()) != 1) {
                        entity.setId(uuid.toString());
                    }
                } while (entity.getId().isEmpty());
                entity.setTimeCreated(DateTimeUtil.getNowUTC());
                entity.setTimeUpdated(DateTimeUtil.getNowUTC());
                repo.save(entity);
                result = new ResponseMessageDTO(Status.SUCCESS, "");
            } else {
                result = new ResponseMessageDTO(Status.FAILED, "E46");
            }
        } catch (Exception e) {
            logger.error(LOG_ERROR + "insertTerminalInventory: " + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }

        return result;
    }

    @Override
    public ResponseMessageDTO updateQuantityTerminalInventoryById(TerminalInventoryUpdateQuantityDTO dto) {
        ResponseMessageDTO result = null;
        try {
            repo.updateQuantityTerminalInventoryById(dto.getId(), dto.getQuantity(), DateTimeUtil.getNowUTC());
            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            logger.error(LOG_ERROR + "updateQuantityTerminalInventoryById: " + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }

        return result;
    }

    @Override
    public Object findTerminalInventoryById(String terminalInventoryId) {
        Object result = null;
        try {
            ITerminalInventoryDTO entity = repo.findTerminalInventoryById(terminalInventoryId);
            result = new ResponseObjectDTO(Status.SUCCESS, entity);
        } catch (Exception e) {
            logger.error(LOG_ERROR + "findTerminalInventoryById: " + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }

        return result;
    }

    @Override
    public Object findTerminalInventoryByTid(String tid) {
        Object result = null;
        try {
            List<ITerminalInventoryDTO> entity = repo.findTerminalInventoryByTid(tid);
            result = new ResponseObjectDTO(Status.SUCCESS, entity);
        } catch (Exception e) {
            logger.error(LOG_ERROR + "findTerminalInventoryByTid: " + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }

        return result;
    }

    @Override
    public ResponseMessageDTO updateExpTerminalInventoryById(TerminalInventoryUpdateExpDTO dto) {
        ResponseMessageDTO result = null;
        try {
            repo.updateExpTerminalInventoryById(dto.getId(), dto.getExp(), dto.getMfg(), DateTimeUtil.getNowUTC());
            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            logger.error(LOG_ERROR + "updateExpTerminalInventoryById: " + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }

        return result;
    }
}
