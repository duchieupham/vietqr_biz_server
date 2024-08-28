package com.vietqr.org.service.impl;

import com.vietqr.org.constant.Constant;
import com.vietqr.org.constant.Status;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class TerminalServiceImpl implements TerminalService {
    private static final Logger logger = Logger.getLogger(TerminalServiceImpl.class);
    private final String[] headers = Constant.TERMINAL_HEADERS;
    private final String sheetName = Constant.TERMINAL_SHEET_NAME;
    private final String sheetTitle = Constant.TERMINAL_TITLE;

    @Autowired
    private TerminalRepository repo;

    @Override
    public ResponseMessageDTO insertTerminal(TerminalInsertDTO dto) {
        ResponseMessageDTO result = null;
        /*
         * TODO: Implement generate VietQR Code (when connect gRPC)
         *  Check user is belong to the merchant
         * */
        try {
            TerminalEntity entity = new TerminalEntity(dto.getName().trim(), dto.getAddress().trim(), dto.getMid().trim(), dto.getCode().trim(), dto.getBankId().trim());
            entity.setCode(GeneratorUtil.generateTerminalCode(entity.getName()));
            UUID uuid = UUID.randomUUID();
            entity.setId(uuid.toString());
            entity.setNumOfStaff(0);
            entity.setTimeCreated(DateTimeUtil.getNowUTC());
            entity.setPublicId(GeneratorUtil.generatePublicId("TER"));
            entity.setStatus(true);
            entity.setSub(false);
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
        /*
         * TODO: Check user is belong to the merchant
         *  Handle role of user: Admin, staff of merchant or terminal
         * */
        Object result = null;
        try {
            List<ITerminalResultOfFindDTO> entities = repo.getListOfTerminal(dto.getMid().trim());
            if (entities != null) {
                result = new ResponseObjectDTO(Status.SUCCESS, entities);
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
    public Object getTerminalById(TerminalAuthDTO dto) {
        /*
         * TODO: Check user is belong to the merchant
         * */
        Object result = null;
        try {
            TerminalEntity entity = repo.findTerminalById(dto.getId().trim());
            if (entity != null) {
                result = new ResponseObjectDTO(Status.SUCCESS, entity);
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
         * TODO: Check user is belong to the merchant
         *  Get bank detail consist of bank_account, bank_short_name, img_id (when connect gRPC)
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
        ResponseMessageDTO result = null;
        /*
         * TODO: Implement re-generate VietQR Code if update code (when connect gRPC)
         *  Check user is belong to the merchant
         * */

        try {
            TerminalEntity entity = repo.findTerminalById(id);
            if (entity.getName().equals(dto.getName().trim()) && entity.getAddress().equals(dto.getAddress().trim()) && entity.getCode().equals(dto.getCode().trim()) && entity.getBankId().equals(dto.getBankId().trim())) {
                result = new ResponseMessageDTO(Status.FAILED, "E187");
                logger.error("updateTerminal: Invalid request body at: " + System.currentTimeMillis());
            } else {
                if (!entity.getName().equals(dto.getName().trim()) && !dto.getName().isEmpty()) {
                    entity.setName(dto.getName().trim());
                }
                if (!entity.getAddress().equals(dto.getAddress().trim()) && !dto.getAddress().isEmpty()) {
                    entity.setAddress(dto.getAddress().trim());
                }
                if (!entity.getCode().equals(dto.getCode().trim()) && !dto.getCode().isEmpty()) {
                    entity.setCode(dto.getCode().trim());
                }
                if (!entity.getBankId().equals(dto.getBankId().trim()) && !dto.getBankId().isEmpty()) {
                    entity.setBankId(dto.getBankId().trim());
                }
                repo.updateTerminal(entity.getId(), entity.getName(), entity.getAddress(), entity.getCode(), entity.getBankId());
                result = new ResponseMessageDTO(Status.SUCCESS, "");
            }

        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("updateTerminal: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public ResponseMessageDTO deleteTerminalById(TerminalAuthDTO dto) {
        /*
         * TODO: Update info when update the status
         * */
        ResponseMessageDTO result = null;

        try {
            if (isTerminalAuthorized(dto.getId().trim(), dto.getUserId().trim())) {
                TerminalEntity entity = repo.findTerminalById(dto.getId().trim());
                if (entity.getStatus()) {
                    repo.updateTerminalStatusById(dto.getId().trim(), false, DateTimeUtil.getNowUTC());
                    result = new ResponseMessageDTO(Status.SUCCESS, "");
                } else {
                    result = new ResponseMessageDTO(Status.FAILED, "E05");
                    logger.error("deleteTerminalById: Terminal was deleted at: " + System.currentTimeMillis());
                }
            } else {
                result = new ResponseMessageDTO(Status.FAILED, "E115");
                logger.error("deleteTerminalById: User don't have the permission to do this action at: " + System.currentTimeMillis());
            }
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("deleteTerminalById: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public ResponseMessageDTO recoverTerminalById(TerminalAuthDTO dto) {
        ResponseMessageDTO result = null;

        try {
            if (isTerminalAuthorized(dto.getId().trim(), dto.getUserId().trim())) {
                TerminalEntity entity = repo.findTerminalById(dto.getId().trim());
                if (!entity.getStatus() && entity.getTimeUpdatedStatus() < DateTimeUtil.getTimeUTCNMonthsAgo(6)) {
                    repo.updateTerminalStatusById(dto.getId().trim(), true, DateTimeUtil.getNowUTC());
                    result = new ResponseMessageDTO(Status.SUCCESS, "");
                } else {
                    result = new ResponseMessageDTO(Status.FAILED, "E05");
                    logger.error("recoverTerminalById: Terminal is active or was deleted more than 6 months at: " + System.currentTimeMillis());
                }
            } else {
                result = new ResponseMessageDTO(Status.FAILED, "E115");
                logger.error("recoverTerminalById: User don't have the permission to do this action at: " + System.currentTimeMillis());
            }

        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("recoverTerminalById: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public Object getListOfTerminalDeleted(TerminalGetListDTO dto) {
        Object result = null;
        try {
            List<ITerminalResultOfFindDTO> entities = repo.getListOfTerminalDeleted(dto.getUserId().trim(), dto.getMid().trim(), DateTimeUtil.getTimeUTCNMonthsAgo(6));
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

        try (XSSFWorkbook workbook = new XSSFWorkbook();) {
            TerminalEntity entity = repo.findTerminalById(id.trim());
            if (entity != null) {
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
        /*
         * TODO: Insert terminal without id and more
         * */
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
                            terminal.setQrBoxId(currentCell.getStringCellValue());
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
                            terminal.setStatus(currentCell.getBooleanCellValue());
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
        ExcelGeneratorUtil.createCell(sheet, rowContent, columnCount++, entity.getQrBoxId(), style);
        ExcelGeneratorUtil.createCell(sheet, rowContent, columnCount++, entity.getSub(), style);
        ExcelGeneratorUtil.createCell(sheet, rowContent, columnCount++, entity.getData1(), style);
        ExcelGeneratorUtil.createCell(sheet, rowContent, columnCount++, entity.getData2(), style);
        ExcelGeneratorUtil.createCell(sheet, rowContent, columnCount++, entity.getTraceTransfer(), style);
        ExcelGeneratorUtil.createCell(sheet, rowContent, columnCount++, entity.getStatus(), style);
        ExcelGeneratorUtil.createCell(sheet, rowContent, columnCount++, entity.getNumOfStaff(), style);
        ExcelGeneratorUtil.createCell(sheet, rowContent, columnCount++, entity.getTimeUpdatedStatus(), style);
        ExcelGeneratorUtil.createCell(sheet, rowContent, columnCount, entity.getTimeCreated(), style);
    }

    private boolean isTerminalAuthorized(String id, String userId) {
        return repo.countTerminalByAuth(id, userId) == 1;
    }

    private boolean isTerminalIdExists(String id) {
        return repo.isTerminalIdExists(id) == 1;
    }

    private boolean isTerminalCodeExists(String code) {
        return repo.isTerminalCodeExists(code) == 1;
    }

    private boolean isTerminalPublicIdExists(String publicId) {
        return repo.isTerminalPublicIdExists(publicId) == 1;
    }
}
