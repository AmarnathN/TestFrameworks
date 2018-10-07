package com.helpers;


import java.util.Arrays;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.frameworkUtilities.ExecutionSettings;
import com.frameworkUtilities.TestData;


public class ElementLocator {

	final private static Logger LOGGER = Logger.getLogger(ElementLocator.class);
	private static ElementLocator instance;

	

	/**
	 * Gets the single instance of ExecutionSettings.
	 * 
	 * @return single instance of ExecutionSettings
	 */
	public static synchronized ElementLocator getInstance() {

		if (instance == null) {
			instance = new ElementLocator();
		}
		return instance;
	}

	
	
	
	
	
	public String getLocator(String rawLocatorString) {
		
		
		String finalLocator = null;
		  String[] fieldDetailsArray = rawLocatorString.split("\\|") ;
	        String fieldName = fieldDetailsArray[0];
	        String locator =  FieldDefinitions.getInstance().fieldDefinitions.get(fieldName);

	        if(fieldName.startsWith("RTD_") && fieldDetailsArray.length > 1 ){
	            String[]  runtimeData = Arrays.copyOfRange(fieldDetailsArray,1,fieldDetailsArray.length);
	            for (String data : runtimeData){
	                if(data.startsWith("RT_")){
	                    data = TestData.getInstance().getRuntimeData(data).toString();
	                }
	                finalLocator = locator.replaceFirst("DATA", data);
	            }
	        }

		return finalLocator;
	}
	
	
    public WebElement findElement(WebDriver driver, String fieldDetails){

       // LOGGER.info(fieldDetails);
        String[] fieldDetailsArray = fieldDetails.split("\\|") ;
        String fieldName = fieldDetailsArray[0];
        String locatorStrategy = FieldDefinitions.getInstance().fieldLocationStrategy.get(fieldName);
        String locator = getLocator(fieldDetails);
        		
       return findElementBy(driver,locatorStrategy,locator);

    }

    public WebElement findElementBy(WebDriver driver, String locatorStrategy, String locator){
        WebElement element = null;
       
        switch(locatorStrategy.toUpperCase()){
            case "XPATH" :
                try{
                	new fluentWait(driver).waitUntil("pageLoaded");
                	new fluentWait(driver).waitUntil("elementToBeVisible",locator);
                    element=driver.findElement(By.xpath(locator));

                }catch(Exception e){
                    LOGGER.error(" Exception in finding Element with Xpath : " + locator + " " + e);
                }
                break;
            default:
                LOGGER.error(this.getClass().getName() + " : " + "Improper locator type");
        }
     //   LOGGER.info(element);
        return element;

    }
    
    public List<WebElement> findElements(WebDriver driver, String fieldDetails){

        // LOGGER.info(fieldDetails);
         String[] fieldDetailsArray = fieldDetails.split("\\|") ;
         String fieldName = fieldDetailsArray[0];
         String locatorStrategy = FieldDefinitions.getInstance().fieldLocationStrategy.get(fieldName);
         String locator =  FieldDefinitions.getInstance().fieldDefinitions.get(fieldName);

         if(fieldName.startsWith("RTD_") && fieldDetailsArray.length > 1 ){
             String[]  runtimeData = Arrays.copyOfRange(fieldDetailsArray,1,fieldDetailsArray.length);
             for (String data : runtimeData){
                 if(data.startsWith("RT_")){
                     data = TestData.getInstance().getRuntimeData(data).toString();
                 }
                 locator = locator.replaceFirst("DATA", data);
             }
         }
          return findElementsBy(driver,locatorStrategy,locator);

     }

     public List<WebElement> findElementsBy(WebDriver driver, String locatorStrategy, String locator){
         List<WebElement> elements = null;
        
         switch(locatorStrategy.toUpperCase()){
             case "XPATH" :
                 try{
                 	new fluentWait(driver).waitUntil("pageLoaded");
                 	new fluentWait(driver).waitUntil("elementToBeVisible",locator);
                    elements=driver.findElements(By.xpath(locator));

                 }catch(Exception e){
                     LOGGER.error(" Exception in finding Elements list with Xpath : " + locator + " " + e);
                 }
                 break;
             default:
                 LOGGER.error(this.getClass().getName() + " : " + "Improper locator type");
         }
      //   LOGGER.info(element);
         return elements;

     }




}
