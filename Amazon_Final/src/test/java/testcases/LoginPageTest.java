package testcases;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBase;
import pages.HomePage;
import pages.LoginPage;

public class LoginPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	
	public LoginPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws MalformedURLException {
		initialization();
		loginPage = new LoginPage();
	}
	
	//Test method to verify Amazon logo in Login Page
	@Test(priority=1)
	public void amazonLogoTest() {
		boolean flag=loginPage.validateAmazonLogo();
		Assert.assertTrue(flag);
		System.out.println("Amazon Logo displayed successfully.");
	}
	
	//Test method to login to and log out from application
	@Test(priority=2)
	public void loginTest() throws InterruptedException {
		homePage=loginPage.validateLogin(prop.getProperty("email"), prop.getProperty("password"));
		loggingOutApp();
	}
	
	
	@AfterMethod
	public void closingBrowser() {
		tearDown();
	}

}
