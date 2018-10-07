package authentication;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.fileUtilities.ExcelUtilities;
import com.frameworkUtilities.FrameWorkConstants;
import com.frameworkUtilities.GenericUtilities;
import com.frameworkUtilities.TestData;
import com.helpers.DriverManager;
import com.moduleActionSteps.BaseTest;
import com.moduleActionSteps.GoogleSearch;
import com.moduleSteps.GoogleModuleSteps;
import com.moduleSteps.OBPModuleSteps;
import com.reports.ExtentManager;

public class GoogleSearchTest {

	private static final Logger LOGGER = Logger.getLogger(OriginationTest.class); 
	protected ExtentReports extent;
	protected ExtentTest test;

	@DataProvider(parallel = true)
    public static Object[][] GoogleSearchTestData() {
    	
    	ArrayList<Map<String, String>> testDataList = null;
    	
    	
		try {
			testDataList = (new ExcelUtilities().getTestDataMapList("GoogleSearchTest"));
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
	
	@BeforeMethod
	    public void BeforeMethod() {
		intiateLoggers();
	    	extent = ExtentManager.getInstance().GetExtent();
	    	test = extent.createTest("searchInGoogle" + Thread.currentThread().getName(), "");
	    }
	 
	@AfterMethod
	    public void AfterMethod() {
	    	extent.flush();
	    	
	    }

    @Test(dataProvider="GoogleSearchTestData")
    public void searchInGoogle(Map<String, String> testDataMap) {
    	try {
	    	
	    if(testDataMap!=null) {
	    	
	    	WebDriver driver = new DriverManager().getDriver();
	    		LOGGER.info("main : " + driver);
				BaseTest.getInstance().setDriver(driver);
				TestData.getInstance().addRuntimeData("TestDataMap", testDataMap);
				GoogleModuleSteps googleSearchModule = new GoogleModuleSteps(test);
	    		
	    		LOGGER.info("Level1 :" + BaseTest.getInstance().getDriver());
	        	
	    		googleSearchModule.LoadHomePage();
	    		googleSearchModule.SearchgivenData();
	    		googleSearchModule.moveToLastSearch();
	    		LOGGER.info("Logs_Into_The_ApplicationForm");
	    		driver.close();
	    	}else {
	    		Assert.assertTrue(false);
	    	}
    	}catch(Exception e) {
    		System.out.println("In The test " + e);
    	}
    }
    
    private void intiateLoggers() {
		try {
			GenericUtilities genericUtilities = new GenericUtilities();
			System.setProperty("log4j.time", genericUtilities.getCurrentTimeStamp());
			String propertiesFilePath = System.getProperty("selenium_artifactsFolder") + File.separator
					+ FrameWorkConstants.GLOBAL_SETTINGS_FOLDERNAME + File.separator
					+ FrameWorkConstants.LOG4JPROPERTIESFILENAME;
			PropertyConfigurator.configure(propertiesFilePath);
			LOGGER.info("log4j properties filepath: " + propertiesFilePath);
		} catch (Exception e) {
			LOGGER.error("Error while intiating Loggers." + e);
		}
	}   

}
