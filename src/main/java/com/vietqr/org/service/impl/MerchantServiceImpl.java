package com.vietqr.org.service.impl;

import com.vietqr.org.constant.Status;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.common.ResponseObjectDTO;
import com.vietqr.org.dto.merchant.MerchantRequestDTO;
import com.vietqr.org.dto.merchant.MerchantResponseDTO;
import com.vietqr.org.entity.MerchantEntity;
import com.vietqr.org.entity.TerminalEntity;
import com.vietqr.org.repository.MerchantRepository;
import com.vietqr.org.repository.TerminalRepository;
import com.vietqr.org.service.MerchantService;
import com.vietqr.org.utils.DateTimeUtil;
import com.vietqr.org.utils.ExcelGeneratorUtil;
import com.vietqr.org.utils.GeneratorUtil;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MerchantServiceImpl implements MerchantService {
    private final MerchantRepository merchantRepository;
    private final TerminalRepository terminalRepository;
    private static final Logger logger = Logger.getLogger(MerchantServiceImpl.class);

    public MerchantServiceImpl(MerchantRepository merchantRepository, TerminalRepository terminalRepository) {
        this.merchantRepository = merchantRepository;
        this.terminalRepository = terminalRepository;
    }

    @Override
    public ResponseMessageDTO insertMerchant(MerchantRequestDTO merchantRequestDTO) {
        ResponseMessageDTO result;
        try {
            String id = UUID.randomUUID().toString();
            MerchantEntity merchantEntity = new MerchantEntity();
            merchantEntity.setId(id);
            String shortName = merchantRequestDTO.getName();
            if (shortName == null || shortName.isEmpty()) {
                shortName = generateShortName(merchantRequestDTO.getFullName());
                merchantEntity.setName(shortName);
            } else if (merchantRepository.existsByName(shortName) == 0){
                merchantEntity.setName(merchantRequestDTO.getName());
            } else {
                return new ResponseMessageDTO(Status.FAILED, "E05");
            }
            merchantEntity.setFullName(merchantRequestDTO.getFullName());
            merchantEntity.setAddress(merchantRequestDTO.getAddress());
            int nationalIdCount = merchantRepository.existsByNationalId(merchantRequestDTO.getNationalId());
            if (nationalIdCount > 0) {
                return new ResponseMessageDTO(Status.FAILED, "E05");
            }
            merchantEntity.setNationalId(merchantRequestDTO.getNationalId());
            merchantEntity.setBusinessSector(merchantRequestDTO.getBusinessSector());
            merchantEntity.setBusinessType(merchantRequestDTO.getBusinessType());
            merchantEntity.setStatus(true);
            LocalDateTime now = LocalDateTime.now();
            long time = now.toEpochSecond(ZoneOffset.UTC);
            merchantEntity.setTimeCreate(time);
            merchantEntity.setPublishId(generateUniquePublishId());
            merchantEntity.setMaster(false);
            merchantEntity.setRefId("");
            merchantRepository.save(merchantEntity);
            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            logger.error("insertMerchant ERROR: " + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }
        return result;
    }

    @Override
    public Object merchantInfo(String id) {
        Object result;
        MerchantResponseDTO merchantResponseDTO = new MerchantResponseDTO();
        try {
            Optional<MerchantEntity> merchantEntityOptional = merchantRepository.findMerchantById(id);
            if (merchantEntityOptional.isPresent()) {
                MerchantEntity merchantEntity = merchantEntityOptional.get();
                merchantResponseDTO.setFullName(merchantEntity.getFullName());
                merchantResponseDTO.setName(merchantEntity.getName());
                merchantResponseDTO.setAddress(merchantEntity.getAddress());
                merchantResponseDTO.setBusinessSector(merchantEntity.getBusinessSector());
                merchantResponseDTO.setBusinessType(merchantEntity.getBusinessType());
                merchantResponseDTO.setServiceType(merchantEntity.getServiceType());
                result = new ResponseObjectDTO(Status.SUCCESS, merchantResponseDTO);
            } else {
                result = new ResponseMessageDTO(Status.FAILED, "E188");
            }
        } catch (Exception e) {
            logger.error("merchantInfo ERROR: " + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }
        return result;
    }

    @Override
    public ResponseMessageDTO updateMerchant(String id, MerchantRequestDTO merchantRequestDTO) {
        ResponseMessageDTO result;
        try {
            Optional<MerchantEntity> merchantEntityOptional = merchantRepository.findMerchantById(id);
            if (merchantEntityOptional.isPresent()) {
                MerchantEntity merchantEntity = merchantEntityOptional.get();
                merchantRepository.save(updateMerchantFields(merchantEntity, merchantRequestDTO));
                result = new ResponseMessageDTO(Status.SUCCESS, "");
            } else {
                result = new ResponseMessageDTO(Status.FAILED, "E188");
            }
        } catch (Exception e) {
            logger.error("updateMerchant ERROR: " + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }
        return result;
    }

    @Override
    public ResponseMessageDTO deleteMerchant(String id) {
        ResponseMessageDTO result;
        try {
            Optional<MerchantEntity> merchantEntityOptional = merchantRepository.findMerchantById(id);
            if (merchantEntityOptional.isPresent()) {
                MerchantEntity merchantEntity = merchantEntityOptional.get();
                merchantEntity.setStatus(false);
                merchantEntity.setTimeUpdatedStatus(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
                merchantRepository.save(merchantEntity);
                result = new ResponseMessageDTO(Status.SUCCESS, "");
            } else {
                result = new ResponseMessageDTO(Status.FAILED, "E188");
            }
        } catch (Exception e) {
            logger.error("deleteMerchant ERROR" + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }
        return result;
    }

    @Override
    public Object getListDeleteMerchant() {
        Object result;
        try {
            List<MerchantEntity> merchantEntityList = merchantRepository.findDeletedMerchants(DateTimeUtil.getTimeUTCNMonthsAgo(6));
            List<MerchantResponseDTO> merchantResponseDTOList = merchantEntityList.stream().map(merchantEntity -> {
                MerchantResponseDTO merchantResponseDTO = new MerchantResponseDTO();
                merchantResponseDTO.setName(merchantEntity.getName());
                merchantResponseDTO.setFullName(merchantEntity.getFullName());
                merchantResponseDTO.setAddress(merchantEntity.getAddress());
                merchantResponseDTO.setNationalId(merchantEntity.getNationalId());
                merchantResponseDTO.setBusinessSector(merchantEntity.getBusinessSector());
                merchantResponseDTO.setServiceType(merchantEntity.getServiceType());
                merchantResponseDTO.setBusinessType(merchantEntity.getBusinessType());
                return merchantResponseDTO;
            }).collect(Collectors.toList());
            result = new ResponseObjectDTO(Status.SUCCESS, merchantResponseDTOList);
        } catch (Exception e) {
            logger.error("getListDeleteMerchant ERROR: " + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }
        return result;
    }

    @Override
    public ResponseMessageDTO recoverMerchant(String id) {
        ResponseMessageDTO result;
        try {
            Optional<MerchantEntity> merchantEntityOptional = merchantRepository.findMerchantById(id);
            if (merchantEntityOptional.isPresent()) {
                MerchantEntity merchantEntity = merchantEntityOptional.get();
                merchantEntity.setTimeUpdatedStatus(0);
                merchantEntity.setStatus(true);
                merchantRepository.save(merchantEntity);
                result = new ResponseMessageDTO(Status.SUCCESS, "");
            } else {
                result = new ResponseMessageDTO(Status.FAILED, "E188");
            }
        } catch (Exception e) {
            logger.error("restoreMerchant ERROR: " + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }
        return result;
    }

    @Override
    public ResponseMessageDTO exportMerchantToExcel(HttpServletResponse httpServletResponse, String id) {
        ResponseMessageDTO result;

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Optional<MerchantEntity> merchantEntityOptional = merchantRepository.findMerchantById(id);
            if (!merchantEntityOptional.isPresent()) {
                return new ResponseMessageDTO(Status.FAILED, "E05");
            }
            MerchantEntity merchantEntity = merchantEntityOptional.get();

            XSSFSheet sheet = workbook.createSheet("Merchant");
            int rownum = 0;
            int numberOfTerminal = terminalRepository.countTerminalsByMid(id);
            Row titleRow = sheet.createRow(rownum++);
            CellStyle styleTitle = ExcelGeneratorUtil.getStyleTitle(workbook);
            ExcelGeneratorUtil.createCell(sheet, titleRow, 0, "Merchant", styleTitle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 14));

            Row headerRow = sheet.createRow(rownum++);
            CellStyle styleHeader = ExcelGeneratorUtil.getStyleHeader(workbook);
            String[] columns = {"STT", "Name", "FullName", "Address", "NationalId", "Vso", "Email", "ServiceType", "UserId", "TimeCreate", "PublishId", "QrBoxId", "BusinessSector", "BusinessType", "NumberOfTerminals"};
            for (int i = 0; i < columns.length; i++) {
                ExcelGeneratorUtil.createCell(sheet, headerRow, i, columns[i], styleHeader);
            }

            Row dataRow = sheet.createRow(rownum++);
            CellStyle styleContent = ExcelGeneratorUtil.getStyleContent(workbook);
            ExcelGeneratorUtil.createCell(sheet, dataRow, 0, 1, styleContent);
            ExcelGeneratorUtil.createCell(sheet, dataRow, 1, merchantEntity.getName(), styleContent);
            ExcelGeneratorUtil.createCell(sheet, dataRow, 2, merchantEntity.getFullName(), styleContent);
            ExcelGeneratorUtil.createCell(sheet, dataRow, 3, merchantEntity.getAddress(), styleContent);
            ExcelGeneratorUtil.createCell(sheet, dataRow, 4, merchantEntity.getNationalId(), styleContent);
            ExcelGeneratorUtil.createCell(sheet, dataRow, 5, merchantEntity.getVso(), styleContent);
            ExcelGeneratorUtil.createCell(sheet, dataRow, 6, merchantEntity.getEmail(), styleContent);
            ExcelGeneratorUtil.createCell(sheet, dataRow, 7, merchantEntity.getServiceType(), styleContent);
            ExcelGeneratorUtil.createCell(sheet, dataRow, 8, merchantEntity.getUserId(), styleContent);
            ExcelGeneratorUtil.createCell(sheet, dataRow, 9, merchantEntity.getTimeCreate(), styleContent);
            ExcelGeneratorUtil.createCell(sheet, dataRow, 10, merchantEntity.getPublishId(), styleContent);
            ExcelGeneratorUtil.createCell(sheet, dataRow, 11, merchantEntity.getQrBoxId(), styleContent);
            ExcelGeneratorUtil.createCell(sheet, dataRow, 12, merchantEntity.getBusinessSector(), styleContent);
            ExcelGeneratorUtil.createCell(sheet, dataRow, 13, merchantEntity.getBusinessType(), styleContent);
            ExcelGeneratorUtil.createCell(sheet, dataRow, 14, numberOfTerminal, styleContent);

            ExcelGeneratorUtil.initResponseForExport(httpServletResponse);
            ServletOutputStream outputStream = httpServletResponse.getOutputStream();
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            logger.error("exportMerchantToExcel ERROR: " + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }

        return result;
    }

    @Override
    public ResponseMessageDTO merchantDataTransfer(String oldMid, String newMid) {
        ResponseMessageDTO result;
        try {
            List<TerminalEntity> terminalEntities = terminalRepository.findTerminalsByMid(oldMid);
            for (TerminalEntity terminalEntity: terminalEntities) {
                TerminalEntity newTerminal = new TerminalEntity();
                UUID uuid = UUID.randomUUID();
                newTerminal.setId(uuid.toString());
                newTerminal.setName(terminalEntity.getName());
                newTerminal.setAddress(terminalEntity.getAddress());
                newTerminal.setMid(newMid);
                newTerminal.setCode(GeneratorUtil.generateTerminalCode(terminalEntity.getName()));
                newTerminal.setPublicId(GeneratorUtil.generatePublicId("TER"));
                newTerminal.setRefId(terminalEntity.getRefId());
                newTerminal.setBankId(terminalEntity.getBankId());
                newTerminal.setBoxDeviceId(terminalEntity.getBoxDeviceId());
                newTerminal.setSub(terminalEntity.getSub());
                newTerminal.setData1(terminalEntity.getData1());
                newTerminal.setData2(terminalEntity.getData2());
                newTerminal.setTraceTransfer(terminalEntity.getTraceTransfer());
                newTerminal.setStatus(terminalEntity.getStatus());
                newTerminal.setNumOfStaff(terminalEntity.getNumOfStaff());
                newTerminal.setTimeUpdatedStatus(terminalEntity.getTimeUpdatedStatus());
                newTerminal.setTimeCreated(terminalEntity.getTimeCreated());
                terminalRepository.save(newTerminal);
            }
            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            logger.error("merchantDataTransfer ERROR: " + e.getMessage() + " at: " + System.currentTimeMillis());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }
        return result;
    }

    private String generateShortName(String fullName) {
        SecureRandom random = new SecureRandom();
        String shortName;
        int exist;
        do {
            StringBuilder shortNameBuilder = new StringBuilder();
            String[] words = fullName.split(" ");
            for (String word : words) {
                if (!word.isEmpty()) {
                    int index = random.nextInt(word.length());
                    shortNameBuilder.append(word.charAt(index));
                }
            }
            shortName = shortNameBuilder.toString();
            exist = merchantRepository.existsByName(shortName);
        } while (exist == 1);
        return shortName;
    }

    private String generateUniquePublishId() {
        SecureRandom random = new SecureRandom();
        String publishId;
        int exist;
        do {
            StringBuilder stringBuilder = new StringBuilder("MER");
            for (int i = 0; i < 8; i++) {
                stringBuilder.append(random.nextInt(10));
            }
            publishId = stringBuilder.toString();
            exist = merchantRepository.existsByPublishId(publishId);
        } while (exist == 1);
        return publishId;
    }

    private MerchantEntity updateMerchantFields(MerchantEntity merchantEntity, MerchantRequestDTO merchantRequestDTO) {
        if (merchantRequestDTO.getName() != null) {
            merchantEntity.setName(merchantRequestDTO.getName());
        }
        if (merchantRequestDTO.getFullName() != null) {
            merchantEntity.setFullName(merchantRequestDTO.getFullName());
        }
        if (merchantRequestDTO.getAddress() != null) {
            merchantEntity.setAddress(merchantRequestDTO.getAddress());
        }
        if (merchantRequestDTO.getNationalId() != null) {
            merchantEntity.setNationalId(merchantRequestDTO.getNationalId());
        }
        if (merchantRequestDTO.getBusinessSector() != null) {
            merchantEntity.setBusinessSector(merchantRequestDTO.getBusinessSector());
        }
        if (merchantRequestDTO.getBusinessType() != null) {
            merchantEntity.setBusinessType(merchantRequestDTO.getBusinessType());
        }
        return merchantEntity;
    }
}