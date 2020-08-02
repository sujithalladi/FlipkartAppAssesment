package com.flipkart.qa.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.flipkart.qa.base.TestBase;

public class TestUtil extends TestBase {

	public TestUtil() throws IOException {
		super();
	}

	public static long PAGE_LOAD_TIMEOUT = 30;
	public static long IMPLICIT_WAIT = 20;
	
	public static String MyTestData_Path = "‪D:/FlipkartTestData.xlsx";

	public void switchToFrame(){
		driver.switchTo().frame("mainpanel");
	}

	
	public Set<String> getWindowHandles(){
		return driver.getWindowHandles();
	}
	
	public void switchToWindow(String WindowhandleId){	
		driver.switchTo().window(WindowhandleId);
	}
	
	
	public static Object[][] getTestData(String sheetName){
		
		FileInputStream filepath = null;
		try{
			filepath = new FileInputStream("D:/FlipkartTestData.xlsx");
			Workbook book = WorkbookFactory.create(filepath);
			Sheet sheet = book.getSheet(sheetName);
			int rowCount = sheet.getLastRowNum();
			int columnCount = sheet.getRow(0).getLastCellNum();
			
			Object[][] data = new Object[rowCount][columnCount];
			
			for(int i=1;i<rowCount;i++){
				
				for(int j=0;j<columnCount;j++){
					data[i][j] = sheet.getRow(i).getCell(j).toString();
				}
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	 
}
