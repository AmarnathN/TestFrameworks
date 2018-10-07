package com.ofss.Utilities.frameworkUtilities;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ofss.Utilities.fileUtilities.CSVUtilities;

/**
 * The Class ExecutionSettings loads and make ExecutionSettings available.
 */
public class ExecutionSettings {
	private static final Logger LOGGER = Logger.getLogger(ExecutionSettings.class);
	private static ExecutionSettings instance;
	private Map<String, Map<String, String>> executionSettings;
	private String executionEnvironment = null;
	private String testSuiteName = null;
	private String environmentName = null;
	private String testArtifactsFolderPath = null;
	private String artifactsFolderPath = null;

	/**
	 * Instantiates a new execution settings.
	 */
	private ExecutionSettings() {
		
		String globalSettingsPath = System.getProperty("selenium_artifactsFolder") + File.separator
				+ FrameWorkConstants.GLOBAL_SETTINGS_FOLDERNAME;
		this.executionSettings = new CSVUtilities().readExecutionSettings(globalSettingsPath);
		
		setArtifactsFolderPath(System.getProperty("selenium_artifactsFolder"));
		setTestArtifactsFolderPath();
		
		
	}

	/**
	 * Gets the single instance of ExecutionSettings.
	 * 
	 * @return single instance of ExecutionSettings
	 */
	public static synchronized ExecutionSettings getInstance() {

		if (instance == null) {
			instance = new ExecutionSettings();
		}
		return instance;
	}

	public String getArtifactsFolderPath() {
		return artifactsFolderPath;
	}

	public void setArtifactsFolderPath(String artifactsFolderPath) {
		this.artifactsFolderPath = artifactsFolderPath;
	}

	public String getTestArtifactsFolderPath() {
		return testArtifactsFolderPath;
	}

	public void setTestArtifactsFolderPath() {
		this.testArtifactsFolderPath = getArtifactsFolderPath() + File.separator
				+ FrameWorkConstants.TESTARTIFACTS_ROOTFOLDERPREFIX + getExecutionEnvironment();
	}

	/**
	 * Gets the execution environment.
	 * 
	 * @return the execution environment
	 */
	public String getExecutionEnvironment() {
		String execEnvironment = null;
		if (this.executionEnvironment != null) {
			execEnvironment = this.executionEnvironment;
		} else {
			Map<String, String> exEnv = getExecutionSettings().get("EXECUTIONENVIRONMENT");
			for (String envName : exEnv.keySet()) {
				if (exEnv.get(envName).equalsIgnoreCase("YES")) {
					execEnvironment = envName;
				}
			}
		}
		return execEnvironment;
	}

	// For CI:
	/**
	 * Sets the execution environment.
	 * 
	 * @param string
	 *            the new execution environment
	 */
	public void setExecutionEnvironment(String string) {
		this.executionEnvironment = string;
	}

	/**
	 * Gets the environment name.
	 * 
	 * @return the environment name
	 */
	public String getEnvironmentName() {
		String environmentName = null;
		if (this.environmentName != null) {
			environmentName = this.environmentName;
		} else {
			Map<String, Map<String, String>> exSettings = getExecutionSettings();
			Map<String, String> exEnv = exSettings.get("ENVIRONMENTNAME");
			for (String envName : exEnv.keySet()) {
				if (exEnv.get(envName).equalsIgnoreCase("YES")) {
					environmentName = envName;
					break;
				}
			}
		}
		return environmentName;
	}

	// For CI:
	/**
	 * Sets the environment name.
	 * 
	 * @param string
	 *            the new environment name
	 */
	public void setEnvironmentName(String string) {
		this.environmentName = string;
	}

	/**
	 * Gets the execution environments.
	 * 
	 * @return the execution environments
	 */
	public List<String> getExecutionEnvironments() {
		List<String> executionEnvironment = new ArrayList<String>();
		Map<String, Map<String, String>> exSettings = getExecutionSettings();
		Map<String, String> exEnv = exSettings.get("EXECUTIONENVIRONMENT");
		for (String envName : exEnv.keySet()) {
			if (exEnv.get(envName).equalsIgnoreCase("YES")) {
				executionEnvironment.add(envName.toUpperCase());
			}
		}
		return executionEnvironment;
	}

	/**
	 * Gets the execution browsers.
	 * 
	 * @return the execution browsers
	 */
	public List<String> getExecutionBrowsers() {
		List<String> executionBrowsers = new LinkedList<String>();
		Map<String, Map<String, String>> executionSettings = getExecutionSettings();
		if (executionSettings.containsKey("EXECUTIONBROWSERS")) {
			Map<String, String> executionBrowserSettings = executionSettings.get("EXECUTIONBROWSERS");
			for (String execBrowser : executionBrowserSettings.keySet()) {
				if (executionBrowserSettings.get(execBrowser).equalsIgnoreCase("YES")) {
					executionBrowsers.add(execBrowser);

				}
			}
		} else {
			LOGGER.error("executionSettings do not contain EXECUTIONBROWSERS configured.");
		}
		return executionBrowsers;
	}

	/**
	 * Gets the element timeout.
	 * 
	 * @return the element timeout
	 */
	public int getElementTimeout() {
		int elementTimeout = 30;
		try {
			if (getExecutionSettings().containsKey("TIMEOUTS")
					&& getExecutionSettings().get("TIMEOUTS").containsKey("ELEMENTTIMEOUT")) {
				elementTimeout = Integer.parseInt(getExecutionSettings().get("TIMEOUTS").get("ELEMENTTIMEOUT"));
				// LOGGER.info("ElementtTimeout is set to " + elementTimeout);
			} else {
				elementTimeout = 30;
				LOGGER.error("Missing ExecutionVariableType TIMEOUTS/ELEMENTTIMEOUT in GlobalSettings,"
						+ " hence defaulting it to: " + elementTimeout);
			}
		} catch (Exception e) {
			elementTimeout = 30;
			LOGGER.error("Unknown error in fetching element timeout from GlobalSettings," + " hence defaulting it to: "
					+ elementTimeout);
		}
		return elementTimeout;
	}

	/**
	 * Gets the page load timeout.
	 * 
	 * @return the page load timeout
	 */
	public int getPageLoadTimeout() {
		int pageloadTimeout = 60;
		try {
			if (getExecutionSettings().containsKey("TIMEOUTS")) {
				if (getExecutionSettings().get("TIMEOUTS").containsKey("PAGELOADTIMEOUT")) {
					pageloadTimeout = Integer.parseInt(getExecutionSettings().get("TIMEOUTS").get("PAGELOADTIMEOUT"));
					// LOGGER.info("pageloadTimeout is set to :: " + pageloadTimeout);
				} else {
					pageloadTimeout = 60;
					LOGGER.error(
							"TestSuite settings doesnt contain ExecutionVariableName:PAGELOADTIMEOUT, Setting value to default: "
									+ pageloadTimeout);
				}
			} else {
				pageloadTimeout = 60;
				LOGGER.error(
						"TestSuite settings doesnt contain ExecutionVariableType:TIMEOUTS, Setting value to default: "
								+ pageloadTimeout);
			}
		} catch (Exception e) {
			pageloadTimeout = 60;
			LOGGER.error(
					"Unknown error in fetching pageload timeout from TestSuite settings. Setting the elementTimeout value to default: "
							+ pageloadTimeout);
		}
		return pageloadTimeout;
	}

	/**
	 * Checks if is debug on.
	 * 
	 * @return true, if is debug ons
	 */
	public boolean isDebugOn() {
		boolean isDebugOn;
		Map<String, Map<String, String>> execSettings = getExecutionSettings();
		if (execSettings.get("EXECUTIONSETTINGS").containsKey("DEBUG")) {
			if (execSettings.get("EXECUTIONSETTINGS").get("DEBUG").equalsIgnoreCase("ON")) {
				isDebugOn = true;
			} else {
				isDebugOn = false;
			}
		} else {
			LOGGER.error(
					"EXECUTIONSETTINGS-DEBUG is not specified in TestSuiteExecutionSettings file. Hence returning the default value - false");
			isDebugOn = false;
		}
		return isDebugOn;
	}

	/**
	 * Checks if is abort for major checkpoint failure on.
	 * 
	 * @return true, if is abort for major checkpoint failure on
	 */
	public boolean isAbortForMajorCheckpointFailureOn() {
		boolean isAbortForMajorCheckpointFailureOn;
		if (getExecutionSettings().get("EXECUTIONSETTINGS").containsKey("ABORTEXECUTIONIFCHECKPOINTFAILS")) {
			if (getExecutionSettings().get("EXECUTIONSETTINGS").get("ABORTEXECUTIONIFCHECKPOINTFAILS")
					.equalsIgnoreCase("ON")) {
				LOGGER.info(
						"WARNING: ABORTEXECUTIONIFCHECKPOINTFAILS is turned ON in TestSuiteExecutionSettings. Hence setting continueTestcaseRun to false. Turn this OFF to continue test case execution despite major checkpoint failures");
				isAbortForMajorCheckpointFailureOn = true;
			} else {
				LOGGER.info(
						"WARNING: ABORTEXECUTIONIFCHECKPOINTFAILS is turned OFF in TestSuiteExecutionSettings. Hence setting continueTestcaseRun to true. Turn this ON to save on the execution time");
				isAbortForMajorCheckpointFailureOn = false;
			}
		} else {
			isAbortForMajorCheckpointFailureOn = false;
			LOGGER.error(
					"ABORTEXECUTIONIFCHECKPOINTFAILS is not found in execution settings. Hence returning the default value - false");
		}
		return isAbortForMajorCheckpointFailureOn;
	}

	/**
	 * Checks if is screenshot for minor checkpoint failure on.
	 * 
	 * @return true, if screenshot for minor checkpoint failure is turned on
	 */
	public boolean isScreenshotForMinorCheckpointFailureOn() {
		boolean isScreenshotForMinorCheckpointFailureOn;
		if (getExecutionSettings().get("SCREENSHOT").containsKey("MINOR")) {
			if (getExecutionSettings().get("SCREENSHOT").get("MINOR").equalsIgnoreCase("ON")) {
				// LOGGER.info("INFO: Screenshot for MINOR is turned ON in
				// TestSuiteExecutionSettings. Turn this OFF if not needed for minor checkpoint
				// failures");
				isScreenshotForMinorCheckpointFailureOn = true;
			} else {
				isScreenshotForMinorCheckpointFailureOn = false;
			}
		} else {
			isScreenshotForMinorCheckpointFailureOn = false;
			// LOGGER.error("SCREENSHOT is not found in execution settings. Hence MAJOR
			// screenshot will be captured by default");
		}
		return isScreenshotForMinorCheckpointFailureOn;
	}

	/**
	 * Gets the polling interval.
	 * 
	 * @return the polling interval
	 */
	public long getPollingInterval() {
		long pollingInterval = 100L;
		try {
			if (getExecutionSettings().containsKey("TIMEOUTS")) {
				if (getExecutionSettings().get("TIMEOUTS").containsKey("POLLINGINTERVAL")) {
					pollingInterval = Long.parseLong(getExecutionSettings().get("TIMEOUTS").get("POLLINGINTERVAL"));
					// LOGGER.info("pollingInterval is set to :: " + pollingInterval);
				} else {
					pollingInterval = 100L;
					// LOGGER.error("TestSuite settings doesnt contain the ev-name: PollingInterval,
					// Setting the PollingInterval value to default: " + pollingInterval);
				}
			} else {
				pollingInterval = 100L;
				// LOGGER.error("TestSuite settings doesnt contain the ev-type: TimeOuts,
				// Setting the PollingInterval value to default: " + pollingInterval);
			}
		} catch (Exception e) {
			pollingInterval = 100L;
			// LOGGER.error("Unknown error in fetching polling interval from TestSuite
			// settings. Setting the PollingInterval value to default: " + pollingInterval);
		}
		return pollingInterval;
	}

	/**
	 * Gets the execution settings.
	 * 
	 * @return the execution settings
	 */
	public Map<String, Map<String, String>> getExecutionSettings() {
		return executionSettings;
	}

	/**
	 * Sets the execution settings.
	 * 
	 * @param executionSettings
	 *            the execution settings
	 */
	/*
	 * public void setExecutionSettings(Map<String, Map<String, String>>
	 * executionSettings) { this.executionSettings = executionSettings; }
	 */

	/**
	 * Gets the environment filters for the specified environment name.
	 * 
	 * @param environmentName
	 *            the environment name
	 * @return the environmentFilters
	 */
	public String getEnvironmentFilters(String environmentName) {
		String environmentFilters = "";

		try {
			if (ExecutionSettings.getInstance().getExecutionSettings().containsKey("ENVIRONMENTFILTER")) {
				if (ExecutionSettings.getInstance().getExecutionSettings().get("ENVIRONMENTFILTER")
						.containsKey(environmentName)) {
					environmentFilters = ExecutionSettings.getInstance().getExecutionSettings().get("ENVIRONMENTFILTER")
							.get(environmentName);
				} else {
					LOGGER.error("ENVIRONMENTFILTER for Environment Name: " + environmentName
							+ " not found in TestSuite execution Settings");
					environmentFilters = "";
				}
			}
			/*
			 * else { LOGGER.
			 * error("TestSuite Execution Settings doesn't contain valid key: ENVIRONMENTFILTER for Environment: "
			 * + environmentName); environmentFilters = ""; }
			 */
		} catch (Exception e) {
			LOGGER.error("Unknown exception in getting ENVIRONMENTFILTER for name: " + environmentName + " ERROR:: "
					+ e.getMessage());
			environmentFilters = "";
		}

		return environmentFilters;
	}

	/**
	 * Gets the test suite name.
	 *
	 * @return the test suite name
	 */
	public String getTestSuiteName() {
		String execTestSuiteName = null;
		if (this.testSuiteName != null) {
			execTestSuiteName = this.testSuiteName;
		} else {
			Map<String, Map<String, String>> exSettings = getExecutionSettings();
			if (exSettings.get("SUITESETTINGS").containsKey("TESTSUITEFILENAME")) {
				execTestSuiteName = exSettings.get("SUITESETTINGS").get("TESTSUITEFILENAME");
			} else {
				execTestSuiteName = "TestSuite";
			}
		}
		return execTestSuiteName;
	}

	/**
	 * Sets the test suite name.
	 *
	 * @param testSuiteName
	 *            the new test suite name
	 */
	public void setTestSuiteName(String testSuiteName) {
		if (testSuiteName.trim().isEmpty()) {
			this.testSuiteName = null;
		} else {
			this.testSuiteName = testSuiteName;
		}
	}

	/**
	 * This Method returns application URL based on Application Id.
	 * 
	 * @param applicationID
	 *            the application id
	 * @return application URL
	 */
	public static String getApplicationURL(String applicationID) {
		String appURL = "NOT_FOUND";

		try {
			// String environmentSettings =
			// ExecutionSettings.getInstance().getExecutionEnvironment();
			String environmentSettings = ExecutionSettings.getInstance().getEnvironmentName();

			if (ExecutionSettings.getInstance().getExecutionSettings().containsKey("APPLICATIONURL")) {
				if (ExecutionSettings.getInstance().getExecutionSettings().get("APPLICATIONURL")
						.containsKey(applicationID + "_" + environmentSettings)) {
					appURL = ExecutionSettings.getInstance().getExecutionSettings().get("APPLICATIONURL")
							.get(applicationID + "_" + environmentSettings);
				} else {
					LOGGER.error("TestSuite execution Settings do not contain the applicationID under ApplicaionURL: "
							+ applicationID + "_" + environmentSettings);
					appURL = "NOT_FOUND";
				}
			} else {
				LOGGER.error("TestSuite Execution Settings do not contain valid key: APPLICATIONURL");
				appURL = "NOT_FOUND";
			}
		} catch (Exception e) {
			LOGGER.error("Unknown exception in getting application URL for applicationID: " + applicationID + "\n"
					+ e.getMessage());
			appURL = "NOT_FOUND";
		}

		return appURL;
	}

	/**
	 * This Method returns application logout URL based on Application Id.
	 * 
	 * @param applicationID
	 *            the application id
	 * @return application logout URL
	 */
	public static String getApplicationLogoutURL(String applicationID) {
		String appLogoutURL = "NOT_FOUND";

		try {
			String environmentSettings = ExecutionSettings.getInstance().getEnvironmentName();

			if (ExecutionSettings.getInstance().getExecutionSettings().containsKey("APPLICATIONLOGOUTURL")) {
				if (ExecutionSettings.getInstance().getExecutionSettings().get("APPLICATIONLOGOUTURL")
						.containsKey(applicationID + "_" + environmentSettings)) {
					appLogoutURL = ExecutionSettings.getInstance().getExecutionSettings().get("APPLICATIONLOGOUTURL")
							.get(applicationID + "_" + environmentSettings);
				} else {
					LOGGER.error(
							"TestSuite execution Settings do not contain the applicationID under APPLICATIONLOGOUTURL: "
									+ applicationID + "_" + environmentSettings);
					appLogoutURL = "NOT_FOUND";
				}
			} else {
				LOGGER.error("TestSuite Execution Settings do not contain valid key: APPLICATIONLOGOUTURL");
				appLogoutURL = "NOT_FOUND";
			}
		} catch (Exception e) {
			LOGGER.error("Unknown exception in getting application logout URL for applicationID: " + applicationID
					+ "\n" + e.getMessage());
			appLogoutURL = "NOT_FOUND";
		}

		return appLogoutURL;
	}

	/**
	 * This Method returns TailAdmin URL based on Environment Name.
	 *
	 * @return TailAdmin URL
	 */
	public static String getTailAdminURL() {
		String tailAdminURL = "NOT_FOUND";
		try {
			if (ExecutionSettings.getInstance().getExecutionSettings().containsKey("TAILADMINURL")) {
				if (ExecutionSettings.getInstance().getExecutionSettings().get("TAILADMINURL")
						.containsKey(ExecutionSettings.getInstance().getEnvironmentName())) {
					tailAdminURL = ExecutionSettings.getInstance().getExecutionSettings().get("TAILADMINURL")
							.get(ExecutionSettings.getInstance().getEnvironmentName());
				} else {
					LOGGER.error("TestSuite execution Settings doesn't have TailAdminURL for environment: "
							+ ExecutionSettings.getInstance().getEnvironmentName());
					tailAdminURL = "NOT_FOUND";
				}
			} else {
				LOGGER.error("TestSuite Execution Settings doesn't contain valid key :TAILADMINURL");
				tailAdminURL = "NOT_FOUND";
			}
		} catch (Exception e) {
			LOGGER.error("Unknown exception in getting TailAdminURL url for environment: "
					+ ExecutionSettings.getInstance().getEnvironmentName() + "\n" + e.getMessage());
			tailAdminURL = "NOT_FOUND";
		}

		return tailAdminURL;
	}

	/**
	 * This Method returns user defined variable value for the user defined variable
	 * name.
	 * 
	 * @param udvariableName
	 *            the udvariable name
	 * @return userdefinedvariablevalue
	 */
	public static String getUserDefinedVariableValue(String udvariableName) {
		String udValue = "";

		try {
			if (ExecutionSettings.getInstance().getExecutionSettings().containsKey("USERDEFINEDVARIABLES")) {
				if (ExecutionSettings.getInstance().getExecutionSettings().get("USERDEFINEDVARIABLES")
						.containsKey(udvariableName)) {
					udValue = ExecutionSettings.getInstance().getExecutionSettings().get("USERDEFINEDVARIABLES")
							.get(udvariableName);
				} else {
					LOGGER.error("TestSuite Execution Settings do not contain ExecutionVariableName: " + udvariableName
							+ " under ExecutionVariableType: USERDEFINEDVARIABLES");
					udValue = "NOT_FOUND";
				}
			} else {
				LOGGER.error(
						"TestSuite Execution Settings Settings do not contain ExecutionVariableType: USERDEFINEDVARIABLES");
				udValue = "NOT_FOUND";
			}
		} catch (Exception e) {
			LOGGER.error("Unknown exception in getting ExecutionVariableName:" + udvariableName
					+ " under ExecutionVariableType: USERDEFINEDVARIABLES. ERROR:: " + e.getMessage());
			udValue = "NOT_FOUND";
		}

		return udValue;
	}

}
