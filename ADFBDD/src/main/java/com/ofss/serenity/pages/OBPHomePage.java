package com.ofss.serenity.pages;

import com.ofss.selenium.actions.WebUIElement;
import net.serenitybdd.core.pages.PageObject;

import org.openqa.selenium.WebDriver;


public class OBPHomePage extends PageObject {

    private BasePageofOrigination basePageofOrigination = new BasePageofOrigination();
    private WebUIElement webUIElement                   = new WebUIElement();

   public void loginIntoApplication(String username ,String password) {

        basePageofOrigination.loginToApplicationForm(username,password);

    }

    public void goToPageUsingFastPath(String fastPath) {

        webUIElement.enterText(getDriver(),"RTD_Input_WithPlaceHolder|Fast Path",fastPath);

        webUIElement.clickElement(getDriver(),"RTD_Link_WithTitle|Open page");

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        webUIElement.switchWindowByTitleNotContains(getDriver(),"Oracle Banking Platform");


    }

    public Boolean verifyFastPathFieLdEnabled() {


        return webUIElement.quickVerifyElementPresent(getDriver(),"RTD_Input_WithPlaceHolder|Fast Path");

    }

}
