package com.vietqr.org.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;

public class ExcelGeneratorUtil {
    private final static String fontName = "Avenir Book";

    public static void createCell(XSSFSheet sheet, Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    public static CellStyle getStyleContent(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(10);
        font.setFontName(fontName);
        style.setFont(font);
        return style;
    }

    public static CellStyle getStyleHeader(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(10);
        font.setFontName(fontName);
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    public static CellStyle getStyleTitle(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(12);
        font.setFontName(fontName);
        style.setFont(font);
        font.setBold(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

    public static void initResponseForExport(HttpServletResponse response) {
        String headerName = "Content-Disposition";
        String headerValue = "attachment; filename=biz_" + GeneratorUtil.generateNameFormatDate() + ".xlsx";
        String contentType = "application/octet-stream";
        response.setContentType(contentType);
        response.setHeader(headerName, headerValue);
    }
}
