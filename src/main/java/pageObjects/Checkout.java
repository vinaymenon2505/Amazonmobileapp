package pageObjects;


import org.testng.Assert;

import ebay1.Base;
import utils.Locator;
import utils.TestNGUtils;



public class Checkout extends Base
{
	public static Locator CHECKOUT_ERROR=new Locator("Checkout Error", "com.ebay.mobile:id/home", Locator.Type.ID);
	public static Locator CONTINUE_SHOPPING=new Locator("Continue Shopping button", "Continue Shopping", Locator.Type.ID);
	public static Locator CHECKOUT_PRODUCT_NAME=new Locator("Checkout product name", "com.ebay.mobile:id/item_title", Locator.Type.ID);
	public static Locator CHECKOUT_PRODUCT_PRICE=new Locator("Checkout product price", "com.ebay.mobile:id/textview_item_price", Locator.Type.ID);
	public static Locator CHECKOUT_PRODUCT_COMMIT=new Locator("Checkout product commit button", "com.ebay.mobile:id/take_action", Locator.Type.ID);
	public static Locator CHECKOUT_PRODUCT_ERROR_OK=new Locator("Checkout product error ok button", "com.ebay.mobile:id/error_okay_btn", Locator.Type.ID);

	public Checkout clickonaButton() throws Exception 
	{
		TestNGUtils.reportLog("Click on any button to exit the error page");
		try 
		{
		getAction().findElement(CONTINUE_SHOPPING);
		getAction().click(CONTINUE_SHOPPING);
		getAction().waitFor(3000);
		}
		catch(Exception e) 
		{
			getAction().findElement(CHECKOUT_ERROR);
			getAction().click(CHECKOUT_ERROR);
			getAction().waitFor(3000);
			
		}
		return this;
	}
	
	public Checkout clickonCommitButton() throws Exception 
	{
		TestNGUtils.reportLog("Click on commit to order button");
		getAction().findElement(CHECKOUT_PRODUCT_COMMIT);
		getAction().click(CHECKOUT_PRODUCT_COMMIT);
		getAction().waitFor(3000);
		getAction().findElement(CHECKOUT_PRODUCT_ERROR_OK);
		getAction().click(CHECKOUT_PRODUCT_ERROR_OK);
		getAction().waitFor(5000);
		return this;
	}	
	
	public  boolean ifErrorPage() throws Exception 
	{
		TestNGUtils.reportLog("Check if error page is loaded");
		boolean error=false;
		getAction().waitFor(2000);;
		if(!getAction().isElementVisible(CHECKOUT_PRODUCT_COMMIT)) 
		{
			error=true;
		}
		return error;
	}	
	
	public Checkout verifyProductDetailsCheckoutPage() throws Exception 
	{
		TestNGUtils.reportLog("Verifying whether product name and price in checkout matches with that of search results page");
		getAction().findElement(CHECKOUT_PRODUCT_NAME);
		String nameInCheckout=getAction().getText(CHECKOUT_PRODUCT_NAME);
		String nameInSrp=(String) getAction().retrieveKeyValue("productName");
		Assert.assertEquals(nameInCheckout, nameInSrp, "Product Name mismatch between checkout and SRP.Product name in checkout:"+nameInCheckout+" Product name in SRP:"+nameInSrp);
		String priceInCheckout=getAction().getText(CHECKOUT_PRODUCT_PRICE).replaceAll(",", "").replaceAll("Rs.", "");
		String priceInSrp=(String) getAction().retrieveKeyValue("productPrice");
		Assert.assertEquals(String.valueOf(Long.parseLong(priceInCheckout)),Long.parseLong(priceInSrp), "Product Price mismatch between Checkout and SRP.Product price in Checkout:"+priceInCheckout+" Product price in SRP:"+priceInSrp);

		return this;
	}
}