//package com.ofss.moduleSteps;
//
//
//import org.openqa.selenium.WebDriver;
//
//import com.ofss.frameworkUtilities.TestData;
//import com.ofss.moduleActionSteps.BaseTest;
//import com.ofss.moduleActionSteps.LoginSteps;
//import com.ofss.moduleActionSteps.ProductSelectionSteps;
//import com.ofss.moduleSteps.driver.MethodDriver;
//import com.ofss.pages.BasePageofOrigination;
//import com.ofss.pages.LandingPage;
//
//
//
//
//
//public class OBPModuleSteps2 extends MethodDriver {
//
//   
//	private WebDriver driver ;
//	
//	private LoginSteps loginSteps;
//	private ProductSelectionSteps productSelectionSteps ;
//	
//	public void InitiateSteps(WebDriver driver) {
//    	
//		setDriver(driver);
//    	loginSteps = new LoginSteps(getDriver());
//    	productSelectionSteps = new ProductSelectionSteps(getDriver());
//		
//	}
//    
//    public WebDriver getDriver() {
//		return driver;
//	}
//
//	public void setDriver(WebDriver driver) {
//		this.driver = driver;
//	}
//
//
//	public void Logs_Into_The_ApplicationForm() {
//    	
//      	String userName = TestData.getTestData("GV_OBP_Username") ;
//    	String passWord = TestData.getTestData("GV_OBP_Password") ;
//    	String FastPath = TestData.getTestData("GV_OBP_ApplicationForm_FastPath") ;
//    	
//        loginSteps.isOnTheHomePage();
//
//        loginSteps.logsIntoApplication(userName,passWord);
//
//        loginSteps.shouldBeOnLandingPage();
//
//        loginSteps.goToPageUsingFastPath(FastPath);
//        
//        driver = new BasePageofOrigination(getDriver()).switchWindow("switchWindowByTitleNotContains", "Oracle Banking Platform");
//        InitiateSteps(driver);
//       
//        loginSteps.loginToApplicationForm();
//    }
//
// 
//    public void Select_The_Products_For_Application(String applicationType,String productType ,String productOffer){
//    	
//    	System.out.println(getDriver().getTitle());
//        productSelectionSteps.selectTypeofApplication(applicationType);
//
//        productSelectionSteps.selectTypeofProduct(productType);
//
//        productSelectionSteps.addProductOfferToCart(productOffer);
//
//        productSelectionSteps.startApplication();
//
//    }
//
//
//
//}
