package com.ofss.serenity.pages;

import org.apache.log4j.Logger;

import com.ofss.Utilities.frameworkUtilities.TestData;
import com.ofss.selenium.actions.WebUIElement;
import net.serenitybdd.core.pages.PageObject;


//@DefaultUrl("https://whf00bpq.in.oracle.com:9001/com.ofss.fc.ui.view/faces/main.jspx")
public class CommonPageActions extends PageObject {
	
	private static final Logger LOGGER = Logger.getLogger(CommonPageActions.class.getName());
	
    private WebUIElement webUIElement = new WebUIElement();
    
  
    public void openURL() {
    
      		getDriver().quit();
      		webUIElement.navigateToURL(getDriver(), TestData.getInstance().getTestData("GV_OBP_APPLICATIONURL"));    		
 	
    }
    
    public void loginToApplicationForm(String username, String password) {
        	
            webUIElement.enterText(getDriver(),"RTD_Input_WithPlaceHolder|Username",username);

            webUIElement.enterText(getDriver(),"RTD_Input_WithPlaceHolder|Password",password);

            webUIElement.clickElement(getDriver(),"Login_Button");

    }
    
    public void GoToNextPage() {
    		webUIElement.clickElement(getDriver(), "RTD_Button_LinkWithText|Next Step");
    		
    }
    
    
    
   
}
