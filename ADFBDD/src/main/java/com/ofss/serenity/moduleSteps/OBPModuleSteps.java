package com.ofss.serenity.moduleSteps;


import com.ofss.serenity.moduleActionSteps.LoginSteps;
import com.ofss.serenity.moduleActionSteps.ProductSelectionSteps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;



public class OBPModuleSteps {
    @Steps
    private LoginSteps loginSteps;
    @Steps
    private ProductSelectionSteps productSelectionSteps;

    @Step
    public void Logs_Into_The_Application(String userName, String passWord, String FastPath) {

        loginSteps.isOnTheHomePage();

        loginSteps.logsIntoApplication(userName,passWord);

        loginSteps.shouldBeOnLandingPage();

        loginSteps.goToPageUsingFastPath(FastPath);

        loginSteps.loginToApplicationForm();
    }

    @Step
    public void Select_The_Products_For_Application(String applicationType,String productType ,String productOffer){

        productSelectionSteps.selectTypeofApplication(applicationType);

        productSelectionSteps.selectTypeofProduct(productType);

        productSelectionSteps.addProductOfferToCart(productOffer);

        productSelectionSteps.startApplication();

    }



}
