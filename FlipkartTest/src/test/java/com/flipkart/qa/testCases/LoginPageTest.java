package com.flipkart.qa.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.flipkart.qa.base.TestBase;
import com.flipkart.qa.pages.HomePage;
import com.flipkart.qa.pages.LoginPage;

public class LoginPageTest extends TestBase{

	LoginPage loginPage;
	HomePage homePage;
	
	public LoginPageTest() throws IOException {
		/*We are calling super class construtor to initialize our properties file
			As we have our properties file setupin TestBase class constructor
		*/
		super();
	}

	@BeforeMethod
	public void setUp() throws IOException{	
		initialization();
		loginPage = new LoginPage();	
	}
	
	@Test(priority=1)
	public void loginPageTitleTest(){
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!");
		System.out.println("Title is displayed correctly");
	}
	
	@Test(priority=2)
	public void flipkartLogoTest(){
		boolean flag = loginPage.flipkartLogoTest();
		Assert.assertTrue(flag);  // if flag is true assertion will pass else it will fail
		System.out.println("Logo is displayed correctly");
	}
	
	@Test(priority=3)
	public void loginTest() throws IOException{
		
		homePage = loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
		System.out.println("Logged in and Landed in Home page succesfully");
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
}
