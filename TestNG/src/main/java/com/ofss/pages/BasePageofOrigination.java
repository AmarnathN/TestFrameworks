package com.ofss.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.ofss.moduleActionSteps.BaseTest;
import com.ofss.moduleActionSteps.PageObject;
import com.ofss.selenium.actions.WebUIElement;

public class BasePageofOrigination extends PageObject{
	private static final Logger LOGGER = Logger.getLogger(BasePageofOrigination.class.getName());
	
	private WebUIElement webUIElement= new WebUIElement();
	    
	public void openURL(String URL) {
    	try {
    		LOGGER.info("Level4 :" + getDriver());
    		getDriver().get(URL);
    	}catch(Exception e) {
    		LOGGER.error("Unable to open URL Error: " + e );
    	}
    }
    	
    public void loginToApplicationForm(String username, String password) {
        try {
            webUIElement.enterText(getDriver(),"RTD_Input_WithPlaceHolder|Username",username);

            webUIElement.enterText(getDriver(),"RTD_Input_WithPlaceHolder|Password",password);

            webUIElement.clickElement(getDriver(),"Login_Button");

        }catch (Exception e){
           LOGGER.error("Exception in Logging Into Application Form " +  e);
        }

    }

}
