package com.ofss.selenium.helpers;


import java.util.Arrays;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ofss.Utilities.frameworkUtilities.TestData;

import net.serenitybdd.core.pages.PageObject;

public class ElementLocator  extends PageObject{

	final private static Logger LOGGER = Logger.getLogger(ElementLocator.class.getName());
    public WebElement findElement(WebDriver driver, String fieldDetails){

        WebElement targetElement = null;
        String[] fieldDetailsArray = fieldDetails.split("\\|") ;
        String fieldName = fieldDetailsArray[0];
        String locatorStrategy = FieldDefinitions.getInstance().fieldLocationStrategy.get(fieldName);
        String locator =  FieldDefinitions.getInstance().fieldDefinitions.get(fieldName);

        if(fieldName.startsWith("RTD_") && fieldDetailsArray.length > 1 ){
            String[]  runtimeData = Arrays.copyOfRange(fieldDetailsArray,1,fieldDetailsArray.length);
            for (String data : runtimeData){
                if(data.startsWith("RT_")){
                    data = TestData.getRuntimeData(data).toString();
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
                	setDriver(driver);
                	waitFor(locator);
                    element=driver.findElement(By.xpath(locator));

                }catch(Exception e){
                    System.out.println(this.getClass().getName() + " : " + e);
                }
                break;
            default:
                System.out.println(this.getClass().getName() + " : " + "Improper locator type");
        }
        LOGGER.info(locatorStrategy  +  " : " + locator);
        return element;

    }



}
