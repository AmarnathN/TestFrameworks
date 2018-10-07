package com.ofss.serenity.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebDriver;

import com.ofss.selenium.actions.WebUIElement;


public class LandingPage extends PageObject {

    private CommonPageActions basePageofOrigination =  new CommonPageActions();
    private WebUIElement webUIElement = new WebUIElement();
    

    public void loginToApplicationForm(String username, String password) {
    	basePageofOrigination.loginToApplicationForm(username,password);
    }

    public void selectApplication(String applicationType) {
    	webUIElement.clickElement(getDriver(), "RTD_Link_WithText|"+applicationType);
    	
    }
    
    public void selectProductType(String productType) {
    	webUIElement.clickElement(getDriver(), "RTD_Link_WithText|"+productType);
    }

    public void selectProductOffer(String productOffer) {
    	webUIElement.clickElement(getDriver(), "RTD_ProductOffer_Link|"+productOffer);
    }

    public void clickOnAddToCart() {
    	webUIElement.clickElement(getDriver(), "RTD_Button|Add to Cart");
       
    }

    public void clickOnStartApplication() {
    	webUIElement.clickElement(getDriver(), "StartApplication_Button");
 
    }
}