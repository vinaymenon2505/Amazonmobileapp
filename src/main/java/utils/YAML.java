package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import java.util.

public class YAML 
{	
	@SuppressWarnings("rawtypes")
	public static Object loadYaml(String key, String filepath) 
	{
		Map obj=new HashMap();
		YAML yaml=new YAML();
		try 
		{
			obj=(Map)YAML.load(new FileInputStream(new File(filepath)));
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj.get(key);
	}
}

	