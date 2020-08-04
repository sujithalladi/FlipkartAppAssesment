package com.flipkart.qa.pages;
import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.qa.base.TestBase;
import com.flipkart.qa.testCases.ProductDetails;

public class PaymentPage extends TestBase{

	SearchPage searchPage;
	public PaymentPage() throws IOException {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='_325-ji']")   
	WebElement cameraFullNameText;  
	
	@FindBy(xpath = "//span[@class='pMSy0p XU9vZa']")   
	WebElement cameraActualPrice;
	
	@FindBy(xpath = "//div[@class='_2EitLy']")   
	WebElement cameraActualSeller;
	
	public String verifyingCameraName(){
		return cameraFullNameText.getText();
	}
	
	public String verifyingCameraPrice(){
		return cameraActualPrice.getText();
	}
	
	public ProductDetails captureProductDetails(){
		ProductDetails productDetails=new ProductDetails();
		productDetails.setName(cameraFullNameText.getText());
		productDetails.setPrice(cameraActualPrice.getText());
		productDetails.setSeller(cameraActualSeller.getText().replace("Seller: ", ""));
		return productDetails;
	}
	
}
