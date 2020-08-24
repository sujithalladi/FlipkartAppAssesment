package navigation;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.flipkart.qa.base.TestBase;
import com.flipkart.qa.util.TestUtil;

public class TopNavigationSection extends TestBase {

	@BeforeMethod
	public void setUp() throws IOException{
		initialization();
		driver.navigate().to("https://www.amazon.in/");
	}
	
	public List<WebElement> getMenuItems(){
		List<WebElement>  menuItems = driver.findElements(By.xpath("//div[@id='nav-xshop']/a"));
		return menuItems;
	}
	
	public void navigateTo(String menuItem){
		String menuXpath = "//div[@id='nav-xshop']/a[contains(text(),'"+menuItem+"')]";
		driver.findElement(By.xpath(menuXpath)).click();
	}
	
	@Test
	public void navigateToMenuItem() throws InterruptedException{
		List<WebElement> menuItemsList = getMenuItems();
		int menuListSize = menuItemsList.size();
		System.out.println(menuListSize);
		for(WebElement menuElement : menuItemsList){
			System.out.println(menuElement.getText());
		}
		navigateTo("Best Sellers");
		TestUtil.waitForWebElementToLoad(2000);
		navigateTo("Books");
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
	
	
	
}
