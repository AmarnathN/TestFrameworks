package com.ofss.Utilities.frameworkUtilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;

import com.google.common.base.Verify;

import net.thucydides.core.steps.StepEventBus;

public class GenericUtilities {
	
	private static final Logger LOGGER = Logger.getLogger(GenericUtilities.class.getName());
	
	/**
	 * Gets the current time stamp.
	 * 
	 * @return the current time stamp
	 */
	public static String getCurrentTimeStamp() {
		long currMilli = System.currentTimeMillis();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss.SSS");
		String currentTimestamp = dateFormat.format(new Date(currMilli)); //+System.nanoTime(); --removed NanoTime append logic
		//LOGGER.info("currentTimestamp::" + currentTimestamp);

		return currentTimestamp;
	}
	
	
	public static void customAssertEquals(String actual, String expected) {
		boolean assertStatus = false;
		if(ExecutionSettings.getInstance().isAbortForCheckpointFailureOn()) {
//    		StepEventBus.getEventBus().enableSoftAsserts();
//    		System.out.println("Abbbort Execution " + ExecutionSettings.getInstance().isAbortForCheckpointFailureOn());
			Assert.assertEquals(expected, actual);
    	}else {
    		Verify.verify(actual.equals(expected));
    	}
		
	}
	
	
	public static void customAssertTrue(boolean condition) {
		if(ExecutionSettings.getInstance().isAbortForCheckpointFailureOn()) {
			Assert.assertTrue(condition);
		}else {
			Verify.verify(condition);
		}
	}
	
    /**
	 * Initialize log4j logger instance.
	 */
	public static void intiateLoggers() {
		try {
			
			System.setProperty("log4j.time", getCurrentTimeStamp());
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
