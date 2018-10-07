package com.ofss.moduleSteps;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import com.aventstack.extentreports.ExtentTest;
import com.ofss.frameworkUtilities.TestData;
import com.ofss.moduleActionSteps.BaseTest;
import com.ofss.moduleActionSteps.LoginSteps;
import com.ofss.moduleActionSteps.ProductSelectionSteps;



public class OBPModuleSteps {
	private static final Logger LOGGER = LogManager.getLogger(OBPModuleSteps.class);
	
	
	public void Logs_Into_The_ApplicationForm(ExtentTest test) {
		ExtentTest childTest = test.createNode("Logs_Into_The_ApplicationForm");
      
		String userName = TestData.getInstance().getTestData("GV_OBP_Username") ;
    	String passWord = TestData.getInstance().getTestData("GV_OBP_Password") ;
    	String FastPath = TestData.getInstance().getTestData("GV_OBP_ApplicationForm_FastPath") ;
    	
    	LoginSteps loginSteps = new LoginSteps();
    
    	LOGGER.info("Level2 :" + BaseTest.getInstance().getDriver());
    
        loginSteps.isOnTheHomePage();
        childTest.pass("The application launched");
        loginSteps.logsIntoApplication(userName,passWord);
        childTest.pass("logsIntoApplication");
        loginSteps.shouldBeOnLandingPage();
        childTest.pass("shouldBeOnLandingPage");
        loginSteps.goToPageUsingFastPath(FastPath);
        childTest.pass("goToPageUsingFastPath");
        loginSteps.loginToApplicationForm(userName,passWord);
        
    
        
    }

 
    public void Select_The_Products_For_Application(String applicationType,String productType ,String productOffer){
    	
    	ProductSelectionSteps productSelectionSteps = new ProductSelectionSteps();
    	
        productSelectionSteps.selectTypeofApplication(applicationType);

        productSelectionSteps.selectTypeofProduct(productType);

        productSelectionSteps.addProductOfferToCart(productOffer);

        productSelectionSteps.startApplication();

    }



}
