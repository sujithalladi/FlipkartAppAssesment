package com.flipkart.qa.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.flipkart.qa.ExcelLib.ReadDataFromExcel;
import com.flipkart.qa.base.TestBase;
import com.flipkart.qa.pages.HomePage;
import com.flipkart.qa.pages.LoginPage;

public class LoginPageTest extends TestBase{

	LoginPage loginPage;
	HomePage homePage;
	private String username;
	private String password;
	
	public LoginPageTest() throws IOException {
		super();
	}

	@BeforeMethod
	public void setUp() throws IOException{	
		initialization();
		loginPage = new LoginPage();
		ReadDataFromExcel readData = new ReadDataFromExcel();
		readData.ExcelDataConfig();
		username = readData.getData("Credentials", 1, 0);
		password = readData.getData("Credentials", 1, 1);
	}
	
	@Test(priority=1)
	public void loginPageTitleTest(){
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!");
	}
	
	@Test(priority=2)
	public void flipkartLogoTest(){
		boolean flag = loginPage.flipkartLogoTest();
		Assert.assertTrue(flag);  
	}	
	
	@Test(priority=3)
	public void loginTest() throws IOException, InterruptedException {
		homePage = loginPage.Login(username, password);
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
}
