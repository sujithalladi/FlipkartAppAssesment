package com.flipkart.qa.pages;
import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.qa.base.TestBase;

public class PaymentPage extends TestBase{

	SearchPage searchPage;
	public PaymentPage() throws IOException {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='_325-ji']")   
	WebElement cameraFullNameText;  
	
	@FindBy(xpath = "//span[@class='pMSy0p XU9vZa']")   
	WebElement cameraActualPrice;
	
	public String verifyingCameraName(){
		return cameraFullNameText.getText();
	}
	
	public String verifyingCameraPrice(){
		return cameraActualPrice.getText();
	}
	
}
