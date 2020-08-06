package com.flipkart.qa.testCases;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import com.flipkart.qa.model.ProductDetails;
import org.testng.Assert;
import org.testng.annotations.*;

import com.flipkart.qa.ExcelLib.ReadDataFromExcel;
import com.flipkart.qa.base.TestBase;
import com.flipkart.qa.pages.HomePage;
import com.flipkart.qa.pages.LoginPage;
import com.flipkart.qa.pages.PaymentPage;
import com.flipkart.qa.pages.SearchPage;
import com.flipkart.qa.util.TestUtil;

public class PaymentPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	SearchPage searchPage;
	PaymentPage paymentPage;
	private String username;
	private String password;
	private String searchForItem;

	public PaymentPageTest() {
		super();
	}

	@BeforeTest
	public void setup() throws IOException {
		initialization();
		loginPage = new LoginPage();
		ReadDataFromExcel readData = new ReadDataFromExcel();
		readData.ExcelDataConfig();
		username = readData.getData("Credentials", 1, 0);
		password = readData.getData("Credentials", 1, 1);
		searchForItem = readData.getData("Credentials", 1, 2);
		homePage = loginPage.Login(username, password);
	}

	@Test(priority = 1)
	public void loginTest() throws IOException, InterruptedException {
		boolean flag = loginPage.flipkartLogoTest();
		Assert.assertTrue(flag);
		String loginTitle = loginPage.validateLoginPageTitle();
		Assert.assertEquals(loginTitle,
				"Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!");

	}

	@Test(priority = 2)
	public void HomepageTest() throws IOException {
		boolean flag = homePage.verifyMyaccountText();
		Assert.assertTrue(flag);
		homePage.verifyHomePageLogo();
	}

	@Test(priority = 3)
	public void clickOnBuyNowAndVerifyDetailsOnCheckoutPage()
			throws IOException, InterruptedException {
		TestUtil.waitForWebElementToLoad(10);
		SearchPage searchPage = homePage.SearchForSomethingUsingText(searchForItem);
		searchPage.verifyShowingTextForSearchedText();
		searchPage.verifyRelevanceLink();
		searchPage.SelectTheProdcut();
		Set<String> WindowIds = driver.getWindowHandles();
		Iterator<String> itr = WindowIds.iterator();
		String parentId = itr.next();
		TestUtil.waitForWebElementToLoad(20);
		String childId = itr.next();
		TestUtil.waitForWebElementToLoad(20);
		driver.switchTo().window(childId);
		boolean addToCartFlag = searchPage.verifyAddToCartButton();
		Assert.assertTrue(addToCartFlag);
		boolean buyNowFlag = searchPage.verifyBuyNowButton();
		Assert.assertTrue(buyNowFlag);
		ProductDetails selectedProductDetails=searchPage.captureProductDetails();
		paymentPage = searchPage.clickOnBuyNowButton();
		ProductDetails purchasedProductDetails=paymentPage.captureProductDetails();
		Assert.assertNotNull(selectedProductDetails);
		Assert.assertNotNull(purchasedProductDetails);
		Assert.assertTrue(selectedProductDetails.getName().contains(purchasedProductDetails.getName()));
		Assert.assertEquals(selectedProductDetails.getPrice(), purchasedProductDetails.getPrice());
		if(selectedProductDetails.getSeller().length()==purchasedProductDetails.getSeller().length())
			Assert.assertEquals(selectedProductDetails.getSeller(), purchasedProductDetails.getSeller());
		else
			Assert.assertTrue(selectedProductDetails.getSeller().contains(purchasedProductDetails.getSeller()));
		driver.close();
		driver.switchTo().window(parentId);
	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}

}
