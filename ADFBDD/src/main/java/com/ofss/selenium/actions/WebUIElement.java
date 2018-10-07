package com.ofss.selenium.actions;

import com.ofss.Utilities.frameworkUtilities.ERRORCODES;
import com.ofss.Utilities.frameworkUtilities.ExecutionSettings;

import com.ofss.selenium.helpers.ElementLocator;
import net.serenitybdd.core.SerenitySystemProperties;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.ThucydidesSystemProperty;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WebUIElement {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebUIElement.class);

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
                webElement.click();
                exitcode =ERRORCODES.PASS;
            }else{
                exitcode = ERRORCODES.ELEMENT_NOT_FOUND ;
            }
        }catch (Exception e ){
           LOGGER.error("Exception in Clicks : " + e);
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

    public int switchWindowByTitleNotContains(WebDriver driver, String nonTitleString) {
        int exitCode = ERRORCODES.DEFAULT_CODE;

        try {
            Thread.sleep(2000L);
            ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {

                    Set<String> Winhandlers = driver.getWindowHandles();
                    System.out.println("NO. =" + Winhandlers.size());
                    if (Winhandlers.size() > 1) {
                        return true;
                    } else {
                        return false;
                    }
                }
            };

            WebDriverWait wait = new WebDriverWait(driver, ExecutionSettings.getInstance().getElementTimeout(), 100L);
           // WebDriverWait wait = new WebDriverWait(driver,10, 100L);
            wait.until(expectation);

        } catch (Exception e) {
            System.out.println(this.getClass().getName() + " : " + "Exception in finding out the No of windows " + e);
        }

        try {

            if (driver!=null) {
                    Set<String> winHandles = driver.getWindowHandles();

                    for (String winHandle : winHandles) {
                        System.out.println("Handle :" + winHandle);
                       WebDriver popup = driver.switchTo().window(winHandle);
                        System.out.println(popup.getTitle());
                        if (!(popup.getTitle().trim().contains(nonTitleString))) {

                            try {

                                if (SerenitySystemProperties.getProperties().getValue(ThucydidesSystemProperty.WEBDRIVER_DRIVER).equalsIgnoreCase("IEXPLORER")) {
                                    driver.manage().window().maximize();
                                    driver.manage().timeouts().implicitlyWait(1L, TimeUnit.SECONDS);
                                    Thread.sleep(1000L);
                                    if (popup.getTitle().trim().contains("Certificate Error")) {
                                        driver.get("javascript:document.getElementById('overridelink').click();");
                                    }

                                }

                            } catch (Exception e) {
                               // LOGGER.error("Error in Handling Certificate Error for IE :" + e.getStackTrace());
                                //Just Log and do nothing
                            }

                            exitCode = ERRORCODES.PASS;
                            break;
                        } else {
                            exitCode = -2;
                        }
                    }
                } else {
                    exitCode = ERRORCODES.TESTDATA_NOT_FOUND; //Testdata not found
               //     LOGGER.info("Testdata not found for" + testcaseStep.getTestcaseID() + " @step# " + testcaseStep.getStepID() + "for TestdataName"
                //            + testcaseStep.getTestDataName());
                //    testcaseStep.setErrDescription("Testdata not found ");
                }
                } catch (Exception e) {
                exitCode = ERRORCODES.UNKNOWN_ERROR_IN_KEYWORD;
                //testcaseStep.setErrDescription("Unknown Exception in " + testcaseStep.getKeyword() + ": " + e.getMessage());
                //LOGGER.error("Unknown Exception in " + testcaseStep.getKeyword() + ": " + e.getMessage());
            }

                if (exitCode == -2) {
             //   LOGGER.error("no windows found without the exptected title" + testcaseStep.getTestDataValue());
             //   testcaseStep.setErrDescription("no windows found without the exptected title");
            }

                if (exitCode == 0) {
               // testcaseStep.setStepResult(FrameworkConstants.TestResultSatus.PASS.toString());
            } else {
                //testcaseStep.setStepResult(FrameworkConstants.TestResultSatus.FAIL.toString());
                //testcaseStep.setErrorCode(exitCode);
            }
                return exitCode;


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
