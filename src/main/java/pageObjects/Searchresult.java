package pageObjects;


import java.util.Random;

import ebay1.Base;
import utils.Locator;
import utils.TestNGUtils;

public class Searchresult extends Base
{
	public static Locator SRP_PRODUCT_CARD=new Locator("Product card", "//android.widget.RelativeLayout[@index='{0}']", Locator.Type.XPATH);
	public static Locator SRP_PRODUCT_CARD_TITLE=new Locator("Product card name", "//android.widget.RelativeLayout[@index='{0}']/android.widget.RelativeLayout/android.widget.TextView", Locator.Type.XPATH);
	public static Locator SRP_PRODUCT_CARD_PRICE=new Locator("Product card price", "(//android.widget.TextView[@resource-id='com.ebay.mobile:id/textview_item_price'])[{0}]", Locator.Type.XPATH);

	
	public Searchresult clickOnRandomProduct() throws Exception 
	{
		Random run = new Random();
		int x = run.nextInt(2);
		x=x+1;
		TestNGUtils.reportLog("Clicking on a random product at position:"+x);
		getAction().scrollForN(2);
		getAction().findElement(SRP_PRODUCT_CARD_TITLE.format(String.valueOf(x)));
		String productName=getAction().getText(SRP_PRODUCT_CARD_TITLE.format(String.valueOf(x)));
		getAction().findElement(SRP_PRODUCT_CARD_PRICE.format(String.valueOf(x+1)));
		String productPrice=getAction().getText(SRP_PRODUCT_CARD_PRICE.format(String.valueOf(x+1))).replaceAll(",", "").replaceAll("Rs.", "");
		getAction().storeKeyValue("productName", productName);
		getAction().storeKeyValue("productPrice", productPrice);
		getAction().click(SRP_PRODUCT_CARD_TITLE.format(String.valueOf((x))));
		getAction().waitFor(3000);
	
		return this;
	}
}
