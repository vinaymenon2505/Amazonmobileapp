package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.TestBase;

public class ProductDetailsPage extends TestBase {
	
	//Page Factory - OR:
	@FindBy(xpath="//android.view.View[@text='TCL 163.96 cm (65 inches) 4K Ultra HD Smart Certified Android LED TV 65P8E (Black) (2019 Model)']")
	WebElement ProductName;
	
	@FindBy(id="buyNowCheckout")
	WebElement BuyNowBtn;
	
	//Initializing the Page Objects:
	public ProductDetailsPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	//Method to validate product details in product details page
	public String validateProductDetails() {
		return ProductName.getText();
	}
	
	//This method has not been implemented in used java
	/*public void validateScrollTo() {
		
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].scrollIntoView(true);", BuyNowBtn);
	}*/
	
	//Method to click on BuyNow button to navigate to checkout page
		public CheckOutPage validateCheckOut() throws InterruptedException {
			
		Thread.sleep(2000);
		BuyNowBtn.click();
		return new CheckOutPage();
}
}
