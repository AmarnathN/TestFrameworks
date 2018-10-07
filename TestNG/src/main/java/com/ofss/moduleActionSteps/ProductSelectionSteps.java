package com.ofss.moduleActionSteps;

import org.openqa.selenium.WebDriver;

import com.ofss.pages.LandingPage;


public class ProductSelectionSteps  {

	private LandingPage landingPage  = new LandingPage();

	public void selectTypeofApplication(String applicationType) {
		
        landingPage.selectApplication(applicationType);
        
    }

  
    public void selectTypeofProduct(String productType) {
        landingPage.selectProductType(productType);
    }

    
    public void addProductOfferToCart(String productOffer) {
        landingPage.selectProductOffer(productOffer);
    //    landingPage.clickOnAddToCart();
    }


   
    public void startApplication() {
        landingPage.clickOnStartApplication();
    }

}
