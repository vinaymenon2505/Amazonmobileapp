package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.TestBase;

public class CheckOutPage extends TestBase {
	
	//Page Factory - OR:
	@FindBy(xpath="//android.view.View[@text='TCL 163.96 cm (65 inches) 4K Ultra HD Smart Certified Android LED TV 65P8E (Black) (2019 Model)']")
	WebElement CheckOutProductDetail;
	
	//Initializing the Page Objects:
	public CheckOutPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Method to validate the details of product in checkout page
	public String validateCheckOutProductDetails() throws InterruptedException {
		return CheckOutProductDetail.getText();
	}
	
	
	
	
	

}
