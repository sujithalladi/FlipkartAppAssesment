package com.flipkart.qa.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.qa.base.TestBase;

public class LoginPage extends TestBase{
	
	//Page Factory -- Object Repository;
	
	@FindBy(xpath = "//a[text()='Login']")
	WebElement loginButtonInHomePage;
	
	@FindBy(xpath="//input[@class='_2zrpKA _1dBPDZ']")
	WebElement username;
	
	@FindBy(xpath="//input[@class='_2zrpKA _3v41xv _1dBPDZ']")
	WebElement password;
	
	@FindBy(xpath = "//button[@class='_2AkmmA _1LctnI _7UHT_c']")
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
		System.out.println("I am in Login page and");
		username.sendKeys(un);
		System.out.println("Entered the UN succesfully");
		password.sendKeys(pwd);
		System.out.println("Entered the password succesfully");
		loginbtn.click();
		System.out.println("clicked on login button");
		return new HomePage();
	}
	
	public boolean validateAccountText(){
		
		return myAccount.isDisplayed();
	}
	
	
}
