package com.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.frameworkUtilities.PageObject;
import com.helpers.ElementLocator;
import com.helpers.fluentWait;

public class GoogleHomePage extends PageObject {
	
	
	private static final Logger LOGGER = Logger.getLogger(GoogleHomePage.class);
    
	public void openURL(String URL) {
    	navigateToURL(URL);
    }
	
	public void verifyOnHomePage() {
		 System.out.println("I am here");
		// new fluentWait(getDriver()).waitUntil("elementToBeVisible", ElementLocator.getInstance().getLocator("RTD_Input_WithLabel|Google Search"));
		 quickVerifyElementPresent("RTD_Input_WithLabel|Google Search");
    }
    	
    public void searchText(String searchText) {
        try {
            enterText("RTD_Input_WithTitle|Search",searchText);
        }catch (Exception e){
           LOGGER.error("Exception in Entering Serach Text " +  e);
        }

    }
    
    public void search() {
        try {
          clickElement("RTD_Input_WithLabel|Google Search");
      
        }catch (Exception e){
           LOGGER.error("Exception in Searching " +  e);
        }

    }
    
  
}
