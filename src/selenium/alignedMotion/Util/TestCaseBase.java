package selenium.alignedMotion.Util;

import java.awt.AWTException;
import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import selenium.alignedMotion.POM.AMotionManageTeacher_POM;
import selenium.alignedMotion.Util.BrowserFactory;
import selenium.alignedMotion.Util.Utility;
import selenium.alignedMotion.Util.ProjectRoutines;


//this class refers to the structure which comes first during test execution


public class TestCaseBase 
{
	//declaration of all static variables (all classes under POM package should be here
	
	public static WebDriver driver;
	public static AMotionManageTeacher_POM aMotionManageTeacher ;

	@BeforeTest
	public void setupTest()
	{
		driver = BrowserFactory.initializeDriver();
		ProjectRoutines.loginRoutine(driver);
	}
	
	@BeforeMethod
	public void beforeTest(Method method)
	{
		//object instantiation
		aMotionManageTeacher = new AMotionManageTeacher_POM();

		Utility.defaultBeforeMethod(method);
		Utility.temporaryWaitSolutionShort();
	}
	
	@AfterMethod
	public void afterMethod(Method method, ITestResult result) throws IOException, AWTException
	{		
		if (ITestResult.FAILURE == result.getStatus())
		{
		//	Utility.takeScreenshot(driver, method, "FAILED");
		}
		
		else
		{
		//	Utility.takeScreenshot(driver, method, "SUCCESS");
		}
	}
	
	@AfterTest
	public void afterTest ()
	{
		//driver.quit();
	}
}
