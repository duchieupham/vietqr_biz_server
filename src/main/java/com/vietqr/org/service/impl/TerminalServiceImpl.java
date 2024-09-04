package com.vietqr.org.service.impl;

import com.vietqr.org.constant.Constant;
import com.vietqr.org.constant.Status;
import com.vietqr.org.constant.UniqueError;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.common.ResponseObjectDTO;
import com.vietqr.org.dto.terminal.*;
import com.vietqr.org.entity.TerminalEntity;
import com.vietqr.org.repository.TerminalRepository;
import com.vietqr.org.service.TerminalService;
import com.vietqr.org.utils.DateTimeUtil;
import com.vietqr.org.utils.ExcelGeneratorUtil;
import com.vietqr.org.utils.GeneratorUtil;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.*;

@Service
public class TerminalServiceImpl implements TerminalService {
    private static final Logger logger = Logger.getLogger(TerminalServiceImpl.class);
    private final String[] headers = Constant.TERMINAL_HEADERS;
    private final String sheetName = Constant.TERMINAL_SHEET_NAME;
    private final String sheetTitle = Constant.TERMINAL_TITLE;

    private final TerminalRepository repo;

    public TerminalServiceImpl(TerminalRepository repo) {
        this.repo = repo;
    }

    @Override
    public ResponseMessageDTO insertTerminal(TerminalInsertDTO dto) {
        /*
         * TODO: Implement generate VietQR Code (when connect gRPC)
         * */
        ResponseMessageDTO result = null;
        try {
            TerminalEntity entity = new TerminalEntity(dto.getName(), dto.getAddress(), dto.getMid(), dto.getCode(), dto.getBankId());
            // Generate id
            do {
                UUID uuid = UUID.randomUUID();
                if (!repo.existsById(uuid.toString())) {
                    entity.setId(uuid.toString());
                }
            } while (entity.getId().isEmpty());
            // Generate terminal code by name
            while (entity.getCode().isEmpty()){
                String code = GeneratorUtil.generateTerminalCode(entity.getName());
                if (repo.isTerminalCodeExists(code) == 0) {
                    entity.setCode(GeneratorUtil.generateTerminalCode(entity.getName()));
                }
            }
            // Generate public id
            do {
                String publicId = GeneratorUtil.generatePublicId("TER");
                if (repo.isTerminalPublicIdExists(publicId) == 0) {
                    entity.setPublicId(publicId);
                }
            } while (entity.getPublicId().isEmpty());

            repo.save(entity);
            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (DataIntegrityViolationException e) {
            String message = e.getMessage() == null ? "" : e.getMessage();
            if (message.contains(UniqueError.TERMINAL_CODE)) {
                result = new ResponseMessageDTO(Status.FAILED, "E205");
                logger.error("insertTerminal: " + e.getMessage() + " at: " + System.currentTimeMillis());
            } else {
                result = new ResponseMessageDTO(Status.FAILED, "E05");
                logger.error("insertTerminal: " + e.getMessage() + " at: " + System.currentTimeMillis());
            }
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("insertTerminal: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public Object getTerminalsByMid(String mid) {
        Object result = null;
        try {
            Optional<List<ITerminalResultOfFindDTO>> entities = repo.getListOfTerminal(mid.trim());
            if (entities.isPresent()) {
                result = new ResponseObjectDTO(Status.SUCCESS, entities.get());
            } else {
                result = new ResponseMessageDTO(Status.FAILED, "E185");
                logger.error("getListOfTerminal: List of terminal is null at: " + System.currentTimeMillis());
            }
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("getListOfTerminal: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public Object getTerminalById(String tid) {
        Object result = null;
        try {
            Optional<TerminalEntity> entity = repo.findTerminalById(tid.trim());
            if (entity.isPresent()) {
                result = new ResponseObjectDTO(Status.SUCCESS, entity.get());
            } else {
                result = new ResponseMessageDTO(Status.FAILED, "E186");
                logger.error("getTerminalById: Terminal is null at: " + System.currentTimeMillis());
            }
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("getTerminalById: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public Object searchTerminals(TerminalFindDTO dto) {
        /*
         * TODO: Get bank detail consist of bank_account, bank_short_name, img_id (when connect gRPC)
         *  Must be improve search
         * */
        Object result = null;
        try {
            switch (dto.getType()) {
                case 0: {
                    List<ITerminalResultOfFindDTO> findDTO = repo.findTerminalsByName(dto.getMid().trim(), dto.getSearchTerm().trim());
                    result = new ResponseObjectDTO(Status.SUCCESS, findDTO);
                    break;
                }
                case 1: {
                    List<ITerminalResultOfFindDTO> findDTO = repo.findTerminalsByCode(dto.getMid().trim(), dto.getSearchTerm().trim());
                    result = new ResponseObjectDTO(Status.SUCCESS, findDTO);
                    break;
                }
                case 2: {
                    List<ITerminalResultOfFindDTO> findDTO = repo.findTerminalsByAddress(dto.getMid().trim(), dto.getSearchTerm().trim());
                    result = new ResponseObjectDTO(Status.SUCCESS, findDTO);
                    break;
                }
                case 3: {
                    List<ITerminalResultOfFindDTO> findDTO = repo.findTerminalsByBankId(dto.getMid().trim(), dto.getSearchTerm().trim());
                    result = new ResponseObjectDTO(Status.SUCCESS, findDTO);
                    break;
                }
                case 4: {
                    List<ITerminalResultOfFindDTO> findDTO = repo.findTerminals(dto.getMid().trim(), dto.getSearchTerm().trim());
                    result = new ResponseObjectDTO(Status.SUCCESS, findDTO);
                    break;
                }
                default: {
                    result = new ResponseObjectDTO(Status.FAILED, "E46");
                    logger.error("searchTerminals: Invalid request body, type is invalid at: " + System.currentTimeMillis());
                    break;
                }
            }
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("searchTerminals: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public ResponseMessageDTO updateTerminal(String id, TerminalUpdateDTO dto) {
        /*
         * TODO: Implement re-generate VietQR Code if update code (when connect gRPC)
         * */
        ResponseMessageDTO result = null;
        try {
            Optional<TerminalEntity> terminal = repo.findTerminalById(id);
            if (terminal.isPresent()) {
                TerminalEntity entity = terminal.get();
                if (entity.getName().equals(dto.getName().trim()) && entity.getAddress().equals(dto.getAddress().trim()) && entity.getCode().equals(dto.getCode().trim()) && entity.getBankId().equals(dto.getBankId().trim())) {
                    result = new ResponseMessageDTO(Status.FAILED, "E187");
                    logger.error("updateTerminal: Invalid request body at: " + System.currentTimeMillis());
                } else {
                    if (!entity.getName().equals(dto.getName().trim()) && !dto.getName().isEmpty()) {
                        entity.setName(dto.getName());
                    }
                    if (!entity.getAddress().equals(dto.getAddress().trim()) && !dto.getAddress().isEmpty()) {
                        entity.setAddress(dto.getAddress());
                    }
                    if (!entity.getCode().equals(dto.getCode().trim()) && !dto.getCode().isEmpty()) {
                        entity.setCode(dto.getCode());
                    }
                    if (!entity.getBankId().equals(dto.getBankId().trim()) && !dto.getBankId().isEmpty()) {
                        entity.setBankId(dto.getBankId());
                    }
                    repo.updateTerminal(entity.getId(), entity.getName(), entity.getAddress(), entity.getCode(), entity.getBankId());
                    result = new ResponseMessageDTO(Status.SUCCESS, "");
                }
            } else {
                result = new ResponseMessageDTO(Status.FAILED, "E186");
                logger.error("updateTerminal: Terminal is null at: " + System.currentTimeMillis());
            }
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("updateTerminal: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public ResponseMessageDTO deleteTerminalById(String tid) {
        /*
         * TODO: Update info when update the status
         * */
        ResponseMessageDTO result = null;
        try {
            Optional<TerminalEntity> terminal = repo.findTerminalById(tid.trim());
            if (terminal.isPresent()) {
                TerminalEntity entity = terminal.get();
                if (entity.getStatus() == 1) {
                    repo.updateTerminalStatusById(tid.trim(), false, DateTimeUtil.getNowUTC());
                    result = new ResponseMessageDTO(Status.SUCCESS, "");
                } else {
                    result = new ResponseMessageDTO(Status.FAILED, "E115");
                    logger.error("deleteTerminalById: User don't have the permission to do this action at: " + System.currentTimeMillis());
                }
            } else {
                result = new ResponseMessageDTO(Status.FAILED, "E186");
                logger.error("deleteTerminalById: Terminal is null at: " + System.currentTimeMillis());
            }
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("deleteTerminalById: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public ResponseMessageDTO recoverTerminalById(String tid) {
        ResponseMessageDTO result = null;
        try {
            Optional<TerminalEntity> terminal = repo.findTerminalById(tid.trim());
            if (terminal.isPresent()) {
                TerminalEntity entity = terminal.get();
                if (entity.getStatus() == 0 && entity.getTimeUpdatedStatus() < DateTimeUtil.getTimeUTCNMonthsAgo(6)) {
                    repo.updateTerminalStatusById(tid.trim(), true, DateTimeUtil.getNowUTC());
                    result = new ResponseMessageDTO(Status.SUCCESS, "");
                } else {
                    result = new ResponseMessageDTO(Status.FAILED, "E05");
                    logger.error("recoverTerminalById: Terminal is active or was deleted more than 6 months at: " + System.currentTimeMillis());
                }
            } else {
                result = new ResponseMessageDTO(Status.FAILED, "E186");
                logger.error("recoverTerminalById: Terminal is null at: " + System.currentTimeMillis());
            }
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("recoverTerminalById: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public Object getTerminalsDeletedByMid(String mid) {
        Object result = null;
        try {
            List<ITerminalResultOfFindDTO> entities = repo.getListOfTerminalDeleted(mid.trim(), DateTimeUtil.getTimeUTCNMonthsAgo(6));
            if (entities != null) {
                result = new ResponseObjectDTO(Status.SUCCESS, entities);
            } else {
                result = new ResponseMessageDTO(Status.FAILED, "E185");
                logger.error("getListOfTerminalDeleted: List of terminal deleted is null at: " + System.currentTimeMillis());
            }
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("getListOfTerminalDeleted: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public ResponseMessageDTO exportTerminalById(HttpServletResponse httpServletResponse, String id) {
        /*
         * TODO: Update a headers
         * */
        ResponseMessageDTO result = null;
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Optional<TerminalEntity> terminal = repo.findTerminalById(id.trim());
            if (terminal.isPresent()) {
                TerminalEntity entity = terminal.get();
                XSSFSheet sheet = workbook.createSheet(sheetName);
                // header
                int rownum = 0;
                Row row = sheet.createRow(rownum++);
                CellStyle styleHeader = ExcelGeneratorUtil.getStyleHeader(workbook);
                CellStyle styleTitle = ExcelGeneratorUtil.getStyleTitle(workbook);
                CellStyle styleContent = ExcelGeneratorUtil.getStyleContent(workbook);
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headers.length - 1));
                // title
                ExcelGeneratorUtil.createCell(sheet, row, 0, sheetTitle, styleTitle);
                row = sheet.createRow(rownum++);
                for (int i = 0; i < headers.length; i++) {
                    ExcelGeneratorUtil.createCell(sheet, row, i, headers[i], styleHeader);
                }
                // content
                Row rowContent = sheet.createRow(rownum++);
                writeContent(sheet, rowContent, rownum, entity, styleContent);
                ExcelGeneratorUtil.initResponseForExport(httpServletResponse);
                ServletOutputStream outputStream = httpServletResponse.getOutputStream();
                workbook.write(outputStream);
                workbook.close();
                outputStream.close();
            } else {
                result = new ResponseMessageDTO(Status.FAILED, "E186");
                logger.error("exportTerminalById: The terminal not found at: " + System.currentTimeMillis());
            }
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("exportTerminalById: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public ResponseMessageDTO exportTerminalsByMid(HttpServletResponse httpServletResponse, String mid) {
        /*
         * TODO: Update a headers
         * */
        ResponseMessageDTO result = null;
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            List<TerminalEntity> entities = repo.findTerminalsByMid(mid.trim());

            XSSFSheet sheet = workbook.createSheet(sheetName);
            int rownum = 0;
            // header
            Row row = sheet.createRow(rownum++);
            CellStyle styleHeader = ExcelGeneratorUtil.getStyleHeader(workbook);
            CellStyle styleTitle = ExcelGeneratorUtil.getStyleTitle(workbook);
            CellStyle styleContent = ExcelGeneratorUtil.getStyleContent(workbook);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headers.length - 1));
            // title
            ExcelGeneratorUtil.createCell(sheet, row, 0, sheetTitle, styleTitle);
            row = sheet.createRow(rownum++);
            for (int i = 0; i < headers.length; i++) {
                ExcelGeneratorUtil.createCell(sheet, row, i, headers[i], styleHeader);
            }
            // content
            if (entities != null && !entities.isEmpty()) {
                for (TerminalEntity entity : entities) {
                    Row rowContent = sheet.createRow(rownum++);
                    writeContent(sheet, rowContent, rownum, entity, styleContent);
                }
            }
            ExcelGeneratorUtil.initResponseForExport(httpServletResponse);
            ServletOutputStream outputStream = httpServletResponse.getOutputStream();
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("exportTerminalsByMid: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public Object importTerminals(InputStream is) {
        Object result = null;
        try (Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheet(sheetName);
            Iterator<Row> rows = sheet.iterator();
            List<TerminalEntity> list = new ArrayList<>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header and title
                if (rowNumber == 0 || rowNumber == 1) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                TerminalEntity terminal = new TerminalEntity();
                int cellIndex = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIndex) {
                        case 1:
                            terminal.setId(currentCell.getStringCellValue());
                            break;
                        case 2:
                            terminal.setName(currentCell.getStringCellValue());
                            break;
                        case 3:
                            terminal.setAddress(currentCell.getStringCellValue());
                            break;
                        case 4:
                            terminal.setMid(currentCell.getStringCellValue());
                            break;
                        case 5:
                            terminal.setCode(currentCell.getStringCellValue());
                            break;
                        case 6:
                            terminal.setPublicId(currentCell.getStringCellValue());
                            break;
                        case 7:
                            terminal.setRefId(currentCell.getStringCellValue());
                            break;
                        case 8:
                            terminal.setBankId(currentCell.getStringCellValue());
                            break;
                        case 9:
                            terminal.setBoxDeviceId(currentCell.getStringCellValue());
                            break;
                        case 10:
                            terminal.setSub(currentCell.getBooleanCellValue());
                            break;
                        case 11:
                            terminal.setData1(currentCell.getStringCellValue());
                            break;
                        case 12:
                            terminal.setData2(currentCell.getStringCellValue());
                            break;
                        case 13:
                            terminal.setTraceTransfer(currentCell.getStringCellValue());
                            break;
                        case 14:
                            terminal.setStatus((int) currentCell.getNumericCellValue());
                            break;
                        case 15:
                            terminal.setNumOfStaff((int) currentCell.getNumericCellValue());
                            break;
                        case 16:
                            terminal.setTimeUpdatedStatus((long) currentCell.getNumericCellValue());
                            break;
                        case 17:
                            terminal.setTimeCreated((long) currentCell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIndex++;
                }
                list.add(terminal);
            }
            workbook.close();
            result = new ResponseObjectDTO(Status.SUCCESS, list);
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("importTerminals: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public ResponseMessageDTO transferTerminals(TerminalTransferDTO dto) {
        /*
         * TODO: Transfer data transaction and staff
         * */
        ResponseMessageDTO result = null;
        try {
            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("transferTerminals: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }
        return result;
    }

    private void writeContent(XSSFSheet sheet, Row rowContent, int rownum, TerminalEntity entity, CellStyle style) {
        int columnCount = 0;
        ExcelGeneratorUtil.createCell(sheet, rowContent, columnCount++, rownum - 2, style);
        ExcelGeneratorUtil.createCell(sheet, rowContent, columnCount++, entity.getId(), style);
        ExcelGeneratorUtil.createCell(sheet, rowContent, columnCount++, entity.getName(), style);
        ExcelGeneratorUtil.createCell(sheet, rowContent, columnCount++, entity.getAddress(), style);
        ExcelGeneratorUtil.createCell(sheet, rowContent, columnCount++, entity.getMid(), style);
        ExcelGeneratorUtil.createCell(sheet, rowContent, columnCount++, entity.getCode(), style);
        ExcelGeneratorUtil.createCell(sheet, rowContent, columnCount++, entity.getPublicId(), style);
        ExcelGeneratorUtil.createCell(sheet, rowContent, columnCount++, entity.getRefId(), style);
        ExcelGeneratorUtil.createCell(sheet, rowContent, columnCount++, entity.getBankId(), style);
        ExcelGeneratorUtil.createCell(sheet, rowContent, columnCount++, entity.getBoxDeviceId(), style);
        ExcelGeneratorUtil.createCell(sheet, rowContent, columnCount++, entity.getSub(), style);
        ExcelGeneratorUtil.createCell(sheet, rowContent, columnCount++, entity.getData1(), style);
        ExcelGeneratorUtil.createCell(sheet, rowContent, columnCount++, entity.getData2(), style);
        ExcelGeneratorUtil.createCell(sheet, rowContent, columnCount++, entity.getTraceTransfer(), style);
        ExcelGeneratorUtil.createCell(sheet, rowContent, columnCount++, entity.getStatus(), style);
        ExcelGeneratorUtil.createCell(sheet, rowContent, columnCount++, entity.getNumOfStaff(), style);
        ExcelGeneratorUtil.createCell(sheet, rowContent, columnCount++, entity.getTimeUpdatedStatus(), style);
        ExcelGeneratorUtil.createCell(sheet, rowContent, columnCount, entity.getTimeCreated(), style);
    }
}
