package com.ofss.serenity.moduleActionSteps;





import static org.assertj.core.api.Assertions.*;

import com.ofss.serenity.pages.BasePageofOrigination;
import com.ofss.serenity.pages.LandingPage;
import com.ofss.serenity.pages.OBPHomePage;

import net.thucydides.core.annotations.Step;



public class LoginSteps {
    private BasePageofOrigination basePageofOrigination;
    private OBPHomePage obpHomePage;

    private LandingPage landingPage;

 

    String value=null;
    @Step
    public void isOnTheHomePage() {
    	
        basePageofOrigination.openURL();
   
    }
    @Step
    public void logsIntoApplication(String userName,String passWord) {

        obpHomePage.loginIntoApplication(userName,passWord);
        OutputIs("This is OutPut Step");
        
        
      
    }
    @Step
    public void OutputIs(String userName) {
       
    
		//new SoftAssertions().assertThat(false);
    	assertThat(false);
    }
    @Step
    public void shouldBeOnLandingPage() {
       assertThat(obpHomePage.verifyFastPathFieLdEnabled());


    }
    @Step
    public void goToPageUsingFastPath(String fastPath) {

        obpHomePage.goToPageUsingFastPath(fastPath);

    }

    @Step
    public void loginToApplicationForm() {
        landingPage.loginToApplicationForm("Ofssuser","welcome1");


    }
}
