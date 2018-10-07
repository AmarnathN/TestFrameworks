package com.selenium.actions;

import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.frameworkUtilities.ERRORCODES;
import com.helpers.ElementLocator;

public class WebUIElement {
    private static final Logger LOGGER = Logger.getLogger(WebUIElement.class);

    private ElementLocator elementLocator   = new ElementLocator();
    private WebElement webElement;

    public int enterText (WebDriver driver, String fieldName, String inputData){

        int exitcode = ERRORCODES.DEFAULT_CODE ;
         try{
            webElement = null;
            webElement = elementLocator.findElement(driver,fieldName);
            if(!inputData.isEmpty() &&  ( webElement!=null ) ){
                webElement.clear();
                webElement.sendKeys(inputData);
                exitcode =ERRORCODES.PASS;
                LOGGER.info("Data has been sent to Element");
            }else{
                exitcode = ((webElement!=null)? ERRORCODES.ELEMENT_NOT_FOUND :ERRORCODES.TESTDATA_NOT_FOUND);
            }
        }catch (Exception e ){
            LOGGER.error("Exception in Entering Keys : " + e);
        }
        return exitcode;
  }

    public int clickElement (WebDriver driver, String fieldName){

        int exitcode = ERRORCODES.DEFAULT_CODE ;
        try{
            webElement = null;
            webElement = elementLocator.findElement(driver,fieldName);
            if(( webElement!=null ) ){
            	 int attempts = 0;
            	 
                 while(attempts < 2) {
                     try {
                    	 webElement.click();
                         break;
                     } catch(StaleElementReferenceException e) {
                     }
                     attempts++;
                 }
                exitcode =ERRORCODES.PASS;
                LOGGER.info("Clicked on the element " + webElement);
            }else{
                exitcode = ERRORCODES.ELEMENT_NOT_FOUND ;
            }
        }catch (Exception e ){
           LOGGER.error("Exception in Clicking Webelement : " + webElement + " "+ e);
        }

        return exitcode;

    }


    public Boolean quickVerifyElementPresent(WebDriver driver, String fieldName) {
        Boolean flag = false ;
        try{
            webElement = null;
            webElement = elementLocator.findElement(driver,fieldName);
            if(( webElement!=null ) ){
                   flag = true;
            }

        }catch (Exception e ){
            System.out.println(this.getClass().getName() + " : " + "Exception in quickVerify : " + e);
        }

        return flag;

    }

    public WebDriver switchWindowByTitleNotContains(WebDriver driver, String nonTitleString) {
        int exitCode = ERRORCODES.DEFAULT_CODE;
        WebDriver SwitchedDriver = null;
        try {

            if (driver!=null) {
                    Set<String> winHandles = driver.getWindowHandles();

                    for (String winHandle : winHandles) {
                        System.out.println("Handle :" + winHandle);
                       WebDriver popup = driver.switchTo().window(winHandle);
                       String browserName = ((RemoteWebDriver) popup).getCapabilities().getBrowserName();
                        System.out.println(browserName + " : : " + popup.getTitle());
                        if (!(popup.getTitle().trim().contains(nonTitleString))) {

                                SwitchedDriver = popup;
                                break;
                        } 
                    }
                } else {
                     LOGGER.info("NO Driver available ");
                   }
                } catch (Exception e) {
              
                LOGGER.error("Unknown Exception in Switching window by Title not contains " + e);
                
            }
                return SwitchedDriver;


}

    public void navigateToURL(WebDriver driver, String url) {
    	try {
    			driver.get(url);
    		   Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
    		    String browserName = cap.getBrowserName().toLowerCase();
    		    System.out.println(browserName);
    		    String os = cap.getPlatform().toString();
    		    System.out.println(os);
    		    String v = cap.getVersion().toString();
    		    System.out.println(v);
			//if ((((RemoteWebDriver) getWebdriver()).getCapabilities().getBrowserName()).equalsIgnoreCase("internet explorer")) {
			if (browserName.trim().equalsIgnoreCase("INTERNETEXPLORER")
					||browserName.trim().equalsIgnoreCase("REMOTEINTERNETEXPLORER")) {
				
				if (driver.findElement(By.id("overridelink")).isDisplayed())
				{
					//elementLocator.ignoreSecurityCertificateErrorInIE(testcaseStep.getTestDataValue());
					
					 driver.get("javascript:document.getElementById('overridelink').click();");
					
				}
				
				
				

    
			}
    	}catch(Exception e) {
    		
    	}
    	
    	}
    
    
 }
