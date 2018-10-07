package com.ofss.serenity.moduleActionSteps;

import com.ofss.serenity.pages.LandingPage;

import net.thucydides.core.annotations.Step;

public class ProductSelectionSteps {

    private LandingPage landingPage;

    @Step("Select the application of type : {0}")
    public void selectTypeofApplication(String applicationType) {
        landingPage.selectApplication(applicationType);
    }

    @Step("Select the new Product of type : {0}")
    public void selectTypeofProduct(String productType) {
        landingPage.selectProductType(productType);
    }

    @Step("Select the Product offer  : {0} and Add to Cart")
    public void addProductOfferToCart(String productOffer) {
        landingPage.selectProductOffer(productOffer);
    //    landingPage.clickOnAddToCart();
    }


    @Step("Start the application")
    public void startApplication() {
        landingPage.clickOnStartApplication();
    }

}
