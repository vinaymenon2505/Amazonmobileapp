package ebay1;


import java.util.HashMap;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import io.appium.java_client.MobileElement;

import io.appium.java_client.android.AndroidDriver;

import utils.Locator;
import utils.TestNGUtils;

public class Action
{
	WebDriver driver;
	AndroidDriver<MobileElement> appdriver;
	HashMap<String, Object> hashMap = new HashMap<String, Object>();

	public Action(AndroidDriver<MobileElement> driver)
	{
		this.appdriver = driver;
	}

	public Action click(Locator loc)
	{
		try 
		{
			appdriver.findElement(Locator.getByObject(loc)).click();
		}
			catch (Exception e) 
		{
			TestNGUtils.reportLog("The element:" + loc.getKey() + " could not be clicked");
			throw e;
		}
		return this;
	}

	public synchronized void storeKeyValue(String key, Object value)
	{
		hashMap.put(key, value);
	}

	public synchronized Object retrieveKeyValue(String key)
	{
		return hashMap.get(key);
	}
	
	

	public Action scrollTo(Locator loc) 
	{
		int x = 0;
		
		try
		{
			while (!isElementVisible(loc)) 
			 {
				x++;
				int x1 = appdriver.manage().window().getSize().width / 2;
				int y = appdriver.manage().window().getSize().height * 2 / 5;
				int z = appdriver.manage().window().getSize().height / 16;
				
				TouchActions action = new TouchActions(driver);
				action.down(x1, y);
				action.move(y, z);
				action.release();
				action.perform();
				
				


				//ActionParameter action = new ActionParameter("moveTo", moveToOptions);
		        //parameterBuilder.add(action);
				//deprecated feature 
				//new TouchAction(appdriver).longPress(pressX, bottomY).moveTo(pressX, topY).release().perform();
						
				
				if (isElementVisible(loc) || x1 > 5)
				{
					break;
				}
			}
		} 
		
		catch (Exception e) 
		{
			TestNGUtils.reportLog("The element:" + loc.getKey() + " could not be scrolled to");
		}
		
		return this;
	}

	public Action scrollForN(int num) 
	{
		try 
		{
			while (num > 0) 
			{
				num--;
				int x2 = appdriver.manage().window().getSize().width / 2;
				int y = appdriver.manage().window().getSize().height * 2 / 5;
				int z = appdriver.manage().window().getSize().height / 16;
				
				TouchActions action = new TouchActions(driver);
				action.down(x2, y);
				action.move(y, z);
				action.release();
				action.perform();
				
				//new TouchAction(appdriver).longPress(X,Y).moveTo(X, Z).release().perform();
				
				if (num > 7) 
				{
					break;
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return this;
	}

	public WebElement findElement(Locator loc)
	{
		waitTillElementVisible(3000, loc);
		WebElement ele = appdriver.findElement(Locator.getByObject(loc));
		return ele;
	}

	public String getText(Locator loc) throws Exception
	{
		String text = appdriver.findElement(Locator.getByObject(loc)).getText();
		return text;
	}

	public boolean isElementVisible(Locator loc)
	{
		boolean isvisible = false;
		try
		{
			appdriver.findElement(Locator.getByObject(loc));
			isvisible = true;
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return isvisible;
	}

	public Action sendText(Locator loc, String input)
	{
		waitTillElementVisible(3000, loc);
		appdriver.findElement(Locator.getByObject(loc)).clear();
		;
		appdriver.findElement(Locator.getByObject(loc)).sendKeys(input);
		return this;
	}

	public Action pressEnter() 
	{
		/*Robot robot;
	    try 
	    {
	        robot = new Robot();
	        robot.keyPress(KeyEvent.KEYCODE_ENTER);	      
	    } 
	    catch (AWTException e) 
	    {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }*/
		
		//appdriver.pressKeyCode(AndroidKeyCode.ENTER); --> deprecated
				
		

		return this;
	}

	@SuppressWarnings("deprecation")
	public void waitTillElementVisible(long sec, Locator loc)
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(appdriver, sec);
			wait.until(ExpectedConditions.visibilityOf(appdriver.findElement(Locator.getByObject(loc))));
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public void rotateTo(String mode)
	{
		try
		{
			switch (mode) 
			{
				case "landscape":
					appdriver.rotate(ScreenOrientation.LANDSCAPE);
					break;

				case "portrait":
					appdriver.rotate(ScreenOrientation.PORTRAIT);
					break;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public void waitFor(long sec)
	{
		try
		{
			Thread.sleep(sec);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}