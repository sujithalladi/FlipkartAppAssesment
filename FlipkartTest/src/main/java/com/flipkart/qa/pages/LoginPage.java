package com.flipkart.qa.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.qa.base.TestBase;
import com.flipkart.qa.util.TestUtil;

public class LoginPage extends TestBase{
	
	//Page Factory -- Object Repository;
	@FindBy(xpath = "//a[text()='Login']")
	WebElement loginButtonInHomePage;
	
	@FindBy(xpath="//div[@class='_39M2dM JB4AMj']/input[@type='text']")
	WebElement username;
	
	@FindBy(xpath="//div[@class='_39M2dM JB4AMj']/input[@type='password']")
	WebElement password;
	
	@FindBy(xpath = "//div[@class='_1avdGP']/button[@type='submit']")
	WebElement loginbtn;
	
	@FindBy(xpath ="//div[text()='My Account']")
	WebElement myAccount;
	
	@FindBy(xpath="//img[@title='Flipkart']")
	WebElement flipkartLogo;
	
	//Initialization or initializing the page objects
	public LoginPage() throws IOException {
		PageFactory.initElements(driver, this);
	}
	
	//Actions i.e., write methods for the loginpage like verifying images, titles, entering text and clicking on buttons
	public String validateLoginPageTitle(){
		return driver.getTitle();
	}
	
	public boolean flipkartLogoTest(){
		return flipkartLogo.isDisplayed();
	}
	
	public HomePage Login(String un, String pwd) throws IOException{
		TestUtil.waitForWebElementToLoad(10);
		username.sendKeys(un);
		password.sendKeys(pwd);
		loginbtn.click();
		return new HomePage();
	}
	
	public boolean validateAccountText(){
		TestUtil.waitForWebElementToLoad(10);
		return myAccount.isDisplayed();
	}
	
}
