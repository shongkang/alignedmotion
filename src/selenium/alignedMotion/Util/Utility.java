package selenium.alignedMotion.Util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

//import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import selenium.alignedMotion.Util.Utility;
import selenium.alignedMotion.Util.ProjectConfiguration;
/*
This class contains configured methods under selenium commands, this way we eliminate using same configuration and writing it again and again
This class makes it easy to configure and maintain methods to be used under almost any classes especially POM classes.
Methods are used by using "Utility.(name of method)"
*/
public class Utility
{


	//	INTERACTION METHODS
	//	------------------------------------------------------------------------------------------------------------------------------------
	//	------------------------------------------------------------------------------------------------------------------------------------
	
	public static void goToURL(WebDriver driver, String url)
	{
		driver.navigate().to(url);
		Utility.logger("Navigating to :" + url);
	}
	public static WebElement retrieveThis (WebDriver driver, By byFinder )
	{
		try
		{
			(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(byFinder));
			WebElement e = driver.findElement(byFinder);
			Utility.logger("Retrieve this element " + e.getText() );
			return e;
		}
		catch(NoSuchElementException e)
		{
			Utility.logger("FAILED: UNABLE TO RETRIEVE ELEMENT, See error below for more details");
			throw e;
		}
	}
	public static void selectThis(WebDriver driver, By byFinder, String value, String message)
	{
		selectThis(driver, byFinder, value);
		Utility.logger("Selecting " + String.valueOf(value) + " in " + message + " Select Field");
	}	
	public static void selectThis(WebDriver driver, By byFinder, String value)
	{
		int attempts = 0;
		while(attempts <= 3)
		{
			try
			{
				(new WebDriverWait(driver, ProjectConfiguration.driverWait_Duration)).until(ExpectedConditions.elementToBeClickable(byFinder));
				Select webElement = new Select(driver.findElement(byFinder));
				webElement.selectByVisibleText(value);
				break;
			}
			catch(ElementNotSelectableException | NoSuchElementException | StaleElementReferenceException  e)
			{
				Utility.logger("Attempting to select again");
				Utility.temporaryWaitSolutionShort();
				attempts++;
				if (attempts > 3 )
				{
					Utility.logger("FAILED: Unable to Select Element, See below for more details : " + e);
					throw e;
				}
			}
		}

	}
	public static void pressThis( WebDriver driver, By byFinder, Keys key)
	{
		try
		{
			(new WebDriverWait(driver, ProjectConfiguration.driverWait_Duration)).until(ExpectedConditions.presenceOfElementLocated(byFinder)).sendKeys(key);;
			Utility.logger("Press key");
		}
		catch(NoSuchElementException e )
		{
			Utility.logger("FAILED: Unable to Press the Key, See below for more details :" + e);
			throw e;
		}
	}
	public static void typeThis ( WebDriver driver, By byFinder, Object value, String message )
	{
		typeThis(driver, byFinder, value);
		Utility.logger("Typing " + String.valueOf(value) + " in " + message + " field");
	}

	public static void typeThis ( WebDriver driver, By byFinder, Object value ) 
	{
		int attempts = 0;
		while(attempts <= 3)
		{
			try
			{
				(new WebDriverWait(driver, ProjectConfiguration.driverWait_Duration)).until(ExpectedConditions.presenceOfElementLocated(byFinder)).clear();
				driver.findElement(byFinder).clear();
				driver.findElement(byFinder).sendKeys(String.valueOf(value));	
				break;
			}
			catch(NoSuchElementException | StaleElementReferenceException e)
			{
				Utility.logger("Attempting to select again");
				Utility.temporaryWaitSolutionShort();
				attempts++;
				if (attempts > 3 )
				{
					Utility.logger("FAILED: Unable to type on the element, See below for more details : " + e);
					throw e;
				}
			}
		}
	}	

	public static void typeAndCompleteThis ( WebDriver driver, By byFinder, Object value )
	{
		Utility.typeThis(driver, byFinder, value);
		Utility.pressThis(driver, byFinder, Keys.ENTER);
	}
	public static void clickThis (WebDriver driver, By byFinder, String message )
	{
		clickThis(driver, byFinder);
		Utility.logger("Clicking " + message);
	}
	public static void clickThis ( WebDriver driver, By byFinder )
	{
		int attempts = 0;
		while(attempts <= 3)
		{
			try
			{
				(new WebDriverWait(driver, ProjectConfiguration.driverWait_Duration)).until(ExpectedConditions.elementToBeClickable(byFinder)).click();
				break;
			}
			catch(ElementNotVisibleException | NoSuchElementException e)
			{

				{
					Utility.logger("Attempting to select again");
					Utility.temporaryWaitSolutionShort();
					attempts++;
					if (attempts > 3 )
					{
						Utility.logger("FAILED: Unable to click element, See below for more details: " + e);
						throw e;	
					}
				}
			}
		}
	}		

	
	public static void hoverThis  (WebDriver driver, By byFinder)
	{
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(byFinder)).build().perform();
		Utility.temporaryWaitSolutionShort();
	}
	public static void pressEnterKey_PopUp(WebDriver driver) 
	{
		try
		{
			Robot sam;
			sam = new Robot();
			sam.keyPress(KeyEvent.VK_ENTER);
		}

		catch (AWTException i) 
		{
			Utility.logger("Problem Encountered");
		}
	}

	public static void scrollUp(WebDriver driver)
	{
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0, 250)");
		//temporaryWaitSolutionShort();
	}

	public static void scrollDown(WebDriver driver)
	{
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		// temporaryWaitSolutionShort();
	}

	public static void zoomOut(WebDriver driver)
	{
		//WebElement try = driver.findElement(By.xpath("html/body/div[3]/div[2]/div/div/div/div/div/div[1]/div[3]")).click();
		Keys.chord(Keys.CONTROL, Keys.SUBTRACT);
	}

	public static void zoomNormal(WebDriver driver)
	{
		WebElement html = driver.findElement(By.tagName("html"));
		html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
	}
	

	//	CHECKING METHODS
	//	------------------------------------------------------------------------------------------------------------------------------------
	//	------------------------------------------------------------------------------------------------------------------------------------	
	public static void checkThis(WebDriver driver, By byFinder)
	{
		try 
		{
			(new WebDriverWait(driver, ProjectConfiguration.driverWait_Duration)).until(ExpectedConditions.presenceOfElementLocated(byFinder));
			driver.findElement(byFinder);
			Utility.logger("Element has been located");
		} 
		catch (NoSuchElementException | AssertionError e) 
		{ 
			Utility.logger("Element could not be located, See below for more details" + e );
			throw e;
		}
	}
	public static void checkThisShouldNotBePresent(WebDriver driver, By byFinder)
	{
		try 
		{
			Utility.temporaryWaitSolutionShort();
			driver.findElement(byFinder);
			Assert.fail("Element was found, Element should be be visible");
		} 
		catch (NoSuchElementException e) 
		{ 
			Utility.logger("Element is not visible");	 
		}
	}
	public static void containsString( String actual, String expected)
	{
		try
		{
			Assert.assertTrue(expected.contains(actual));
			Utility.logger("Strings Matched : " + actual + " & " + expected);
		}
		catch(AssertionError e)
		{
			Utility.logger("FAILED : Strings failed to match : " + actual + " & " + expected);
			throw e;
		}
	}
	public static void compareValues( int actual, int expected )
	{
		try
		{
			Assert.assertEquals(actual, expected);
			Utility.logger("Strings Matched : " + actual + " & " + expected);
		}
		catch(AssertionError e)
		{
			Utility.logger("FAILED : Strings failed to match : " + actual + " & " + expected);
			throw e;
		}
	}
	public static void compareValues( double actual, double expected )
	{
		try
		{
			Assert.assertEquals(actual, expected);
			Utility.logger("Strings Matched : " + expected + " & " + actual);
		}
		catch(AssertionError e)
		{
			Utility.logger("FAILED : Strings failed to match : " + actual + " & " + expected);
			throw e;
		}
	}
	public static void compareStrings ( String actual, String expected )
	{
		try
		{
			Assert.assertTrue(actual.toLowerCase().contains(expected.toLowerCase()));
			Utility.logger("Strings Matched : " + actual + " & " + expected);		
		}
		catch(AssertionError e)
		{
			Utility.logger("FAILED : Strings failed to match : " + actual + " & " + expected);
			throw e;
		}
	}

	//	UTILITY METHODS
	//	------------------------------------------------------------------------------------------------------------------------------------
	//	------------------------------------------------------------------------------------------------------------------------------------

	public static String retrieveStringSpecific ( String sentence, int fromWhere, int toWhere)
	{
		return sentence.substring(fromWhere, toWhere);
	}
	public static String retrieveStringSpecific ( String sentence, int fromWhere)
	{
		return sentence.substring(fromWhere);
	}	
	public static double retrieveNumbers (String sentence)
	{
		sentence = sentence.replaceAll("[^0-9]","");	
		try
		{
			double value = Double.parseDouble(sentence);
			return value;
		}
		catch(NullPointerException e)
		{
			Utility.logger(e.toString());
			return 0;
		}
	}
	public static double retrieveNumbers (WebDriver driver, By byFinder)
	{
		String sentence = Utility.retrieveThis(driver, byFinder).getText().replaceAll("[^0-9]","");
		try
		{
			double value = Double.parseDouble(sentence);
			return value;
		}
		catch(NullPointerException e)
		{
			Utility.logger(e.toString());
			return 0;
		}
	}
	public static void logger(String log)
	{
		Reporter.log("<p>  " + log + "  </p>");
		System.out.println(log);
	}
	public static void imageLogger(String log)
	{
		Reporter.log(log);
	}


	public static void temporaryWaitSolutionShort()
	{
		try {
			Thread.sleep(3000);
			Utility.logger("Finished waiting");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	public static void temporaryWaitSolutionLong()
	{
		try 
		{
			Thread.sleep(8000);
			Utility.logger("Finished waiting");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void temporaryWaitSolution()
	{
		temporaryWaitSolution(50);
	}
	public static void temporaryWaitSolution(int seconds)
	{
		try {
			Thread.sleep(seconds * 1000);
			Utility.logger("Wait for " + seconds + " seconds");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	 
	private static Random random = new Random((new Date()).getTime());
	public static String generateRandomString(final int length) {
		final char[] values = {'a','b','c','d','e','f','g','h','i','j',
				'k','l','m','n','o','p','q','r','s','t',
				'u','v','w','x','y','z','0','1','2','3',
				'4','5','6','7','8','9','A','B','C','D',
				'E','F','G','H','I','J','K','L','M','N',
				'O','P','Q','R','S','T','U','V','W','X',
				'Y','Z'};
		String out = "";

		for (int i=0;i<length;i++) 
		{
			final int idx = random.nextInt(values.length);
			out += values[idx];
		}
		return out;
	}
	public static int generateRandomNumber(int min, int max)
	{
		return ThreadLocalRandom.current().nextInt(min, max);	
	}
	public static void takeScreenshot(WebDriver driver, Method method, String result) throws IOException
	{		
		File filepath = new File(ProjectConfiguration.screenshotFilePath + result + "-" + method.getName() + ProjectConfiguration.screenshotFileType);
		String screenshotpath = ProjectConfiguration.reportFilePath + result + "-" + method.getName() + ProjectConfiguration.screenshotFileType;
		Utility.imageLogger("<br> <img src=" + screenshotpath  + "  width='1100' height='900'/> </br>");
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Utility.logger("Performing Screenshot");
		//FileUtils.copyFile(scrFile, filepath );	
		FileHandler.copy(scrFile, filepath);
	}	
	public static void takeScreenshot(WebDriver driver, String description, String result) throws IOException
	{
		File filepath = new File(ProjectConfiguration.screenshotFilePath + result + "-" + description + ProjectConfiguration.screenshotFileType);
		String screenshotpath = ProjectConfiguration.reportFilePath + result + "-" + description + ProjectConfiguration.screenshotFileType;
		Utility.imageLogger("<br> <img src=" + screenshotpath  + "  width='1100' height='900'/> </br>");
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Utility.logger("Performing Screenshot");
		//FileUtils.copyFile(scrFile, filepath );	
		FileHandler.copy(scrFile, filepath);
	}


	//  TESTNG RELATED METHODS	
	//	------------------------------------------------------------------------------------------------------------------------------------
	//	------------------------------------------------------------------------------------------------------------------------------------
	public static void defaultBeforeMethod(Method method)
	{
		Utility.logger("---------------------" + method.getName() + "---------------------");
		Utility.logger("---------------------TEST STARTING---------------------");
	}
	public static void defaultAfterMethod()
	{
		Utility.logger("---------------------TEST END---------------------");
	}

	//	------------------------------------------------------------------------------------------------------------------------------------
	//	------------------------------------------------------------------------------------------------------------------------------------

}
