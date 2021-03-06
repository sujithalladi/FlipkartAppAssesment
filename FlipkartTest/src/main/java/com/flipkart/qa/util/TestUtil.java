package com.flipkart.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.compress.compressors.FileNameUtil;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.flipkart.qa.base.TestBase;

public class TestUtil extends TestBase {

	public TestUtil() throws IOException {
		super();
	}

	public static long PAGE_LOAD_TIMEOUT = 40;
	public static long IMPLICIT_WAIT = 20;
	
	public Set<String> getWindowHandles(){
		return driver.getWindowHandles();
	}
	
	public static void waitForWebElementToLoad(long waitTime){
		driver.manage().timeouts().setScriptTimeout(waitTime, TimeUnit.SECONDS);
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
	
	public static void takeScreenShotAtEndOfTest(){
	
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis()+ ".png"));
	}

	private static void FileUtils(File scrFile, File file) {
		// TODO Auto-generated method stub
		
	}
		
}
