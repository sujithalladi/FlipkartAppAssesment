package com.flipkart.qa.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.DataFormatter;
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
	
	public Set<String> getWindowHandles(){
		return driver.getWindowHandles();
	}
	
	public static void waitForWebElementToLoad(long waittime){
		driver.manage().timeouts().setScriptTimeout(waittime, TimeUnit.SECONDS);
	}
	
	public void switchToWindow(String WindowhandleId){	
		driver.switchTo().window(WindowhandleId);
	}
	
	public static Object[][] getTestData(String sheetName){
		
		FileInputStream filepath = null;
		Object[][] data=null;
		try{
			filepath = new FileInputStream("src/main/resources/TestData/FlipkartTestData.xlsx");
			Workbook book = WorkbookFactory.create(filepath);
			Sheet sheet = book.getSheet(sheetName);
			int rowCount = sheet.getLastRowNum();
			int columnCount = sheet.getRow(0).getLastCellNum();
			
			data = new Object[rowCount][columnCount];
			 
			for(int i=0;i<rowCount;i++){
				for(int j=0;j<columnCount;j++){
					DataFormatter dataFormatter = new DataFormatter();
					String value = dataFormatter.formatCellValue(sheet.getRow(i+1).getCell(j));
					data[i][j] = value;
				}
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return data;
		
	}
		
}
