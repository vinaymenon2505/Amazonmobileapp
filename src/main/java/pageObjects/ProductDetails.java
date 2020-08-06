package pageObjects;



import org.testng.Assert;

import ebay1.Base;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import utils.Locator;
import utils.TestNGUtils;


public class ProductDetails extends Base
{

	AndroidDriver<MobileElement> driver;
	public static Locator PRODUCT_NAME;
	public static Locator PRODUCT_PRICE;
	public static Locator PRODUCT_BUY_BUTTON;
	public static Locator QUANTITY;
	public static Locator QUANTITY_2;
	public static Locator REVIEW;

	public ProductDetails(AndroidDriver<MobileElement> driver)
	{
		this.driver=driver;
		PRODUCT_NAME = new Locator("Product name in PDP", "com.ebay.mobile:id/textview_item_name", Locator.Type.ID);
		PRODUCT_PRICE = new Locator("Product price in PDP", "com.ebay.mobile:id/textview_item_price", Locator.Type.ID);
		PRODUCT_BUY_BUTTON=new Locator("Product Buy Now Button", "com.ebay.mobile:id/button_bin", Locator.Type.ID);
		QUANTITY=new Locator("Product Quantity", "android:id/numberpicker_input", Locator.Type.ID);
		QUANTITY_2=new Locator("Product Quantity", "//android.widget.Button[@text='2']", Locator.Type.XPATH);
		REVIEW=new Locator("Product Quantity", "com.ebay.mobile:id/take_action", Locator.Type.ID);
	}
	
	public ProductDetails verifyProductDetailsInProductPage() throws Exception 
	{
		TestNGUtils.reportLog("Verifying whether product name and price matches with that of search results page");
		getAction().findElement(PRODUCT_NAME);
		String nameInPdp=getAction().getText(PRODUCT_NAME);
		String nameInSrp=(String) getAction().retrieveKeyValue("productName");
		Assert.assertEquals(nameInPdp, nameInSrp, "Product Name mismatch between PDP and SRP.Product name in PDP:"+nameInPdp+" Product name in SRP:"+nameInSrp);
		String priceInPdp=getAction().getText(PRODUCT_PRICE).replaceAll(",", "").replaceAll("Rs.", "");
		String priceInSrp=(String) getAction().retrieveKeyValue("productPrice");
		Assert.assertEquals(String.valueOf(Long.parseLong(priceInPdp)),Long.parseLong(priceInSrp), "Product Price mismatch between PDP and SRP.Product price in PDP:"+priceInPdp+" Product price in SRP:"+priceInSrp);

		return this;
	}
	
	public ProductDetails addProductToCart() throws Exception 
	{
		TestNGUtils.reportLog("Add the product to cart");
		getAction().findElement(PRODUCT_BUY_BUTTON);
		getAction().click(PRODUCT_BUY_BUTTON);
		getAction().waitFor(2000);
		return this;
	}	
	
	public ProductDetails addQuantity() throws Exception 
	{
		TestNGUtils.reportLog("Choose default quantity");
		getAction().findElement(QUANTITY);
		getAction().click(QUANTITY);
		getAction().rotateTo("landscape");
		getAction().rotateTo("portrait");
		getAction().pressEnter();		
		getAction().waitFor(2000);
		return this;
	}
		
	public ProductDetails clickOnReviewButton() throws Exception 
	{
		TestNGUtils.reportLog("Click on review button");
		getAction().findElement(REVIEW);
		getAction().click(REVIEW);
		getAction().waitFor(10000);
		return this;
	}
}
