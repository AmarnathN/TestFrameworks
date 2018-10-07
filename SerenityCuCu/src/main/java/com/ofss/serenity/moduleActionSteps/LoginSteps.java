package com.ofss.serenity.moduleActionSteps;





import static org.assertj.core.api.Assertions.*;

import org.junit.Assert;

import com.ofss.serenity.pages.CommonPageActions;

import com.ofss.serenity.pages.OBPHomePage;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.StepEventBus;



public class LoginSteps {
    private CommonPageActions commonPageActions;
    private OBPHomePage obpHomePage;


 

    String value=null;
    @Step
    public void isOnTheHomePage() {
    	
        commonPageActions.openURL();
   
    }
    @Step
    public void logsIntoApplication(String userName,String passWord) {

        obpHomePage.loginIntoApplication(userName,passWord);
        OutputIs("This is OutPut Step");
        
        
      
    }
    @Step
    public void OutputIs(String userName) {
    	//StepEventBus.getEventBus().enableSoftAsserts();
    	//Assert.assertTrue(false);
		//new SoftAssertions().assertThat(false);
    	assertThat(false);
    }
    @Step
    public void shouldBeOnLandingPage() {
    	
    	Assert.assertTrue(obpHomePage.verifyFastPathFieLdEnabled());


    }
    @Step
    public void goToPageUsingFastPath(String fastPath) {

        obpHomePage.goToPageUsingFastPath(fastPath);

    }

    
}
