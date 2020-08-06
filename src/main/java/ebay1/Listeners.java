package ebay1;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
//import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import pageObjects.Homepage;


public class Listeners implements ITestListener 
{
	@Override
	public void onFinish(ITestContext arg0) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext arg0) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult arg0)
	{
		// TODO Auto-generated method stub
		captureScreenShot(arg0);
		if(ConfigProperties.APPLICATION_TYPE.toString().equalsIgnoreCase("mobile")) 
		{
			Homepage page=new Homepage();
			page.signOut();
			Driverdetails.getDriverInstanceForAndroid().closeApp();;
		}
		else 
		{
			Driverdetails.getDriverInstance().close();
		}
	}
	
	public void captureScreenShot(ITestResult arg0) 
	{
		String testcase=arg0.getMethod().getMethodName();
		File source = ((TakesScreenshot) Driverdetails.getDriverInstanceForAndroid()).getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"\\test-output\\screenshots\\"+testcase+".png";
		try 
		{
			File copied=new File(path);
			FileUtils.copyFile(source,copied );
			String hrefPath = "./screenshots/" + testcase + ".png";
			Reporter.log("<a href='" + hrefPath + "'>Screenshot</a>");	
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult arg0) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestStart(ITestResult arg0) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestSuccess(ITestResult arg0) 
	{
		// TODO Auto-generated method stub
		if(ConfigProperties.APPLICATION_TYPE.toString().equalsIgnoreCase("mobile")) {
			Driverdetails.getDriverInstanceForAndroid().closeApp();;
		}
		else 
		{
			Driverdetails.getDriverInstance().close();
		}
	}
}
