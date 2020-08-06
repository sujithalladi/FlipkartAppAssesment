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
	
	@FindBy(name = "q")
	WebElement searchTextBox;
	
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

		if (searchTextBox.isEnabled()){
			searchTextBox.click();
			TestUtil.waitForWebElementToLoad(10);
			searchTextBox.sendKeys(str);
			searchBtn.click();
		}
		return new SearchPage();
	}
	
	
	
	
}
