package utils;

import org.openqa.selenium.WebDriver;

import ebay1.ConfigProperties;
import ebay1.Driverdetails;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Initialize 
{
	public AndroidDriver<MobileElement> getDriver() 
	{
		String url=ConfigProperties.TEST_URL.toString();
		AndroidDriver<MobileElement> driver=Driverdetails.getDriverInstanceForAndroid();
		if(ConfigProperties.APPLICATION_TYPE.toString().equals("desktop"))
		{
		driver.get(url);
		}
		return driver;
	}
	
	public WebDriver getDriverForWeb()
	{
		String url=ConfigProperties.TEST_URL.toString();
		WebDriver driver=Driverdetails.getDriverInstance();
		if(ConfigProperties.APPLICATION_TYPE.toString().equals("desktop"))
		{
			driver.get(url);
		}
		return driver;
	}
}
