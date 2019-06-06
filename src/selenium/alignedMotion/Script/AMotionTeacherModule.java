package selenium.alignedMotion.Script;

import selenium.alignedMotion.Util.TestCaseBase;
import org.testng.annotations.Test;
import selenium.alignedMotion.Util.Utility;

public class AMotionTeacherModule extends TestCaseBase

{
	
    /*This class is for listing all scenarios from the test cases; 
	Also for easier tracking on which step the error occured by detailing the
	step by step below. This way non-technical people will still be able to understand/assess the flow of the program
	*/
	
	//-----------------------------MANAGE TEACHER------------------------------------
		@Test //(priority=1)
	
		public void editPersonalInfo()
		{
			Utility.logger("TEST SCENARIO 1: Edit Personal Info");
			Utility.logger("STEP 1: LOG IN ACCOUNT");
			Utility.logger("STEP 2: CLICK PROFILE MENU");
			Utility.logger("STEP 3: CLICK MY ACCOUNT TAB");
			aMotionManageTeacher.edit_PersonalInfo_POM(driver);
			
			
		}
		
}