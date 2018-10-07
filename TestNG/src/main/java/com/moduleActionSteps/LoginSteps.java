package com.moduleActionSteps;





import static org.assertj.core.api.Assertions.assertThat;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.frameworkUtilities.ExecutionSettings;
import com.pages.BasePageofOrigination;
import com.pages.LandingPage;
import com.pages.OBPHomePage;






public class LoginSteps{
	
	private static final Logger LOGGER = Logger.getLogger(LoginSteps.class);
	
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
