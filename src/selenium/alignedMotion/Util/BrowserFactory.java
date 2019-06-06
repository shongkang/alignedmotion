package selenium.alignedMotion.Util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


//this class refers to browserdriver initialization and path

public class BrowserFactory 
{
	public static WebDriver initializeDriver()
	{	
		System.out.println("Running...");
		
		//INSERT CHROME DRIVER PATH BELOW
		System.setProperty("webdriver.chrome.driver","C:/Users/Tristan/Documents/selenium/chromedriver_win32/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;

	}
}
