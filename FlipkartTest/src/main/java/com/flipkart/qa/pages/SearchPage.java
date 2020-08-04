package com.flipkart.qa.pages;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.qa.base.TestBase;
import com.flipkart.qa.testCases.ProductDetails;
import com.flipkart.qa.util.TestUtil;

public class SearchPage extends TestBase{

	@FindBy(xpath="//span[contains(text(),'Showing')]")
	WebElement showing ;
	
	@FindBy(xpath="//div[contains(text(),'Relevance')]")
	WebElement relevanceLink;
	
	@FindBy(xpath = "//div[@class='_3BTv9X']")
	WebElement allCameras;
		
	@FindBy(xpath="//button[@class='_2AkmmA _2Npkh4 _2MWPVK']")
	WebElement addToCartBtn;  
	
	@FindBy(xpath="//button[@class='_2AkmmA _2Npkh4 _2kuvG8 _7UHT_c']")
	WebElement buyNowBtn;
	
	@FindBy(xpath = "//span[@class='_35KyD6']")
	WebElement productName;
	
	@FindBy(xpath = "//div[@class='_1vC4OE _3qQ9m1']")
	WebElement productPrice;
	
	@FindBy(xpath = "//div[@id='sellerName']")
	WebElement productSeller;
	
	@FindBy(xpath = "//div[@class='hGSR34 YddkNl']")   
	WebElement cameraSellerRating;
	
	public SearchPage() throws IOException {
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyShowingTextForSearchedText(){
		return showing.isDisplayed();
	}
	public String getSellerRating(){
		return cameraSellerRating.getText();
	}
	public boolean verifyRelevanceLink(){
		return relevanceLink.isDisplayed();
	}
	
	public void SelectTheProdcut() throws InterruptedException, IOException{
		List<WebElement> li = driver.findElements(By.xpath("//div[@class='_3BTv9X']"));
		Random r = new Random();
		int randomValue = r.nextInt(li.size());
		li.get(randomValue).click();
	}

	public boolean verifyAddToCartButton(){
		return addToCartBtn.isDisplayed();
	}
	
	public boolean verifyBuyNowButton(){
		return buyNowBtn.isDisplayed();
	}
	
	public PaymentPage clickOnBuyNowButton() throws InterruptedException, IOException{
		TestUtil.waitForWebElementToLoad(10);
		if(buyNowBtn.isDisplayed()){
			buyNowBtn.click();	
		}
		return new PaymentPage();
	}
	
	public ProductDetails captureProductDetails(){
		ProductDetails productDetails=new ProductDetails();
		productDetails.setName(productName.getText());
		productDetails.setPrice(productPrice.getText());
		productDetails.setSeller(productSeller.getText().substring(0, productSeller.getText().length()-3));
		return productDetails;
		
	}
}
