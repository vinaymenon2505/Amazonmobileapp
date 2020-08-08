package pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.TestBase;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class SearchListPage extends TestBase {
	
	//Page Factory - OR:
	@FindBy(id="com.amazon.mShop.android.shopping:id/rs_search_src_text")
	WebElement SearchTextBox;
	
	@FindBy(xpath="//android.widget.ImageView[@content-desc=\"TCL 163.96 cm (65 inches) 4K Ultra HD Smart Certified Android LED TV 65P8E (Black) (2019 Model),image\"]")
	WebElement Product;
	
	//Initializing the Page Objects:
	public SearchListPage() {
		PageFactory.initElements(driver, this);
	}

	//Actions:
	//Method to scroll down in search list page 
	public void selectProductusingScroll() {
		
		TouchAction action = new TouchAction(driver); 
//		PointOption tapOption = new PointOption(); 
		

		action.press(PointOption.point(408,1707))
		.waitAction(new WaitOptions().withDuration(Duration.ofMillis(600)))
		.moveTo(PointOption.point(408,1382))
		.release()
		.perform();
	
	}
	
	//Method to click on desied product from search list
	public ProductDetailsPage fetchingProduct() throws InterruptedException {
		
		Product.click();
		Thread.sleep(5000);
		return new ProductDetailsPage();
	}
	
}
