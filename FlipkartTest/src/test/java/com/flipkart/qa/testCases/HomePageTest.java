package com.flipkart.qa.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.flipkart.qa.ExcelLib.ReadDataFromExcel;
import com.flipkart.qa.base.TestBase;
import com.flipkart.qa.pages.HomePage;
import com.flipkart.qa.pages.LoginPage;
import com.flipkart.qa.pages.SearchPage;

public class HomePageTest extends TestBase {

	HomePage homepage;
	LoginPage loginPage;
	SearchPage searchPage;
	private String username;
	private String password;
	private String searchForItem;
	
	public HomePageTest() throws IOException {
		super();
	}

	@BeforeTest
	public void setUp() throws IOException {
		initialization();
		loginPage = new LoginPage();
		ReadDataFromExcel readData = new ReadDataFromExcel();
		readData.ExcelDataConfig();
		username = readData.getData("Credentials", 1, 0);
		password = readData.getData("Credentials", 1, 1);
		searchForItem = readData.getData("Credentials", 1, 2);
	}

	@Test(priority = 1)
	public void login() throws IOException, InterruptedException {
		homepage = loginPage.Login(username, password);
	}

	@Test(priority = 2)
	public void verifyHomePageExploreLogo() {
		boolean logoflag = homepage.verifyHomePageLogo();
		Assert.assertTrue(logoflag);
	}

	@Test(priority = 3)
	public void verifyMyAccount() throws InterruptedException {
		boolean flag = homepage.verifyMyaccountText();
		
		Assert.assertTrue(flag);
	}

	@Test(priority = 4)
	public void searchForSomethingToBuy()
			throws InterruptedException, IOException {
		searchPage = homepage.SearchForSomethingUsingText(searchForItem);
	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}

}
