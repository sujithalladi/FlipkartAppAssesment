package com.flipkart.qa.testCases;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
	TestUtil testUtil;
	PaymentPage paymentPage;

	public static String actualLoginPageTitle = "Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!";
	public static String actualCameraName = "Nikon D5600 (With Basic Accessory Kit) DSLR Camera Body with Single Lens: AF-P DX Nikkor 18-55 MM F/3.5-5.6G VR (16 GB SD Card)";
	public static String actualCameraPrice = "₹42,999";

	public PaymentPageTest() throws IOException {
		super();
	}

	@BeforeTest
	public void setup() throws IOException {
		initialization();
		loginPage = new LoginPage();
	}

	@DataProvider
	public Object[][] getTestData() {
		Object[][] data = TestUtil.getTestData("Credentials");
		return data;
	}

	@Test(priority = 1, dataProvider = "getTestData")
	public void loginTest(String username, String password, String searchForItem)
			throws IOException, InterruptedException {

		boolean flag = loginPage.flipkartLogoTest();
		Assert.assertTrue(flag);
		String loginTitle = loginPage.validateLoginPageTitle();
		Assert.assertEquals(loginTitle,
				"Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!");
		homePage = loginPage.Login(username, password);
	}

	@Test(priority = 2)
	public void HomepageTest() throws IOException {
		boolean flag = homePage.verifyMyaccountText();
		Assert.assertTrue(flag);
		homePage.verifyHomePageLogo();
		String homepageTitle = homePage.validateHomepageTitle();
		Assert.assertEquals(homepageTitle, actualLoginPageTitle);
	}

	@Test(priority = 3, dataProvider = "getTestData")
	public void clickOnBuyNowAndVerifyDetailsOnCheckoutPage(String username, String password, String searchForItem)
			throws IOException, InterruptedException {
		searchPage = new SearchPage();
		searchPage = homePage.SearchForSomethingUsingText("nikon");
		searchPage.verifyShowingTextForSearchedText();
		searchPage.verifyRelevanceLink();
		searchPage.scrollToTheWebElementAndClick();
		Set<String> WindowIds = driver.getWindowHandles();
		Iterator<String> itr = WindowIds.iterator();
		String parentId = itr.next();
		String childId = itr.next();
		driver.switchTo().window(childId);
		boolean addToCartflag = searchPage.verifyAddToCartButton();
		Assert.assertTrue(addToCartflag);
		boolean buyNowflag = searchPage.verifyBuyNowButton();
		Assert.assertTrue(buyNowflag);
		paymentPage = searchPage.clickOnBuyNowButton();
		String cameraNameDisplayed = paymentPage.verifyingCameraName();
		Assert.assertEquals(cameraNameDisplayed, actualCameraName);
		String cameraPriceDisplayed = paymentPage.verifyingCameraPrice();
		Assert.assertEquals(cameraPriceDisplayed, actualCameraPrice);
		driver.close();
		driver.switchTo().window(parentId);
	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}

}
