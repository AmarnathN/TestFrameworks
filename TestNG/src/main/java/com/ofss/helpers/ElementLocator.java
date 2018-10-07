package com.ofss.helpers;


import java.util.Arrays;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import com.ofss.frameworkUtilities.TestData;


public class ElementLocator {

	final private static Logger LOGGER = LogManager.getLogger(ElementLocator.class);
    public WebElement findElement(WebDriver driver, String fieldDetails){

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



}
