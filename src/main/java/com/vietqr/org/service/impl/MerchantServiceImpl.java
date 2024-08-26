package com.vietqr.org.service.impl;

import com.vietqr.org.constant.Status;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.common.ResponseObjectDTO;
import com.vietqr.org.dto.merchant.MerchantRequestDTO;
import com.vietqr.org.dto.merchant.MerchantResponseDTO;
import com.vietqr.org.entity.MerchantEntity;
import com.vietqr.org.repository.MerchantRepository;
import com.vietqr.org.service.MerchantService;
import com.vietqr.org.utils.DateTimeUtil;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
    private static final Logger logger = Logger.getLogger(MerchantServiceImpl.class);

    public MerchantServiceImpl(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
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
            logger.error("insertMerchant ERROR: " + e.getMessage());
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
            }
            result = new ResponseObjectDTO(Status.SUCCESS, merchantResponseDTO);
        } catch (Exception e) {
            logger.error("merchantInfo ERROR: " + e.getMessage());
            result = new ResponseObjectDTO(Status.FAILED, null);
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
            }
            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            logger.error("updateMerchant ERROR: " + e.getMessage());
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
            }
            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            logger.error("deleteMerchant ERROR" + e.getMessage());
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
            logger.error("getListDeleteMerchant ERROR: " + e.getMessage());
            result = new ResponseObjectDTO(Status.FAILED, null);
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
            }
            result = new ResponseMessageDTO(Status.SUCCESS, "");
        } catch (Exception e) {
            logger.error("restoreMerchant ERROR: " + e.getMessage());
            result = new ResponseMessageDTO(Status.FAILED, "E05");
        }
        return result;
    }

    @Override
    public ResponseMessageDTO exportMerchantToExcel(String id) {
        ResponseMessageDTO result;
        Workbook workbook;
        Sheet sheet;
        MerchantEntity merchantEntity;
        Optional<MerchantEntity> merchantEntityOptional = merchantRepository.findMerchantById(id);
        if (merchantEntityOptional.isPresent()) {
            merchantEntity = merchantEntityOptional.get();
        } else {
            return new ResponseMessageDTO("FAILED", "E05");
        }
        try (FileInputStream fileInputStream = new FileInputStream("merchant_data.xlsx")) {
            workbook = new XSSFWorkbook(fileInputStream);
            sheet = workbook.getSheetAt(0);
        } catch (IOException e) {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Merchant Data");

            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            Row headerRow = sheet.createRow(1);
            String[] columns = {"STT", "Name", "FullName", "Address", "NationalId", "Vso", "Email", "ServiceType", "UserId", "TimeCreate", "PublishId", "QrBoxId", "BusinessSector", "BusinessType"};
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(cellStyle);
            }

            Row titleRow = sheet.createRow(0);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("Merchant");
            titleCell.setCellStyle(cellStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columns.length));
        }

        int rowNum = sheet.getLastRowNum() + 1;
        Row dataRow = sheet.createRow(rowNum);
        dataRow.createCell(0).setCellValue(rowNum - 1);
        dataRow.createCell(1).setCellValue(merchantEntity.getName());
        dataRow.createCell(2).setCellValue(merchantEntity.getFullName());
        dataRow.createCell(3).setCellValue(merchantEntity.getAddress());
        dataRow.createCell(4).setCellValue(merchantEntity.getNationalId());
        dataRow.createCell(5).setCellValue(merchantEntity.getVso());
        dataRow.createCell(6).setCellValue(merchantEntity.getEmail());
        dataRow.createCell(7).setCellValue(merchantEntity.getServiceType());
        dataRow.createCell(8).setCellValue(merchantEntity.getUserId());
        dataRow.createCell(9).setCellValue(merchantEntity.getTimeCreate());
        dataRow.createCell(10).setCellValue(merchantEntity.getPublishId());
        dataRow.createCell(11).setCellValue(merchantEntity.getQrBoxId());
        dataRow.createCell(12).setCellValue(merchantEntity.getBusinessSector());
        dataRow.createCell(13).setCellValue(merchantEntity.getBusinessType());

        try (FileOutputStream fileOutputStream = new FileOutputStream("merchant_data.xlsx")) {
            workbook.write(fileOutputStream);
            result = new ResponseMessageDTO("SUCCESS", "");
        } catch (IOException e) {
            logger.error("exportMerchantToExcel ERROR: " + e.getMessage());
            result = new ResponseMessageDTO("FAILED", "E05");
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                logger.error("exportMerchantToExcel ERROR: " + e.getMessage());
            }
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