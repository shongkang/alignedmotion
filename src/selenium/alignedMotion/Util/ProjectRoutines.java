 package selenium.alignedMotion.Util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/*This class is used for login configuration of the test*/

public class ProjectRoutines 
{
	public static void loginRoutine(WebDriver driver )
	{
		//login locators
		By login_Username_Field = By.id("email");
		By login_Password_Field = By.id("password");
		By login_login_Button = By.id("signIn");
		
		//login variables
		String login_username = "teacher41@yopmail.com";
		String login_password = "pass2";
		
		
		driver.get(ProjectConfiguration.url);
		
		//login method
		Utility.typeThis(driver, login_Username_Field, login_username);
		Utility.typeThis(driver, login_Password_Field, login_password);
		Utility.clickThis(driver, login_login_Button);

	}
}
