package phase2lmsapihackathon.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class XLUtility {
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public CellStyle style;   
	public File jsonFile;


    public static String getUserIdFromExcel(String sheetName, String file) throws IOException {
    	try {
			fi = new FileInputStream(file);
	    	workbook = new XSSFWorkbook(fi);
	    	sheet = workbook.getSheet(sheetName);
	    	return sheet.getRow(1).getCell(0).getStringCellValue();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
    	
    }
    public static String getRoleData(String sheetName, String fileName) throws Exception {
		Map<String, String[]> dataMap = new HashMap<String, String[]>();
		fi=new FileInputStream(fileName);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
	    DataFormatter dataFormatter = new DataFormatter();

	    int rowCount = sheet.getLastRowNum();	    
		if (rowCount == 0) {
			throw new Exception("NO DATA FOUND for dataKey: "+sheet);
		}
		return sheet.getRow(1).getCell(0).getStringCellValue();
	}
	public static Map<String, String> getData(String sheetName, String fileName, int rowNumber) throws Exception {
		Map<String, String> dataMap = new HashMap<String, String>();
		fi=new FileInputStream(fileName);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
	    DataFormatter dataFormatter = new DataFormatter();

	   // int rowCount = sheet.getPhysicalNumberOfRows(); //gets non -empty row count
	    int rowCount = sheet.getLastRowNum();
	    
		if (rowCount == 0) {
			throw new Exception("NO DATA FOUND for dataKey: "+sheet);
		}
		int columnCnt = sheet.getRow(0).getLastCellNum();
     
		for(int i=0;i<columnCnt;i++) {
			cell = sheet.getRow(rowNumber).getCell(i);
			String cellData = ""; 
			if (cell != null) {
			  	if(cell.getCellType() == CellType.NUMERIC) {
					cell.setCellType(CellType.STRING);
				}
			  	else if(cell.getCellType() == CellType.BOOLEAN) {
					cell.setCellType(CellType.STRING);
				}
				
				//cellData = cell.getStringCellValue().isEmpty()?"":cell.getStringCellValue();
				cellData = cell.getStringCellValue();
			}
			//System.out.println("value: "+sheet.getRow(0).getCell(i).getStringCellValue()+" : "+ cellData);
			dataMap.put(sheet.getRow(0).getCell(i).getStringCellValue(), cellData);
		}
		return dataMap;
	}
	public static void writeData(String sheetName, String fileName, String userId) throws IOException {
		    try {
				fo = new FileOutputStream(fileName);
			    sheet = workbook.getSheet(sheetName);
			    row = sheet.getRow(2);
			    int totRow = sheet.getLastRowNum();
			    for(int i=1;i<=totRow;i++) {
			    	sheet.getRow(i).getCell(0).setCellValue(userId);
			    }
			    workbook.write(fo);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		    finally {
		    	workbook.close();
		    	fo.close();
		    }
		    
	}
	public static Map<String, String> getDataUsingQuery(String sheetName, String fileName) throws Exception {

		Map<String, String> dataMapQuery = new HashMap<String, String>();
		String state ="street";
		String query = "";
		query = String.format("SELECT * FROM %s WHERE State='%s'", sheetName,state);
		Fillo fillo = new Fillo();
		Connection conn = null;
		Recordset record = null;
		System.out.println("query: "+query);
		System.out.println("filename: "+fileName);
		try {
			//fi=new FileInputStream(fileName);

			conn= fillo.getConnection(fileName);
			System.out.println("conn: "+conn.toString());
			record = conn.executeQuery(query);
			System.out.println("record: "+record.getCount());
			while(record.next()) {
				for(String field:record.getFieldNames()) {
					//System.out.println("FieldName: "+field);
					//System.out.println("Field Value: "+record.getField(field));
					dataMapQuery.put(field, record.getField(field));
				}
			}
		}catch(FilloException e) {
			e.printStackTrace();
			throw new Exception("Test Data Not Found.....");
		}
		//conn.close();
		return dataMapQuery;
	}


	public static int getPositiveScenarioCount(String sheetName, String file) throws IOException {
		fi=new FileInputStream(file);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		return rowCount;
	}
	public static int getTotalScenarioCount(String sheetName,String fileName) throws IOException {
		try {
			fi = new FileInputStream(fileName);
			workbook = new XSSFWorkbook(fi);
			sheet = workbook.getSheet(sheetName);
			return sheet.getLastRowNum();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	

}