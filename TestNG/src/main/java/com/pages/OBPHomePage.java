package com.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.frameworkUtilities.PageObject;
import com.selenium.actions.WebUIElement;


public class OBPHomePage extends PageObject {

	private static final Logger LOGGER = Logger.getLogger(OBPHomePage.class);
	
	private WebUIElement webUIElement= new WebUIElement();
	
   
    public void loginIntoApplication(String username ,String password) {
    	LOGGER.info("Logging Into Application");
    	new BasePageofOrigination().loginToApplicationForm(username, password);
      
    }


	public void goToPageUsingFastPath(String fastPath) {

        webUIElement.enterText(getDriver(),"RTD_Input_WithPlaceHolder|Fast Path",fastPath);

        webUIElement.clickElement(getDriver(),"RTD_Link_WithTitle|Open page");

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        switchWindow("switchWindowByTitleNotContains", "Oracle Banking Platform");
       
    }

    public Boolean verifyFastPathFieLdEnabled() {


        return webUIElement.quickVerifyElementPresent(getDriver(),"RTD_Input_WithPlaceHolder|Fast Path");

    }

}
