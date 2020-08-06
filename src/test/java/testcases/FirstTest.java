package testcases;


import org.testng.annotations.Test;

import utils.Initialize;

public class FirstTest 
{
	
	@Test(groups= {"searchProduct"})
	public void searchProduct() 
	{
		Initialize utils=new Initialize();
		utils.getDriverForWeb();		
	}
	
	

}