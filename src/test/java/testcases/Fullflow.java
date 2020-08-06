package testcases;


import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.Findproduct;
import ebay1.Base;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import pageObjects.Checkout;
import pageObjects.Homepage;
import pageObjects.ProductDetails;
import pageObjects.Searchresult;
import utils.Initialize;



public class Fullflow  
{
	@Test(groups= "checkoutProduct")
	public void checkoutProduct() throws Exception 
	{
		String testCaseName=new Exception().getStackTrace()[0].getMethodName();

		Initialize utils=new Initialize();
		AndroidDriver<MobileElement> driver=utils.getDriver();

		Findproduct data=Findproduct.fetch(testCaseName);
		Homepage homePage=new Homepage();

		homePage.signIn(data.Logincredentials.emailid, data.Logincredentials.password)
		.searchItem(data.searchstring);

		Searchresult srp=new Searchresult();
		srp.clickOnRandomProduct();

		ProductDetails pdp=new ProductDetails(driver);
		pdp.verifyProductDetailsInProductPage().addProductToCart()
		.addQuantity().clickOnReviewButton();

		Checkout checkoutPage=new Checkout();

		if(checkoutPage.ifErrorPage())		
		{
			checkoutPage
			.clickonaButton();

		}else {
			checkoutPage
			.verifyProductDetailsCheckoutPage()
			.clickonaButton();

		}
		homePage
		.signOut();
	}

	@Test(dataProvider="getDataFromExcel",groups="searchProduct")
	public void searchProduct(List <Findproduct> data) throws Exception {
		String testCaseName=new Exception().getStackTrace()[0].getMethodName();
		Initialize utils=new Initialize();
		utils.getDriver();
		Findproduct logindata=Findproduct.fetch(testCaseName);
		for(int i=0;i<data.size();i++) {
			Homepage homePage=new Homepage();

			homePage
			.signIn(logindata.Logincredentials.emailid, logindata.Logincredentials.password)
			.searchItem(data.get(i).searchstring);
			homePage
			.signOut();
		}

	}
	@DataProvider 
	public static Object[][] getDataFromExcel() {
		List <Findproduct> searchList=Base.getDataFromExcel();	

		return new Object[][]{
			{
				searchList
			},
		};
	}
}