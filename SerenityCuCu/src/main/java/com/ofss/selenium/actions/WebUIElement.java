package com.ofss.selenium.actions;

import com.ofss.Utilities.frameworkUtilities.ERRORCODES;
import com.ofss.Utilities.frameworkUtilities.FrameWorkConstants;
import com.ofss.selenium.helpers.ElementLocator;
import com.ofss.selenium.helpers.fluentWait;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;


import java.util.Set;

public class WebUIElement {
    private static final Logger LOGGER = Logger.getLogger(WebUIElement.class.getName());

    private ElementLocator elementLocator   = new ElementLocator();
    
    
   
	public void enterText(WebDriver driver, String fieldName, String inputData){
    	 LOGGER.info("Data : " + inputData + " has been sent to Element");
         try{
        	 LOGGER.info("Data : " + inputData + " has been sent to Element");
           WebElement webElement = elementLocator.findElement(driver,fieldName);
           LOGGER.info(webElement.toString());
            webElement.clear();
			webElement.sendKeys(inputData);
			LOGGER.info("Data : " + inputData + " has been sent to Element");
        }catch (Exception e ){
            LOGGER.error("Exception in Entering Keys : " + e);
        }
   
  }

    public int clickElement (WebDriver driver, String fieldName){

        int exitcode = ERRORCODES.DEFAULT_CODE ;
        try{
            WebElement webElement = elementLocator.findElement(driver,fieldName);
            if(( webElement!=null ) ){
                webElement.click();
                exitcode =ERRORCODES.PASS;
            }else{
                LOGGER.error("Element with field Definition : " + fieldName + " Not Found to Click");
            }
        }catch (Exception e ){
           LOGGER.error("Exception in Clicks : " + e);
        }

        return exitcode;

    }


    public Boolean quickVerifyElementPresent(WebDriver driver, String fieldName) {
        Boolean flag = false ;
        try{
        	WebElement webElement = elementLocator.findElement(driver,fieldName);
            if(( webElement!=null ) ){
                   flag = true;
            }else {
            	LOGGER.error("Element with field Definition : " + fieldName + " Not Found ");
            }

        }catch (Exception e ){
            System.out.println(this.getClass().getName() + " : " + "Exception in quickVerify : " + e);
        }

        return flag;

    }

    public WebDriver switchWindowByTitleNotContains(WebDriver driver, String nonTitleString) {
       
        WebDriver SwitchedDriver = driver;
        try {
        	
        		new fluentWait(driver).waitUntil("waitTillNewWindowOpens");

				
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
               
                //testcaseStep.setErrDescription("Unknown Exception in " + testcaseStep.getKeyword() + ": " + e.getMessage());
                LOGGER.error("Unknown Exception in Switching Window : " + e.getMessage());
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
    
    public void waitForElementToBeVisible (WebDriver driver, String fieldName){
    	try {
    		new fluentWait(driver).waitUntil("elementToBeVisible", fieldName);
    	}catch(Exception e) {
    		LOGGER.error("Unknown Exception In Waiting for Element to be visible : " + e);
    	}
    }   
    
    public void selectFromDropdownByLabel(WebDriver driver, String fieldName, String labelText) {
    	try {
    		
    		WebElement webElement = elementLocator.findElement(driver, "PartyType_DropdownList");
    		if(webElement!=null) {
    		
    			if(webElement.getTagName().equalsIgnoreCase("SELECT")) {
    				
    				Select targetSelector = new Select(webElement);
					
        			targetSelector.selectByVisibleText(labelText);
			    	LOGGER.info("selectFromDropdownByLabel operation is completed : Found Label - " + labelText );
			    	
			    	new fluentWait(driver).waitUntil(FrameWorkConstants.FluentWaitFunctions.PAGETOLOAD.toString());
        			
    			}else {
    				LOGGER.error("Invalid Tag found ::: Expected Select Tag Element for given field Definition : " + fieldName );
    			}
    		}else {
    			LOGGER.error("NO Element found with field Definition : " + fieldName );
            }
    	}catch(Exception e) {
    		LOGGER.error("Unknown Exception in Selecting Value from List by Index : " + e);
    	}
    }
    
    public void selectFromDropdownByLabelContains(WebDriver driver, String fieldName, String labelText) {
    	try {
    		
    		WebElement webElement = elementLocator.findElement(driver, fieldName);
    		if(webElement!=null) {
    		
    			if(webElement.getTagName().equalsIgnoreCase("SELECT")) {
    				
    				Select targetSelector = new Select(webElement);
					
        			int optionsCount = targetSelector.getOptions().size();
					boolean optionFlg = false ;
						
								for (int d = 0; d <= (optionsCount - 1); d++) {
									String actOptionText = targetSelector.getOptions().get(d).getText().trim();
									
									if (actOptionText.contains(labelText)) {
										targetSelector.selectByIndex(d);
										optionFlg = true;
										break;
									} else {
										optionFlg = false;
									}
								}
								
								if (optionFlg) {
									LOGGER.info(" selectFromDropdownByLabelContains operation is completed : Found Label Containing - " + labelText);
								} else {
									LOGGER.error("SelectByLabelContains operation failure : Unable to Find Label that contains : " + labelText);
				
								}
        			
        			new fluentWait(driver).waitUntil(FrameWorkConstants.FluentWaitFunctions.PAGETOLOAD.toString());
        			
    			}else {
    				LOGGER.error("Invalid Tag found ::: Expected Select Tag Element for given field Definition : " + fieldName );
    			}
    		}else {
    			LOGGER.error("NO Element found with field Definition : " + fieldName );
            }
    	}catch(Exception e) {
    		LOGGER.error("Unknown Exception in Selecting Value from List by Index : " + e);
    	}
    	
    }
    
    public void selectFromDropdownByIndex(WebDriver driver, String fieldName, String indexValue) {
    	try {
    		
    		WebElement webElement = elementLocator.findElement(driver, fieldName);
    		if(webElement!=null) {
    		
    			if(webElement.getTagName().equalsIgnoreCase("SELECT")) {
    				
    				Select targetSelector = new Select(webElement);
				 	targetSelector.selectByIndex(Integer.parseInt(indexValue));
    				LOGGER.info(" Selection of value at Index " + indexValue + " is completed");
    						
        			new fluentWait(driver).waitUntil(FrameWorkConstants.FluentWaitFunctions.PAGETOLOAD.toString());
        			
    			}else {
    				LOGGER.error("Invalid Tag found ::: Expected Select Tag Element for given field Definition : " + fieldName );
    			}
    		}else {
    			LOGGER.error("NO Element found with field Definition : " + fieldName );
            }
    	}catch(Exception e) {
    		LOGGER.error("Unknown Exception in Selecting Value from List by Index : " + e);
    	}
    
    }
    
    public void selectFromDropdownByValue(WebDriver driver, String fieldName, String selectionvalue) {
    	try {
    		
    		WebElement webElement = elementLocator.findElement(driver, fieldName);
    		if(webElement!=null) {
    		
    			if(webElement.getTagName().equalsIgnoreCase("SELECT")) {
    				
    				Select targetSelector = new Select(webElement);
    				targetSelector.selectByValue(selectionvalue);
    				LOGGER.info(" Selection By Value " + selectionvalue + " operation is comepleted");
    						
        			new fluentWait(driver).waitUntil(FrameWorkConstants.FluentWaitFunctions.PAGETOLOAD.toString());
        			
    			}else {
    				LOGGER.error("Invalid Tag found ::: Expected Select Tag Element for given field Definition : " + fieldName );
    			}
    		}else {
    			LOGGER.error("NO Element found with field Definition : " + fieldName );
            }
    	}catch(Exception e) {
    		LOGGER.error("Unknown Exception in Selecting Value from List by Index : " + e);
    	}
    	
    }
 }
