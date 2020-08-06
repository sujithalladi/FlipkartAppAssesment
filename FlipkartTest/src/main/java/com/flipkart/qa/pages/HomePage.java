package com.flipkart.qa.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.qa.base.TestBase;
import com.flipkart.qa.util.TestUtil;

public class HomePage extends TestBase{

	@FindBy(xpath="//a[contains(text(),'Explore')]")
	WebElement homePageExploreLogo ;
	
	@FindBy(xpath="//div[contains(text(),'My Account')]")
	WebElement myAccount;
	
	@FindBy(xpath = "//input[@type='text']")
	WebElement searchtextbox;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement searchBtn;
	
	public HomePage() throws IOException {
		PageFactory.initElements(driver, this);
	}
	
	public String validateHomepageTitle(){
		return driver.getTitle();
	}
	
	public boolean verifyHomePageLogo(){
		TestUtil.waitForWebElementToLoad(10);
		return homePageExploreLogo.isEnabled();
	}
	
	public boolean verifyMyaccountText(){
		return myAccount.isDisplayed();
	}
	
	public SearchPage SearchForSomethingUsingText(String str) throws IOException, InterruptedException{
		TestUtil.waitForWebElementToLoad(10);
		if (searchtextbox.isDisplayed()){
			searchtextbox.click();
			TestUtil.waitForWebElementToLoad(10);
			searchtextbox.sendKeys(str);
			searchBtn.click();
		}
		return new SearchPage();
	}
	
	
	
	
}
