package ebay1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public enum ConfigProperties 
{
	SELENIUM_HOST("selenium.host","",Type.String),
	SELENIUM_PORT("selenium.port","",Type.String),
	BROWSER_NAME("browser.name","",Type.String),
	BROWSER_VERSION("browser.version","",Type.String),
	THREAD_COUNT("thread.count","",Type.String),
	TESTNG_GROUP("testng.group","",Type.String),
	TEST_URL("test.url","",Type.String),
	TESTNG_PACKAGE("testng.package","",Type.String),
	APPLICATION_TYPE("application.type","",Type.String),
	DEVICE_NAME("device.name","",Type.String),
	PLATFORM_VERSION("platform.version","",Type.String),
	PLATFORM_NAME("platform.name","",Type.String),
	APP_PATH("app.path","",Type.String),
	APP_PACKAGE("app.package","",Type.String),
	;
				
	String key;
	Object value;
	Type type;
	Properties prop;
	
	private enum Type{Integer,String,Boolean};

	private ConfigProperties(String key,Object value,Type type) 
	{
		this.key=key;
		this.value=value;	
		this.type=type;
	}
	
	public void setProperty(Object value1)
	{		
		this.value=value1;
	}
	
	@Override
	public String toString() 
	{
		loadproperties();
		return this.value.toString();
	}

	public Object getProperty()
	{
		loadproperties();
		return this.value;
	}

	public void loadproperties() 
	{
		try 
		{			
			prop=new Properties();
			prop.load(new FileInputStream(new File(System.getProperty("D:\\Vinay\\Ebay\\src\\main\\java\\property.properties"))));	
			for(ConfigProperties tPrp:ConfigProperties.values())			
			{
					String valueFromFile=prop.getProperty(tPrp.key);
						if(valueFromFile!=null&&valueFromFile.contains(tPrp.key))
						{
							valueFromFile=String.valueOf(tPrp.key);
						}
						if(valueFromFile!=null) 
						{
							if(tPrp.type==Type.Integer)
							{
								tPrp.setProperty(Integer.parseInt(valueFromFile));
							}
							else if(tPrp.type==Type.Boolean)
							{
								tPrp.setProperty(Boolean.parseBoolean(valueFromFile));
							}
							else
								tPrp.setProperty(valueFromFile);
						}
			}
		}
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}

