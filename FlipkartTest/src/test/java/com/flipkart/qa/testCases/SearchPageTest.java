package com.flipkart.qa.testCases;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.flipkart.qa.base.TestBase;
import com.flipkart.qa.pages.HomePage;
import com.flipkart.qa.pages.LoginPage;
import com.flipkart.qa.pages.PaymentPage;
import com.flipkart.qa.pages.SearchPage;
import com.flipkart.qa.util.TestUtil;

public class SearchPageTest extends TestBase{

	LoginPage loginPage; 
	HomePage homePage;
	SearchPage searchPage;
	TestUtil testUtil;
	PaymentPage paymentPage;
	
	public SearchPageTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@BeforeTest
	public void setup() throws IOException{
		initialization();
		loginPage = new LoginPage();	
		boolean flag = loginPage.flipkartLogoTest();
		Assert.assertTrue(flag);
		String loginTitle = loginPage.validateLoginPageTitle();
		Assert.assertEquals(loginTitle, "Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!");
		homePage = loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
		System.out.println("Logged in and Landed in Home page succesfully");			
	}

	
	@Test(priority=1)
	public void HomepageTest() throws IOException{
		boolean flag = homePage.verifyMyaccountText();
		Assert.assertTrue(flag);
		System.out.println("MyAccount is displayed in Home page");
		homePage.verifyHomePageLogo();
		String homepageTitle = homePage.validateHomepageTitle();
		Assert.assertEquals(homepageTitle, "Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!");
	}
	
	@Test(priority=2)
	public void searchForCameraToPurchase() throws IOException, InterruptedException{
		
		searchPage = new SearchPage();
		searchPage = homePage.SearchForSomethingUsingText("Nikon");
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
		
		driver.close();
		driver.switchTo().window(parentId);
		
	}
	
	@AfterTest
	public void teardown(){
		System.out.println("Test exection is done");
		//driver.quit();
	}
	
}
