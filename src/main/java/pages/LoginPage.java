package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class LoginPage extends TestBase{
	
  WebDriverWait wait = new WebDriverWait(driver,20);
	
	
	//PageFactory - OR:
	@FindBy(id="com.amazon.mShop.android.shopping:id/sso_splash_logo")
	WebElement AmazonLogo;
	
	@FindBy(xpath="//android.widget.Button[@text='Already a customer? Sign in']")
	WebElement SignInBtn;
	
	@FindBy(id="login_accordion_header")
	WebElement LoginRadioBtn;
	
	@FindBy(id="ap_email_login")
	WebElement EmailTextBox;
	
	@FindBy(xpath="//android.widget.Button[@text='Continue']")
	WebElement ContinueBtn;
	
	@FindBy(id="auth-password-container")
	WebElement PasswordTextBox;
	
	@FindBy(id="auth-signin-button")
	WebElement LoginBtn;
	
	//Initializing the Page Objects:
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	//Method to verify logo in Login Page
	public boolean validateAmazonLogo() {
		return AmazonLogo.isDisplayed();
	}
		
	//Method to validate login to application
	public HomePage validateLogin(String emailId,String pwd) throws InterruptedException {
	
			SignInBtn.click();
			
			 if(!LoginRadioBtn.isSelected()) {
				 LoginRadioBtn.click();
			 }
			 else{
				 System.out.println("Login radio button is already selected.");
			 }
			wait.until(ExpectedConditions.elementToBeClickable(By.id("ap_email_login")));
			EmailTextBox.sendKeys(emailId);
			 
			 ContinueBtn.click();
			 
			 PasswordTextBox.sendKeys(pwd);
			 Thread.sleep(10000);
			 
			 LoginBtn.click();
		 
			 return new HomePage();
			  
		}
	
	}


