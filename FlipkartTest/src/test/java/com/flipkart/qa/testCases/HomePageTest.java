package com.flipkart.qa.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.flipkart.qa.base.TestBase;
import com.flipkart.qa.pages.HomePage;
import com.flipkart.qa.pages.LoginPage;
import com.flipkart.qa.pages.SearchPage;
import com.flipkart.qa.util.TestUtil;

public class HomePageTest extends TestBase {

	HomePage homepage;
	LoginPage loginPage;
	SearchPage searchPage;

	public HomePageTest() throws IOException {
		super();
	}

	@BeforeTest
	public void setUp() throws IOException {
		initialization();
		loginPage = new LoginPage();
	}

	@DataProvider
	public Object[][] getTestData() {
		Object[][] data = TestUtil.getTestData("Credentials");
		return data;
	}

	@Test(priority = 1, dataProvider = "getTestData")
	public void login(String username, String password, String searchForItem) throws IOException, InterruptedException {
		homepage = loginPage.Login(username, password);
	}

	@Test(priority = 2)
	public void verifyHomePageExploreLogo() {
		boolean logoflag = homepage.verifyHomePageLogo();
		Assert.assertTrue(logoflag);
	}

	@Test(priority = 2)
	public void verifyMyAccount() throws InterruptedException {
		boolean flag = homepage.verifyMyaccountText();
		Assert.assertTrue(flag);
	}

	@Test(priority = 3, dataProvider = "getTestData")
	public void searchForSomethingToBuy(String username, String password, String searchForItem)
			throws InterruptedException, IOException {
		searchPage = homepage.SearchForSomethingUsingText(searchForItem);
	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}

}
