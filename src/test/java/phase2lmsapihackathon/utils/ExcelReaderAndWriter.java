package phase2lmsapihackathon.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelReaderAndWriter {
      
    public Map<String, String> readRequestBodyDetailsForUserModule() throws IOException {
        String path = ".\\src\\test\\resources\\testData\\requestBodyDetails.xlsx";
        File excelFile = new File(path);
        FileInputStream fileInputStream = new FileInputStream(excelFile);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet dataSheet = workbook.getSheet("Sheet1");
        Iterator<Row> rowIterator = dataSheet.rowIterator();
        Map<String, String> requestDetails = new HashMap<String, String>();

        while (rowIterator.hasNext()) {
            Row currentRow = rowIterator.next();
            if (currentRow.getRowNum() > 0) {

                requestDetails.put(currentRow.getCell(0).getStringCellValue(), currentRow.getCell(1).getStringCellValue());

            }


        }
        workbook.close();
        return requestDetails;

    }

    public static int totalRow;

    public List<Map<String, String>> getData(String excelFilePath, String sheetName) throws IOException {
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet(sheetName);
        workbook.close();
        return readSheet(sheet);
    }

    private List<Map<String, String>> readSheet(Sheet sheet) {
        Row row;
        Cell cell;
        totalRow = sheet.getLastRowNum();
        List<Map<String, String>> excelRows = new ArrayList<>();
        for (int currentRow = 1; currentRow <= totalRow; currentRow++) {
            row = sheet.getRow(currentRow);
            int totalColumn = row.getLastCellNum();
            LinkedHashMap<String, String> columnMapData = new LinkedHashMap<>();
            for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {
                cell = row.getCell(currentColumn);
                String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn).getStringCellValue();

                if (null != cell && CellType.STRING.equals(cell.getCellType()))
                    columnMapData.put(columnHeaderName, cell.getStringCellValue());
                else if (null != cell && CellType.NUMERIC.equals(cell.getCellType()))
                    columnMapData.put(columnHeaderName,  Long.valueOf((long)cell.getNumericCellValue()).toString());
                else
                    columnMapData.put(columnHeaderName, "");
            }
            excelRows.add(columnMapData);
        }
        return excelRows;
    }

    public int countRow() {
        return totalRow;
    }

   
}


