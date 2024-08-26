package com.vietqr.org.service;

import com.vietqr.org.dto.common.ResponseMessageDTO;
import com.vietqr.org.dto.common.ResponseObjectDTO;
import com.vietqr.org.dto.merchant.MerchantRequestDTO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public interface MerchantService {
    ResponseMessageDTO insertMerchant(MerchantRequestDTO merchantRequestDTO);
    Object merchantInfo(String id);
    ResponseMessageDTO updateMerchant(String id, MerchantRequestDTO merchantRequestDTO);
    ResponseMessageDTO deleteMerchant(String id);
    Object getListDeleteMerchant();
    ResponseMessageDTO recoverMerchant(String id);
}

//    private void exportMovieToExcel(Movie movie) {
//        Workbook workbook;
//        Sheet sheet;
//
//        // Kiểm tra xem file đã tồn tại chưa, nếu tồn tại thì mở file đó
//        try (FileInputStream fileIn = new FileInputStream("movie_data.xlsx")) {
//            workbook = new XSSFWorkbook(fileIn);
//            sheet = workbook.getSheetAt(0);
//        } catch (IOException e) {
//            // Nếu file không tồn tại thì tạo workbook và sheet mới
//            workbook = new XSSFWorkbook();
//            sheet = workbook.createSheet("Movie Data");
//            Row headerRow = sheet.createRow(0);
//            String[] columns = {"Title", "Headline", "Thumbnail", "Language", "Region", "Rating", "Actors"};
//            for (int i = 0; i < columns.length; i++) {
//                Cell cell = headerRow.createCell(i);
//                cell.setCellValue(columns[i]);
//            }
//        }
//
//        // Tìm hàng trống để thêm dữ liệu mới
//        int rowNum = sheet.getLastRowNum() + 1;
//        Row dataRow = sheet.createRow(rowNum);
//
//        // Điền dữ liệu vào các cột
//        dataRow.createCell(0).setCellValue(movie.getTitle());
//        dataRow.createCell(1).setCellValue(movie.getHeadline());
//        dataRow.createCell(2).setCellValue(movie.getThumbnail());
//        dataRow.createCell(3).setCellValue(movie.getLanguage());
//        dataRow.createCell(4).setCellValue(movie.getRegion());
//        dataRow.createCell(5).setCellValue(movie.getRating().toString());
//
//        // Xử lý danh sách các diễn viên
//        StringBuilder actorsList = new StringBuilder();
//        for (Actor actor : movie.getActors()) {
//            actorsList.append(actor.getLastName()).append(", ");
//        }
//        // Xóa dấu phẩy cuối cùng
//        if (!actorsList.isEmpty()) {
//            actorsList.setLength(actorsList.length() - 2);
//        }
//        dataRow.createCell(6).setCellValue(actorsList.toString());
//
//        // Ghi dữ liệu vào file Excel
//        try (FileOutputStream fileOut = new FileOutputStream("movie_data.xlsx")) {
//            workbook.write(fileOut);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                workbook.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
