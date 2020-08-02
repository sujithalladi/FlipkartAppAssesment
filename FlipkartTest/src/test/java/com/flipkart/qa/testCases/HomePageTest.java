package com.flipkart.qa.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.flipkart.qa.base.TestBase;
import com.flipkart.qa.pages.HomePage;
import com.flipkart.qa.pages.LoginPage;
import com.flipkart.qa.pages.SearchPage;

public class HomePageTest extends TestBase{

	HomePage homepage;
	LoginPage loginPage;
	SearchPage searchPage;
	public HomePageTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@BeforeMethod
	public void setUp() throws IOException{
		initialization();
		loginPage = new LoginPage();	
		homepage = loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));	
	}
	
	//@Test(priority=1)
	public void verifyHomePageExploreLogo(){
		boolean logoflag = homepage.verifyHomePageLogo();
		Assert.assertTrue(logoflag);
		System.out.println("Explore logo is displayed");
	}
	
	//@Test(priority=2)
	public void verifyMyAccount() throws InterruptedException{
		Thread.sleep(5000);
		boolean flag = homepage.verifyMyaccountText();
		Assert.assertTrue(flag);
		System.out.println("My account is displayed");
	}
	
	@Test(priority=3)
	public void searchForSomethingToBuy() throws InterruptedException, IOException{
		Thread.sleep(5000);
		searchPage = homepage.SearchForSomethingUsingText(prop.getProperty("itemtobuy"));
		Thread.sleep(5000);
		System.out.println("Searched for Nikon");
	}
	
	@AfterMethod
	public void teardown(){
		driver.quit();
	}
	
}
