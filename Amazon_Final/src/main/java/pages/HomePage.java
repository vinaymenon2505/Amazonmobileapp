package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.TestBase;
import io.appium.java_client.MobileElement;

public class HomePage extends TestBase {
	
	//PageFactory - OR:
	@FindBy(id="com.amazon.mShop.android.shopping:id/action_bar_home_logo")
	WebElement HomePageLogo;
	
	@FindBy(id="com.amazon.mShop.android.shopping:id/rs_search_src_text")
	WebElement SearchTextBox;
	
	//Initializing the Page Objects:
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	//Method to verify logo on Home page of application
	public void verifyHomePageLogo() {
		HomePageLogo.isDisplayed();
	}
	
	//Method to search product by clicking on search text box in Home Page
		public SearchListPage searchProduct() throws InterruptedException {
		
		SearchTextBox.sendKeys("65 inch TV");
		List<MobileElement> lstElements = driver.findElements(By.className("android.widget.LinearLayout"));
//		System.out.println(lstElements.size());
		lstElements.get(1).click();
		Thread.sleep(5000);
		/*for(MobileElement ele : lstElements) {
			if("65 inch tv 4k smart android tv".equals(ele.getText()))
				ele.click();
		}*/
		return new SearchListPage();
	}
}
