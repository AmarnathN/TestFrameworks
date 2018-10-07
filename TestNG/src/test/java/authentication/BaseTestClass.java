package authentication;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.frameworkUtilities.FrameWorkConstants;
import com.frameworkUtilities.GenericUtilities;
import com.reports.ExtentManager;

public class BaseTestClass {
	
	private static final Logger LOGGER = Logger.getLogger(BaseTestClass.class.getName()); 
	protected ExtentReports extent;
	protected ExtentTest test;

	
    @BeforeClass
    public void BeforeClass() {
    //	intiateLoggers();
    }
    
    @AfterMethod
    public void AfterMethod() {
    	extent.flush();
    	
    }
    
    @BeforeMethod
    public void BeforeMethod() {
    	extent = ExtentManager.getInstance().GetExtent();
    	test = extent.createTest("Submit_The_Application_Form1" + Thread.currentThread().getName(), "Verify HomePage");
    }
    
	/**
	 * Initialize log4j logger instance.
	 */
	private static void intiateLoggers() {
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
