package com.flipkart.qa.ExcelLib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadDataFromExcel {

	XSSFWorkbook wb ; 
	XSSFSheet sheet1; 
	FileOutputStream fout;
	FileInputStream fis;
	File excelfilepath;
	
	public void ExcelDataConfig() 
	{
		try {
			this.excelfilepath = new File("src/main/resources/TestData/FlipkartTestData.xlsx");
			this.fis = new FileInputStream(excelfilepath);
			wb = new XSSFWorkbook(fis);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	public String getData(String SheetName, int rowNumber, int cellNumber) throws IOException{
		sheet1 = wb.getSheet(SheetName);
		DataFormatter formatter = new DataFormatter();
		String cellText = formatter.formatCellValue(sheet1.getRow(rowNumber).getCell(cellNumber));
		return cellText;
	}
	public void WriteData(String SheetName, int rownumber, int collNumber, String cellValue) throws Exception{
		FileOutputStream fout=new FileOutputStream(excelfilepath.getAbsoluteFile());
		wb.getSheet(SheetName).getRow(rownumber).createCell(collNumber).setCellValue(cellValue); 
		wb.write(fout);
	}	
}
