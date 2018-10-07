package authentication;

import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fileUtilities.ExcelUtilities;
import com.helpers.DriverManager;
import com.moduleActionSteps.BaseTest;
import com.moduleSteps.OBPModuleSteps;



public class OriginationTest extends BaseTestClass {

	private static final Logger LOGGER = Logger.getLogger(OriginationTest.class); 
	
	@DataProvider(parallel = true)
    public static Object[][] testData() {
    	
    	ArrayList<Map<String, String>> testDataList = null;
    	
    	
		try {
			testDataList = (new ExcelUtilities().getTestDataMapList(OriginationTest.class.getSimpleName()));
			Object[][] finalTestData = new Object[testDataList.size()][1] ;
			if(testDataList!=null) {
				
				for (int i=0 ;i< testDataList.size();i++) {
					
					finalTestData[i][0]=testDataList.get(i);
					
				}
				System.out.println(finalTestData);
			}
			return finalTestData ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
		}
    	

    }

    @Test(dataProvider="testData")
    public void Submit_The_Application_Form1(Map<String, String> testDataMap) {
    	try {
	    	
	    if(testDataMap!=null) {
	    	
	    	WebDriver driver = new DriverManager().getDriver();
	    		LOGGER.info("main : " + driver);
				BaseTest.getInstance().setDriver(driver);
				
			    		System.out.println("I am in Test &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
	    		OBPModuleSteps obpModuleSteps = new OBPModuleSteps();
	    		
	    		LOGGER.info("Level1 :" + BaseTest.getInstance().getDriver());
	        	
	    		obpModuleSteps.Logs_Into_The_ApplicationForm(test);
	    		LOGGER.info("Logs_Into_The_ApplicationForm");
//	    	    obpModuleSteps.Select_The_Products_For_Application(testDataMap.get("Application Type".toUpperCase()),
//	    	    													testDataMap.get("Product Type".toUpperCase()),
//	    	    													testDataMap.get("Product Offer".toUpperCase()));
////	        	obpModuleSteps.setWebDriver(driver);
////
//	    		OBPModuleSteps2 obpModuleSteps2 = new OBPModuleSteps2();

	    	}else {
	    		Assert.assertTrue(false);
	    	}
    	}catch(Exception e) {
    		System.out.println("In The test " + e);
    	}
    }
    
   

}
