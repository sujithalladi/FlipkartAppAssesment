package com.flipkart.qa.testCases;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
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
	
	@BeforeMethod
	public void setup() throws IOException{
		initialization();
		loginPage = new LoginPage();	
		boolean flag = loginPage.flipkartLogoTest();
		Assert.assertTrue(flag);
		String loginTitle = loginPage.validateLoginPageTitle();
		Assert.assertEquals(loginTitle, actualLoginPageTitle);
		homePage = loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
		System.out.println("Logged in and Landed in Home page succesfully");			
	}
	
	@Test(priority=1)
	public void HomepageTest() throws IOException{
		boolean flag = homePage.verifyMyaccountText();
		Assert.assertTrue(flag);
		System.out.println("MyAccount Field is displayed in Home page");
		homePage.verifyHomePageLogo();
		String homepageTitle = homePage.validateHomepageTitle();
		Assert.assertEquals(homepageTitle, actualLoginPageTitle);
	}
	
	@Test(priority=2)
	public void searchForItemToBuy() throws IOException, InterruptedException{
		
		searchPage = new SearchPage();
		searchPage = homePage.SearchForSomethingUsingText("nikon");
		System.out.println("Searched for the item Succesfully");
		boolean flag = searchPage.verifyShowingTextForSearchedText();
		Assert.assertTrue(flag);
		boolean flag1 = searchPage.verifyRelevanceLink();
		Assert.assertTrue(flag1);
		System.out.println("Verified the details in Search page");
	}
	
	@Test(priority=3)
	public void clickOnBuyNowAndVerifyDetailsOnCheckoutPage() throws IOException, InterruptedException{
		
		searchPage = new SearchPage();
		searchPage = homePage.SearchForSomethingUsingText("nikon");
		searchPage.verifyShowingTextForSearchedText();
		searchPage.verifyRelevanceLink();
		searchPage.scrollToTheWebElementAndClick();
		Set<String> WindowIds = driver.getWindowHandles();
		Iterator<String> itr = WindowIds.iterator();
		String parentId = itr.next();
		System.out.println(parentId);
		String childId = itr.next();
		System.out.println(childId);
		
		System.out.println(driver.getWindowHandle());
		driver.switchTo().window(childId);
		
		boolean flag = searchPage.verifyAddToCartButton();
		Assert.assertTrue(flag);
		
		paymentPage = searchPage.clickOnBuyNowButton();
		String cameraNameDisplayed = paymentPage.verifyingCameraName();
		Assert.assertEquals(cameraNameDisplayed, actualCameraName);
		
		String cameraPriceDisplayed = paymentPage.verifyingCameraPrice();
		Assert.assertEquals(cameraPriceDisplayed, actualCameraPrice );
		
		System.out.println("Camera details like name description and price displayed are correct");
		
		driver.close();
		driver.switchTo().window(parentId);
		
	}
	
	@AfterMethod
	public void teardown(){
		System.out.println("Test exection is done succesfully");
		driver.quit();
	}
	

}
