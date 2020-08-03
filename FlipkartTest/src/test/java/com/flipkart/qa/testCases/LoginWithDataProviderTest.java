package com.flipkart.qa.testCases;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.flipkart.qa.base.TestBase;
import com.flipkart.qa.pages.HomePage;
import com.flipkart.qa.pages.LoginPage;
import com.flipkart.qa.util.TestUtil;

public class LoginWithDataProviderTest extends TestBase {

	public LoginWithDataProviderTest() throws IOException {
		super();
	}

	LoginPage loginPage;
	HomePage homePage;
	public static String sheetName = "Credentials";

	@BeforeMethod
	public void setUp() throws IOException {
		initialization();
		loginPage = new LoginPage();
	}

	@Test(dataProvider = "getTestData")
	public void login(String username, String password, String searchForItem) throws IOException, InterruptedException {
		homePage = loginPage.Login(username, password);
		Thread.sleep(3000);
		homePage.SearchForSomethingUsingText(searchForItem);
	}

	@DataProvider
	public Object[][] getTestData() {
		Object[][] data = TestUtil.getTestData(sheetName);
		return data;
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
