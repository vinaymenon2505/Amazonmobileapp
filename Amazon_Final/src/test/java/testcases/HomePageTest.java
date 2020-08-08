package testcases;

import java.net.MalformedURLException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBase;
import pages.HomePage;
import pages.LoginPage;
import pages.SearchListPage;

public class HomePageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	SearchListPage searchListPage;
	
	public HomePageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws MalformedURLException, InterruptedException {
		initialization();
		loginPage = new LoginPage();
		homePage = loginPage.validateLogin(prop.getProperty("email"), prop.getProperty("password"));
	}
	
	//verifying Home page logo
	@Test(priority=1)
	public void validateHomePageLogoTest() throws InterruptedException {
		homePage.verifyHomePageLogo();
		loggingOutApp();
	}
	
	//Searches product in home page and return to search list page
	@Test(priority=2)
	public void validateAddingItemTest() throws InterruptedException {
		searchListPage = homePage.searchProduct();
		loggingOutApp();
	}
	
	@AfterMethod
	public void closingBrowser() {
		tearDown();
	}
	
}
