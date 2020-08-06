package ebay1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testng.ITestNGListener;
import org.testng.TestNG;
import org.testng.xml.XmlPackage;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlSuite.ParallelMode;
import org.testng.xml.XmlTest;

public class Main 
{
	public static void main(String[] args) 
	{		
		Listeners listener=new Listeners();
		XmlSuite suite=new XmlSuite();
		suite.setName("Test Results");
		suite.setParallel(ParallelMode.METHODS);
		suite.setThreadCount(Integer.parseInt(ConfigProperties.THREAD_COUNT.toString()));
		List<XmlSuite> suits=new ArrayList<XmlSuite>();
		suits.add(suite);

		List<XmlPackage> xpackage=new ArrayList<XmlPackage>();
		xpackage.add(new XmlPackage(ConfigProperties.TESTNG_PACKAGE.toString()));
		
		XmlTest test=new XmlTest(suite);
		test.setPackages(xpackage);
		String groups=ConfigProperties.TESTNG_GROUP.toString();
		String groupArray[]=groups.split(",");
		List<String> includedGroups=new ArrayList<String>();
		includedGroups.addAll(Arrays.asList(groupArray));
		test.setIncludedGroups(includedGroups);
			
		TestNG tng=new TestNG();
		tng.setOutputDirectory(System.getProperty("user.dir")+"\\test-output\\");
		tng.setXmlSuites(suits);
		tng.addListener((ITestNGListener) listener);
		tng.run();
		System.exit(0);
	}
}