package com.vietqr.org.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vietqr.org.constant.Constant;
import com.vietqr.org.constant.Status;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.common.ResponseObjectDTO;
import com.vietqr.org.dto.merchantstaff.MerchantStaffDataDTO;
import com.vietqr.org.dto.merchantstaff.MerchantStaffImportDTO;
import com.vietqr.org.dto.merchantstaff.MerchantStaffInsertDTO;
import com.vietqr.org.entity.MerchantStaffEntity;
import com.vietqr.org.repository.MerchantRepository;
import com.vietqr.org.repository.MerchantStaffRepository;
import com.vietqr.org.repository.TerminalRepository;
import com.vietqr.org.service.MerchantStaffService;
import com.vietqr.org.utils.DateTimeUtil;
import com.vietqr.org.utils.ExcelGeneratorUtil;
import javassist.NotFoundException;
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
import java.util.*;

@Service
public class MerchantStaffServiceImpl implements MerchantStaffService {
    private static final Logger logger = Logger.getLogger(MerchantStaffServiceImpl.class);
    private final String[] headers = Constant.MERCHANT_STAFF_HEADERS;
    private final String sheetName = Constant.MERCHANT_STAFF_SHEET_NAME;
    private final String sheetTitle = Constant.MERCHANT_STAFF_TITLE;

    private final MerchantStaffRepository repo;

    private final TerminalRepository terminalRepo;

    private final MerchantRepository merchantRepo;

    public MerchantStaffServiceImpl(MerchantStaffRepository repo, TerminalRepository terminalRepo, MerchantRepository merchantRepo) {
        this.repo = repo;
        this.terminalRepo = terminalRepo;
        this.merchantRepo = merchantRepo;
    }

    @Override
    public ResponseMessageDTO insertMerchantStaffByForm(MerchantStaffInsertDTO dto) {
        ResponseMessageDTO result = null;
        try {
            MerchantStaffEntity entity = new MerchantStaffEntity(dto.getMid().trim(), dto.getTid().trim(), dto.getUserId().trim(), dto.getMerchantStaffRoleId().trim(), dto.getStaffRoleName().trim());
            do {
                UUID uuid = UUID.randomUUID();
                if (!isMerchantStaffIdExists(uuid.toString())) {
                    entity.setId(uuid.toString());
                }
            } while (entity.getId().isEmpty());
            MerchantStaffDataDTO merchantStaffDataDTO = new MerchantStaffDataDTO(dto.getPhoneNo().trim(), dto.getName().trim());
            ObjectMapper mapper = new ObjectMapper();
            String data = mapper.writeValueAsString(merchantStaffDataDTO);
            entity.setData(data);
            String permissions = mapper.writeValueAsString(dto.getPermissionGroupId());
            entity.setPermissionGroupId(permissions);
            entity.setStatus(false);
            entity.setTimeCreated(DateTimeUtil.getNowUTC());
            entity.setTimeUpdated(DateTimeUtil.getNowUTC());
            repo.save(entity);

            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("insertMerchantStaff: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public Object importMerchantStaff(InputStream is, MerchantStaffImportDTO dto) {
        /*
         * TODO: Waiting for example and handle permission
         * */
        Object result = null;

        try (Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheet(sheetName);
            Iterator<Row> rows = sheet.iterator();
            List<MerchantStaffEntity> list = new ArrayList<>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header and title
                if (rowNumber == 0 || rowNumber == 1) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                MerchantStaffEntity merchantStaff = new MerchantStaffEntity();
                MerchantStaffDataDTO merchantStaffDataDTO = new MerchantStaffDataDTO();
                int cellIndex = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIndex) {
                        case 1:
                            do {
                                UUID uuid = UUID.randomUUID();
                                if (!isMerchantStaffIdExists(uuid.toString())) {
                                    merchantStaff.setId(uuid.toString());
                                }
                            } while (merchantStaff.getId().isEmpty());
                            merchantStaff.setUserId(dto.getUserId().trim());
                            merchantStaff.setTimeCreated(DateTimeUtil.getNowUTC());
                            merchantStaff.setTimeUpdated(DateTimeUtil.getNowUTC());
                            if (dto.getType() == 0) {
                                merchantStaff.setMid(dto.getId().trim());
                                merchantStaff.setTid("");
                            } else {
                                merchantStaff.setMid("");
                                merchantStaff.setTid(dto.getId().trim());
                            }
                            break;
                        case 2:
                            merchantStaffDataDTO.setName(currentCell.getStringCellValue());
                            break;
                        case 3:
                            merchantStaffDataDTO.setPhoneNo(currentCell.getStringCellValue());
                            break;
                        case 4:
                            merchantStaff.setPermissionGroupId(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIndex++;
                }
                ObjectMapper objectMapper = new ObjectMapper();
                merchantStaff.setData(objectMapper.writeValueAsString(merchantStaffDataDTO));
                list.add(merchantStaff);
            }
            workbook.close();
            result = new ResponseObjectDTO(Status.SUCCESS, list);
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("importMerchantStaff: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public Object getExampleMerchantStaffExcel(HttpServletResponse httpServletResponse) {
        /*
         * TODO: Research ListBox and MultiSelect with Data Validation in Excel
         * */
        ResponseMessageDTO result = null;

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet(sheetName);
            int rownum = 0;
            Map<String, String[]> items = new HashMap<String, String[]>();
            items.put("Permissions", new String[]{"Create", "Update", "Delete"});
            items.put("Functions", new String[]{"Merchant", "Terminal", "Staff", "Customer"});
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
            Row rowContent = sheet.createRow(rownum);
            ExcelGeneratorUtil.createCell(sheet, rowContent, 0, rownum - 2, styleContent);

            ExcelGeneratorUtil.initResponseForExport(httpServletResponse);
            ServletOutputStream outputStream = httpServletResponse.getOutputStream();
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (Exception e) {
            result = new ResponseMessageDTO(Status.FAILED, "E05");
            logger.error("getExampleMerchantStaffExcel: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    @Override
    public boolean isAuthorized(String userId, String permissionId, String id, int type) {
        boolean result = false;
        try {
            Optional<String> permission = Optional.empty();
            switch (type) {
                case 0:
                    permission = repo.findMerchantStaffPermissionByTid(userId, id);
                    break;
                case 1:
                    if (merchantRepo.existsByUserId(userId, id) == 1) {
                        return true;
                    }
                    permission = repo.findMerchantStaffPermissionByMid(userId, id);
                    break;
                default:
                    logger.error("isAuthorized: Invalid type provided at: " + System.currentTimeMillis());
                    throw new IllegalArgumentException();
            }

            if (permission.isPresent()) {
                ObjectMapper mapper = new ObjectMapper();
                List<String> listPermission = mapper.readValue(permission.get(), new TypeReference<List<String>>() {
                });
                if (listPermission.contains(permissionId)) {
                    result = true;
                }
            } else {
                throw new NotFoundException("No permission found for user with id: " + userId + " and id: " + id);
            }
        } catch (Exception e) {
            logger.error("isAuthorized: " + e.getMessage() + " at: " + System.currentTimeMillis());
        }

        return result;
    }

    private boolean isMerchantStaffIdExists(String id) {
        return repo.isMerchantStaffIdExists(id) == 1;
    }
}
