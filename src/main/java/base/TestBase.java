package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import util.TestUtil;

public class TestBase {
	
	public static AndroidDriver<MobileElement> driver;
	public static Properties prop;
//	public static WebDriverWait wait = new WebDriverWait(driver,20);
	
	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("C:\\Users\\DELL\\Downloads\\AmazonAppTest\\src\\main\\java\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void initialization() throws MalformedURLException {
		
		DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName",prop.getProperty("deviceName"));
        caps.setCapability("udid",prop.getProperty("udid")); 
        caps.setCapability("platformName",prop.getProperty("platformName"));
        caps.setCapability("platformVersion",prop.getProperty("platformVersion"));
        caps.setCapability("skipUnlock","true");
        caps.setCapability("appPackage", prop.getProperty("appPackage"));
        caps.setCapability("appActivity",prop.getProperty("appActivity"));
        caps.setCapability("noReset","true");
        driver = new AndroidDriver<MobileElement>(new URL(prop.getProperty("URLCapability")),caps);
        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
       System.out.println("App Launched");
    }
	
	public static void loggingOutApp() throws InterruptedException {
		
		//clicking on Hamburger icon
		driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='Navigation panel, button, double tap to open side panel']")).click();
//		driver.findElement(By.id("com.amazon.mShop.android.shopping:id/action_bar_burger_icon")).click();
		
		
		//Scrolling down to click on Settings
		Thread.sleep(5000);
		TouchAction action = new TouchAction(driver); 
		action.press(PointOption.point(408,1707))
		.waitAction(new WaitOptions().withDuration(Duration.ofMillis(600)))
		.moveTo(PointOption.point(408,1382))
		.release()
		.perform();
		
		//clicking on Settings
		driver.findElement(By.xpath("//android.widget.TextView[@text='Settings']")).click();
		
		Thread.sleep(5000);
		
		//clicking on Sign out
		driver.findElement(By.xpath("//android.widget.TextView[contains(text(),'? Sign out')]")).click();
		
		//clicking on Sign Out Button in popup
		driver.findElement(By.xpath("//android.widget.Button[@text='SIGN OUT']")).click();
		
	}
	
	public static void tearDown() {
		driver.quit();
		System.out.println("Quiting Browser");
	}
		
	}

