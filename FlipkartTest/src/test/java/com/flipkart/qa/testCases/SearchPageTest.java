package com.flipkart.qa.testCases;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.flipkart.qa.ExcelLib.ReadDataFromExcel;
import com.flipkart.qa.base.TestBase;
import com.flipkart.qa.pages.HomePage;
import com.flipkart.qa.pages.LoginPage;
import com.flipkart.qa.pages.PaymentPage;
import com.flipkart.qa.pages.SearchPage;
import com.flipkart.qa.util.TestUtil;

public class SearchPageTest extends TestBase{

	LoginPage loginPage; 
	HomePage homePage;
	TestUtil testUtil;
	PaymentPage paymentPage;
	private String username;
	private String password;
	private String searchForItem;
	
	public SearchPageTest() throws IOException {
		super();
	}
	
	@BeforeTest
	public void setup() throws IOException{
		initialization();
		loginPage = new LoginPage();
		ReadDataFromExcel readData = new ReadDataFromExcel();
		readData.ExcelDataConfig();
		username = readData.getData("Credentials", 1, 0);
		password = readData.getData("Credentials", 1, 1);
		searchForItem = readData.getData("Credentials", 1, 2);
	}

	@Test(priority=1)
	public void login() throws IOException, InterruptedException {
		boolean flag = loginPage.flipkartLogoTest();
		Assert.assertTrue(flag);
		String loginTitle = loginPage.validateLoginPageTitle();
		Assert.assertEquals(loginTitle, "Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!");
		homePage = loginPage.Login(username, password);
	}
	
	@Test(priority=2)
	public void HomepageTest() throws IOException{
		boolean flag = homePage.verifyMyaccountText();
		Assert.assertTrue(flag);
		homePage.verifyHomePageLogo();
		String homepageTitle = homePage.validateHomepageTitle();
		Assert.assertEquals(homepageTitle, "Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!");
	}
	
	@Test(priority=3)
	public void searchForCameraToPurchase() throws IOException, InterruptedException{
		TestUtil.waitForWebElementToLoad(10);
		SearchPage searchPage = homePage.SearchForSomethingUsingText(searchForItem);	
		searchPage.verifyShowingTextForSearchedText();
		searchPage.verifyRelevanceLink();
		searchPage.SelectTheProdcut();
		Set<String> WindowIds = driver.getWindowHandles();
		Iterator<String> itr = WindowIds.iterator();
		String parentId = itr.next();	
		TestUtil.waitForWebElementToLoad(10);
		String childId = itr.next();
		driver.switchTo().window(childId);
		boolean addToCartFlag = searchPage.verifyAddToCartButton();
		Assert.assertTrue(addToCartFlag);
		boolean buyNowFlag = searchPage.verifyBuyNowButton();
		Assert.assertTrue(buyNowFlag);
		driver.close();
		driver.switchTo().window(parentId);
	}
	
	@AfterTest
	public void teardown(){
		driver.quit();
	}
	
}
