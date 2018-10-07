package com.ofss.selenium.helpers;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;
import com.ofss.Utilities.frameworkUtilities.ExecutionSettings;
import com.ofss.Utilities.frameworkUtilities.FrameWorkConstants;


public class fluentWait {
	final private static Logger LOGGER = LogManager.getLogger(fluentWait.class);
	public static FluentWait<WebDriver> wait;
	public static String elementLocator = null ;
	public static String js =
	        "return typeof AdfPage !== 'undefined' && " + 
	        "typeof AdfPage.PAGE !== 'undefined' && " +
	        "typeof AdfPage.PAGE.isSynchronizedWithServer === 'function' && " +
	        "(AdfPage.PAGE.isSynchronizedWithServer() || " +
	        "(typeof AdfPage.PAGE.whyIsNotSynchronizedWithServer === 'function' && " +
	        "AdfPage.PAGE.whyIsNotSynchronizedWithServer()))";
	public static String adfPageJs = "return (window.AdfPage && window.AdfPage.PAGE) != null;";
	
	public fluentWait(WebDriver driver){
		wait = new FluentWait<WebDriver>(driver);
		wait.pollingEvery(ExecutionSettings.getInstance().getPollingInterval(),  TimeUnit.NANOSECONDS);
		wait.withTimeout(ExecutionSettings.getInstance().getElementTimeout(), TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
	}
	

	static Function<WebDriver, Boolean> pageToLoad = new Function<WebDriver, Boolean>()
		{
			public Boolean apply(WebDriver driver) {
				JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
				try {
					Object result = jsDriver.executeScript(adfPageJs);
					
					if(result.equals(true)) {
						result = jsDriver.executeScript(js);
			//	        System.out.println(result);
						if(result.equals(true))
						{
			//				System.out.println("Target element found");
							return true;
							
						}
					}else {
						result =jsDriver.executeScript("return document.readyState").equals("complete");
						System.out.println(result);
						if(result.equals(true))
						{
				//			System.out.println("Target element found");
							return true;
							
						}
					}
						
				}catch(Exception e) {
				
				}
			//	System.out.println("Waiting");
				return false;
				}
			};
	static Function<WebDriver, Boolean> elementToBeVisible = new Function<WebDriver, Boolean>()
			{
				public Boolean apply(WebDriver driverArg) {
				
					boolean res = driverArg.findElement(By.xpath(elementLocator)).isDisplayed();
					if(res)
					{
						LOGGER.info("Target element found");
						return true;
						
					}
					LOGGER.info("Waiting");
					return false;
					}
				};	
				
	static Function<WebDriver, Boolean> elementToBeEnabled = new Function<WebDriver, Boolean>()
	{
		public Boolean apply(WebDriver driverArg) {
			
			boolean res = driverArg.findElement(By.xpath(elementLocator)).isEnabled();
			if(res)
			{
				System.out.println("Target element found");
				return true;
				
			}
			System.out.println("Waiting");
			return false;
			}
		};	
		
	static  Function<WebDriver, Boolean>  secondWindowOpens = new Function<WebDriver, Boolean> () {
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
	//Should Add WebElement as another argument	
	public boolean waitUntil(String functionName){
		
		switch (FrameWorkConstants.FluentWaitFunctions.valueOf(functionName)){
		
		
		case PAGETOLOAD :{
			wait.withTimeout(ExecutionSettings.getInstance().getPageLoadTimeout(), TimeUnit.SECONDS);
			return wait.until(pageToLoad);
		}
		
		case SECONDWINDOWOPENS :
			return wait.until(secondWindowOpens);
			
		default :
			return false;
		
		}
	}
	
	public boolean waitUntil(String functionName, Object object){
		
		switch (FrameWorkConstants.FluentWaitFunctions.valueOf(functionName)){
		
		case ELEMENTTOBEVISIBLE :
			elementLocator = (String) object ;
			return wait.until(elementToBeVisible);
			
		default :
			return false;
		
		}
	}
	
}
