package com.lms.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {
    public static int totalRow;
    /*

     */
    public List<Map<String, String>> getData(String excelFilePath, String sheetName) throws InvalidFormatException, IOException {
        try(InputStream fis = ExcelUtil.class.getClassLoader().getResourceAsStream("ExcelDataInput.xlsx")){
            XSSFWorkbook workbook = new XSSFWorkbook(fis); //WorkbookFactory.create(new File(String.valueOf(is)));
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheet(sheetName);
            workbook.close();
            return readSheet(sheet);
        }

    }
    /*

     */
    private List<Map<String, String>> readSheet(Sheet sheet) {
        Row row;
        Cell cell;
        totalRow = sheet.getLastRowNum();
        List<Map<String, String>> excelRows = new ArrayList<>();
        for (int currentRow = 1; currentRow <= totalRow; currentRow++) {
            row = sheet.getRow(currentRow);
            int totalColumn = row.getLastCellNum();
            LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<String, String>();
            for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {
                cell = row.getCell(currentColumn);
                String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn).getStringCellValue();
                columnMapdata.put(columnHeaderName, cell.getStringCellValue());
            }
            excelRows.add(columnMapdata);
        }
        return excelRows;
    }
    /*

     */
    public int countRow() {
        return totalRow;
    }
}
