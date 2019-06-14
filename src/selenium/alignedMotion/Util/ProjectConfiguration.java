
package selenium.alignedMotion.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

/*This class is for testURL configuration, screenshots filepath and settings*/


public class ProjectConfiguration 

{
		//Login URL of WebApp
		public final static String url = "https://dev.teacher.alignedmotion.com";
		//URL of Homepage, Ideally URL of where you want all your test to start 
		
		
		// Amount of time Drivers will wait for their element.
		public final static int driverWait_Duration = 30;

		//File Path where Screenshots will be saved. *NOTE that If the file is not existing it will create the path.
		public final static String filePath = "C:\\Users\\Tristan\\Documents\\selenium\\alignedmotion";
		public final static String fileType = ".jpg";	
		public final static String screenshotFilePath = "C:\\Users\\Tristan\\Documents\\selenium\\alignedmotion" + generateTimeNow() + "\\";
		public final static String screenshotFileType = ".png";
		public final static String reportFilePath = "Test-Screenshot\\" + generateTimeNow() + "\\";
		
		/*public final static String chromeDriverLocation = "AddOns\\BrowserDriver\\chromeDriver.exe";
		public final static String screenshotFilePath = "test-output\\Test-Screenshot\\" + generateTimeNow() + "\\";
		public final static String screenshotFileType = ".png";
		public final static String reportFilePath = "Test-Screenshot\\" + generateTimeNow() + "\\";
		
		*/
		public static String generateTimeNow()
		{
			Date dt = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
			return dateFormat.format(dt);
			
		}
}
