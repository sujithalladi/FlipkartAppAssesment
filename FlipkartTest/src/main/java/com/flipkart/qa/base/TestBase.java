package com.flipkart.qa.base;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.flipkart.qa.ExcelLib.ReadDataFromExcel;
import com.flipkart.qa.util.TestUtil;

public class TestBase {

	public static WebDriver driver;
	
	public static void initialization() throws IOException{
		ReadDataFromExcel readData = new ReadDataFromExcel();
		readData.ExcelDataConfig();
		String browserName = readData.getData("AppDetails", 1, 0);
		String url = readData.getData("AppDetails", 1, 1);
		
		if(browserName.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", "src/main/resources/Drivers/chromedriver.exe");
			driver = new ChromeDriver();
		} 
		else if(browserName.equalsIgnoreCase("FF")) {
			System.setProperty("webdriver.gecko.driver", "src/main/resources/Drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("ie")) {
		System.setProperty("webdriver.gecko.driver", "src/main/resources/Drivers/IEDriverServer.exe");
		driver = new InternetExplorerDriver();
	}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(url);
	}
}
