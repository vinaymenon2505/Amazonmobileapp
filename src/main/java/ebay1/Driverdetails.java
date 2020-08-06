package ebay1;

import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;


import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;





public class Driverdetails 
{
	public WebDriver driver;
	public Action action;
	public AndroidDriver<MobileElement>appDriver;
	
	public Driverdetails()
	{
		if(ConfigProperties.APPLICATION_TYPE.toString().equalsIgnoreCase("desktop")) 
		{
		assignWebDriver()	;
		}
		else 
		{
		assignAndroidDriver();
		}
		
		action = new Action(appDriver);
		
	}
	
	public WebDriver getDriver() 
	{
		return driver;
	}
	
	public AndroidDriver<MobileElement> getAndroidDriver() 
	{
		return appDriver;
	}
	
	public Action getAction() 
	{
		return action;
	}
	
	public static AndroidDriver<MobileElement> getDriverInstanceForAndroid()
	{
		return getThread().getAndroidDriver();
	}
	
	public static WebDriver getDriverInstance()
	{
		return getThread().getDriver();
	}
	
	public static ThreadLocal<Driverdetails> driverThread=new ThreadLocal<Driverdetails>()
	{
		@Override
		protected Driverdetails initialValue() 
		{
			return new Driverdetails();
		}
	};
	
	public static Driverdetails getThread() 
	{
		return driverThread.get();
	}
	
	public WebDriver assignWebDriver() 
	{
		DesiredCapabilities caps=null;
		caps=getCapabilities();
		try 
		{
			if(ConfigProperties.APPLICATION_TYPE.toString().equals("desktop"))
			{
				String seleniumHost=ConfigProperties.SELENIUM_HOST.toString();
				String seleniumPort=":"+ConfigProperties.SELENIUM_PORT.toString();
				String hostURL = "http://" + seleniumHost + seleniumPort + "/wd/hub";
				driver=new RemoteWebDriver(new URL(hostURL), caps);
			}
			else 
			{
				driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
			} 
		}
		catch (Exception e) 
		{
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			return driver;
	}
	
	public AndroidDriver<MobileElement> assignAndroidDriver() 
	{
		DesiredCapabilities caps=null;
		caps=getCapabilities();
		try 
		{
			if(ConfigProperties.APPLICATION_TYPE.toString().equals("desktop")) 
			{
				String seleniumHost=ConfigProperties.SELENIUM_HOST.toString();
				String seleniumPort=":"+ConfigProperties.SELENIUM_PORT.toString();
				String hostURL = "http://" + seleniumHost + seleniumPort + "/wd/hub";
				driver=new RemoteWebDriver(new URL(hostURL), caps);
			}
			else 
			{
				appDriver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
			} 
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return appDriver;
	}
	
	public DesiredCapabilities getCapabilities() 
	{
			DesiredCapabilities cap=null;;
			if(ConfigProperties.APPLICATION_TYPE.toString().equals("desktop")) 
			{
				ChromeOptions options=new ChromeOptions();
				String browser=ConfigProperties.BROWSER_NAME.toString();

				switch(browser) 
				{
				case("chrome"):
					options.addArguments("--start-maximised");
				cap=new DesiredCapabilities();
				cap.setCapability(ChromeOptions.CAPABILITY, options);
				cap.setCapability("browserName", browser);
				default:
					cap.setCapability("browserName", browser);
					break;
				}
				cap.setJavascriptEnabled(true);
				cap.setCapability("browserVersion", ConfigProperties.BROWSER_VERSION.toString().trim());

			}
			else 
			{
				cap=new DesiredCapabilities();
				cap.setCapability(CapabilityType.BROWSER_NAME, "");
				cap.setCapability("deviceName", ConfigProperties.DEVICE_NAME.toString());
				cap.setCapability("platformVersion",ConfigProperties.PLATFORM_VERSION.toString());
				cap.setCapability("platformName", ConfigProperties.PLATFORM_NAME.toString());
				cap.setCapability("noReset", "true");
				cap.setCapability("fullReset", "false");
				cap.setCapability("app", ConfigProperties.APP_PATH.toString());
				cap.setCapability("appPackage", ConfigProperties.APP_PACKAGE.toString());
				cap.setCapability("launchActivity", ConfigProperties.APP_PACKAGE.toString()+".MainActivity");		
			}
			return cap;
	}
}
		