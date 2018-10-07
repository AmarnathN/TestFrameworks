package com.pages;




import org.openqa.selenium.WebDriver;

import com.moduleActionSteps.BaseTest;
import com.selenium.actions.WebUIElement;


public class LandingPage{

	public WebDriver driver ;
	private WebUIElement webUIElement= new WebUIElement();
	
    public LandingPage(){
    	driver = BaseTest.getInstance().getDriver();
    }
    
    public void loginToApplicationForm(String username, String password) {
    	new BasePageofOrigination().loginToApplicationForm(username,password);
    }

    public void selectApplication(String applicationType) {
    	webUIElement.clickElement(driver, "RTD_Link_WithText|"+applicationType);
    }
    
    public void selectProductType(String productType) {
    	webUIElement.clickElement(driver, "RTD_Link_WithText|"+productType);
    }

    public void selectProductOffer(String productOffer) {
    	webUIElement.clickElement(driver, "RTD_ProductOffer_Link|"+productOffer);
    }

    public void clickOnAddToCart() {
    	webUIElement.clickElement(driver, "RTD_Button|Add to Cart");
       
    }

    public void clickOnStartApplication() {
    	webUIElement.clickElement(driver, "StartApplication_Button");
 
    }
}