package testcases;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBase;
import pages.CheckOutPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailsPage;
import pages.SearchListPage;

public class CheckOutPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	SearchListPage searchListPage;
	ProductDetailsPage productDetailsPage;
	CheckOutPage checkOutPage;
	
public CheckOutPageTest() {
	super();
}

@BeforeMethod
public void setUp() throws MalformedURLException, InterruptedException {
	
	initialization();
	loginPage = new LoginPage();
	homePage = loginPage.validateLogin(prop.getProperty("email"), prop.getProperty("password"));
	searchListPage = homePage.searchProduct();
	searchListPage.selectProductusingScroll();
	productDetailsPage = searchListPage.fetchingProduct();
	checkOutPage=productDetailsPage.validateCheckOut();
}

//validate product details in checkout page
@Test
public void validatecheckOutProductDetailTest() throws InterruptedException {
	
	String checkoutproductDetail=checkOutPage.validateCheckOutProductDetails();
	System.out.println("TV name: "+checkoutproductDetail);
//	Assert.assertEquals(checkoutproductDetail, "TCL 163.96 cm (65 inches) 4K Ultra HD Smart Certified Android LED TV 65P8E (Black) (2019 Model)");
	Assert.assertEquals(checkoutproductDetail, productDetailsPage.validateProductDetails());
	loggingOutApp();
}
@AfterMethod
public void closingBrowser() {
	tearDown();
}
}
