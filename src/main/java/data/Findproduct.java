package data;

import utils.YAML;

public class Findproduct 
{
		public Logincredentials Logincredentials;
		public String searchstring;
		
		public static Findproduct fetch(String key)
		{
			Findproduct data=(Findproduct)YAML.loadYaml(key, System.getProperty("user.dir")+"/resources/search.yaml");
			if(data!=null) 
			{
				return data;
			}else 
			{
				return null;
			}
		}
}
