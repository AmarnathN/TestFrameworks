package com.ofss.serenity.pages;

import com.ofss.Utilities.frameworkUtilities.ExecutionSettings;
import com.ofss.selenium.actions.WebUIElement;
import com.ofss.selenium.helpers.DriverManager;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;


//@DefaultUrl("https://whf00bpq.in.oracle.com:9001/com.ofss.fc.ui.view/faces/main.jspx")
public class BasePageofOrigination extends PageObject {
    private WebUIElement webUIElement = new WebUIElement();
    
 
    
    public void openURL() {
    
      	getDriver().quit();
    	getDriver().get(ExecutionSettings.getApplicationURL("OBP"));
    
    }
    
    public void loginToApplicationForm(String username, String password) {
        try {
            webUIElement.enterText(getDriver(),"RTD_Input_WithPlaceHolder|Username",username);

            webUIElement.enterText(getDriver(),"RTD_Input_WithPlaceHolder|Password",password);

            webUIElement.clickElement(getDriver(),"Login_Button");

        }catch (Exception e){
            System.out.println(this.getClass().getName() + " : " +  e.getMessage());
        }

    }

}
