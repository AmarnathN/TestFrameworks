package com.ofss.moduleActionSteps;





import static org.assertj.core.api.Assertions.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.ofss.frameworkUtilities.ExecutionSettings;
import com.ofss.pages.BasePageofOrigination;
import com.ofss.pages.LandingPage;
import com.ofss.pages.OBPHomePage;






public class LoginSteps{
	
	private static final Logger LOGGER = LogManager.getLogger(LoginSteps.class);
	
	BasePageofOrigination basePageofOrigination = new BasePageofOrigination();
	OBPHomePage obpHomePage = new OBPHomePage();
	LandingPage landingPage = new LandingPage();

	public void isOnTheHomePage() {
		LOGGER.info("Level3 :" + BaseTest.getInstance().getDriver());

	
		basePageofOrigination.openURL(ExecutionSettings.getApplicationURL("OBP"));
   
    }
 

	public void logsIntoApplication(String userName,String passWord) {

        obpHomePage.loginIntoApplication(userName,passWord);
        OutputIs("This is OutPut Step");
        
        
      
    }

    public void OutputIs(String userName) {
       
    
		//new SoftAssertions().assertThat(false);
    	assertThat(false);
    }
  
    public void shouldBeOnLandingPage() {
       assertThat(obpHomePage.verifyFastPathFieLdEnabled());


    }

    public void goToPageUsingFastPath(String fastPath) {

        obpHomePage.goToPageUsingFastPath(fastPath);

    }

   
    public void loginToApplicationForm(String username, String password) {
        landingPage.loginToApplicationForm(username,password);
    }
}
