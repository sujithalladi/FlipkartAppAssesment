package com.flipkart.qa.pages;
import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

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
	
	@FindBy(xpath = "//span[contains(text(),'Order Summary')]")
	WebElement orderSummaryText;
	
	@FindBy(xpath = "//span[contains(text(),'Price details')]")
	WebElement priceDetailsText;
	
	@FindBy(xpath = "//button[contains(text(),'CONTINUE')]")
	WebElement continueBtn;
	
	@FindBy(xpath = "//span[contains(text(),'Payment Options')]")
	WebElement paymentOptionsSection;
	
	@FindBy(xpath = "//div[contains(text(),'Amount Payable')]")
	WebElement AmountPayableField;
	
	@FindBy(xpath = "//span[contains(text(),'UPI (PhonePe / Google Pay / BHIM)')]")
	WebElement UPIRadioBtn;

	
	
	public void clickOnContinueButton(){
		if(paymentOptionsSection.isDisplayed()){
			continueBtn.click();
		}
	}
	
	public String verifyingCameraName(){
		return cameraFullNameText.getText();
	}
	
	public String verifyingCameraPrice(){
		return cameraActualPrice.getText();
	}
	
}
