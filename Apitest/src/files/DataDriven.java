package files;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class DataDriven {
	
	public ArrayList<String> getData(String testCaseType, String sheetName) throws IOException {
		XSSFSheet sheet = null;
		int j = 0;
		int column = 0;
		Cell value;
		ArrayList<String> testParams = new ArrayList<String>();
		FileInputStream is = new FileInputStream("D:\\FromPC\\DiscD\\Java\\APITesting\\Apitest\\src\\recources\\TestData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(is);
		
		int sheets = workbook.getNumberOfSheets();
//		System.out.println("Number of sheets is: " +sheets);
		for(int i = 0; i<sheets; i++) {
//			System.out.println("Sheet name is: " + workbook.getSheetName(i));
			if(workbook.getSheetName(i).equalsIgnoreCase(sheetName)){
				sheet = workbook.getSheetAt(i);
			}			
		}
		Iterator<Row> rows = sheet.iterator();
		Row firstRow = rows.next();
		Iterator<Cell> cell =  firstRow.cellIterator();
		while(cell.hasNext()) {
			value = cell.next();
//			System.out.println(value.getStringCellValue());
			if(value.getStringCellValue().equalsIgnoreCase("Testcase")){
				column = j;
			}
			j++;
		}
		
		while(rows.hasNext()) {
			Row row = rows.next();
				
			if(row.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseType)) {
				cell = row.cellIterator();
				while (cell.hasNext()) {
//					System.out.println(cells.next().getStringCellValue());
					Cell c = cell.next();
					if(c.getCellType() == CellType.STRING) {
						testParams.add(c.getStringCellValue());
					}
					else {
//						NumberToTextConverter(1212);
						testParams.add(NumberToTextConverter.toText(c.getNumericCellValue()));
					}
				}
			}
			
		}
		return testParams;
	}


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		XSSFSheet sheet = null;
//		int j = 0;
//		int column = 0;
//		Cell value;
//		FileInputStream is = new FileInputStream("D:\\FromPC\\DiscD\\Java\\ExcelDataDriven\\ExcelDriven\\src\\main\\resources\\TestData.xlsx");
//		XSSFWorkbook workbook = new XSSFWorkbook(is);
		
//		int sheets = workbook.getNumberOfSheets();
//		System.out.println("Number of sheets is: " +sheets);
//		for(int i = 0; i<sheets; i++) {
//			System.out.println("Sheet name is: " + workbook.getSheetName(i));
//			if(workbook.getSheetName(i).equalsIgnoreCase("Profile")){
//				sheet = workbook.getSheetAt(i);
//			}			
//		}
//		Iterator<Row> rows = sheet.iterator();
//		Row firstRow = rows.next();
//		Iterator<Cell> cells =  firstRow.cellIterator();
//		while(cells.hasNext()) {
//			value = cells.next();
//			System.out.println(value.getStringCellValue());
//			if(value.getStringCellValue().equalsIgnoreCase("Testcase")){
//				column = j;
//			}
//			j++;
//		}
//		
//		while(rows.hasNext()) {
//			Row row = rows.next();
//			if(row.getCell(column).getStringCellValue().equalsIgnoreCase("Purchase")) {
//				cells = row.cellIterator();
//				while (cells.hasNext()) {
//					System.out.println(cells.next().getStringCellValue());
//				}
//			}
//			
//		}
		
	}
	

}
