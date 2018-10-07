package com.ofss.mainClasses;

import java.io.File;
import java.nio.file.Paths;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.ofss.Utilities.frameworkUtilities.ExecutionSettings;
import com.ofss.Utilities.frameworkUtilities.FrameWorkConstants;
import com.ofss.Utilities.frameworkUtilities.GenericUtilities;




public class PreExecution {
	private static final Logger LOGGER = Logger.getLogger(PreExecution.class);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//	System.out.println("I am In Main Class :" + args[0] + args[1]);
		
		try {
			System.setProperty("JADF_root", Paths.get("").toAbsolutePath().toString());
			//LOGGER.info("JADF root absolute path: " + System.getProperty("JADF_root"));
			if (args.length > 0) {
				
				System.setProperty("selenium_artifactsFolder", args[0]);
				ExecutionSettings.getInstance().setArtifactsFolderPath(args[0]);
	
				if (args.length == 1) {
//					System.setProperty("selenium_testArtifactsFolder", System.getProperty("selenium_artifactsFolder")
//							+ File.separator + "TAF_" + ExecutionSettings.getInstance().getExecutionEnvironment());
					ExecutionSettings.getInstance().setTestArtifactsFolderPath();
					intiateLoggers();
				}

				if (args.length >= 2) {
					
					
					// For CI: used to override Execution Environment folder name set in Global Settings (e.g. SMOKE)
					ExecutionSettings.getInstance().setExecutionEnvironment(args[1]);
					ExecutionSettings.getInstance().setTestArtifactsFolderPath();
					intiateLoggers();
					
				}

				if (args.length >= 3) {
					// For CI: used to override Environment name set in Global Settings (e.g. IDEV, FIT2)
					ExecutionSettings.getInstance().setEnvironmentName(args[2]);
				}

				
			} else {
				//LOGGER.error("To run JADF you need to pass at least one parameter (Artifacts folder path).");
				System.setProperty("selenium_artifactsFolder", "Artifacts");
				ExecutionSettings.getInstance().setArtifactsFolderPath(System.getProperty("selenium_artifactsFolder"));
				ExecutionSettings.getInstance().setTestArtifactsFolderPath();
				intiateLoggers();
			}
		} catch (Exception ex) {
			LOGGER.error("Exception in Pre Execution Class.", ex);
		}
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
			LOGGER.error("Error while intiating Loggers.", e);
		}
	}


}
