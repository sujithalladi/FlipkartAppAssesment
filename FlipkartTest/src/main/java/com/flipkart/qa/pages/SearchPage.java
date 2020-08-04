package com.flipkart.qa.pages;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.qa.base.TestBase;
import com.flipkart.qa.util.TestUtil;

public class SearchPage extends TestBase{

	@FindBy(xpath="//span[contains(text(),'Showing')]")
	WebElement showing ;
	
	@FindBy(xpath="//div[contains(text(),'Relevance')]")
	WebElement relevanceLink;
	
	@FindBy(xpath="//img[@alt='Nikon D5600 (With Basic Accessory Kit) DSLR Camera Body with Single Lens: AF-P DX Nikkor 18-55 MM F/3.5-5.6G VR (16 GB SD Card)']")
	WebElement nikonCamera;
  
	@FindBy(xpath = "//div[contains(text(),'₹42,999')]")
	WebElement cameraPrice;
	
	@FindBy(xpath="//button[@class='_2AkmmA _2Npkh4 _2MWPVK']")
	WebElement addToCartBtn;  
	
	@FindBy(xpath="//button[@class='_2AkmmA _2Npkh4 _2kuvG8 _7UHT_c']")
	WebElement buyNowBtn;
	
	public SearchPage() throws IOException {
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyShowingTextForSearchedText(){
		return showing.isDisplayed();
	}
	
	public boolean verifyRelevanceLink(){
		return relevanceLink.isDisplayed();
	}
	public void scrollToTheWebElementAndClick() throws InterruptedException, IOException{
		
		TestUtil testUtil = new TestUtil();
		testUtil.waitForWebElementToLoad(10);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", nikonCamera);
		Actions act = new Actions(driver);
		act.moveToElement(nikonCamera).click().build().perform();
		
	}
	
	public boolean verifyAddToCartButton(){
		return addToCartBtn.isDisplayed();
	}
	
	public boolean verifyBuyNowButton(){
		return buyNowBtn.isDisplayed();
	}
	
	public PaymentPage clickOnBuyNowButton() throws InterruptedException, IOException{
		 
		if(buyNowBtn.isDisplayed()){
			buyNowBtn.click();
			Thread.sleep(2000);
		}else{
			System.out.println(buyNowBtn + " is not available at this moment");
		}
		return new PaymentPage();
		
	}
	
}
