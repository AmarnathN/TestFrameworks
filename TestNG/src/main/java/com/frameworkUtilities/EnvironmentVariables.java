package com.frameworkUtilities;

import java.io.File;
import java.util.Map;

import org.apache.log4j.Logger;



public class EnvironmentVariables {
	
	private static final Logger LOGGER = Logger.getLogger(EnvironmentVariables.class);

	private String inputTestDataFolderPath ;
	private String globalTestDataFolderPath ;
	
	private String fieldDefinitionsFolderpath;
	
	private Map<String,String> environmentGlobalVariablesMap ;
	


	public EnvironmentVariables() {
		setEnvironmentVariables();
		
	}

	private void setEnvironmentVariables() {
		
		//this the main Artifacts and TAF folder paths for the execution
		String artifactsFolderPath = ExecutionSettings.getInstance().getArtifactsFolderPath();
		String testArtifactsFolderPath = ExecutionSettings.getInstance().getTestArtifactsFolderPath();
		
		//The Environment Test Data Folders paths
		String inputTestDataFolder = testArtifactsFolderPath + File.separator + "TestData_" + ExecutionSettings.getInstance().getEnvironmentName()
									+ File.separator + "Input" ;
		setInputTestDataFolderPath(inputTestDataFolder);
		
		
		String globalTestDataFolder = testArtifactsFolderPath + File.separator + "TestData_" + ExecutionSettings.getInstance().getEnvironmentName()
				+ File.separator + "Global" ;
		setGlobalTestDataFolderPath(globalTestDataFolder);
		
		//Field Definition folder path 
		String fieldDefnFoldername = "FieldDefinitions";
		String fieldDefnFolderpath = testArtifactsFolderPath + File.separator + fieldDefnFoldername;
		setFieldDefinitionsFolderpath(fieldDefnFolderpath);
		
	}

	private void setGlobalTestDataFolderPath(String globalTestData_Folder) {
		this.globalTestDataFolderPath = globalTestData_Folder ;
	}

	private void setFieldDefinitionsFolderpath(String fieldDefnFolderpath) {
		this.fieldDefinitionsFolderpath = fieldDefnFolderpath;
	}

	public String getInputTestDataFolderPath() {
		return inputTestDataFolderPath;
	}
	
	public String getFieldDefinitionsFolderpath() {
		return fieldDefinitionsFolderpath;
	}

	public void setInputTestDataFolderPath(String inputTestData_Folder) {
		this.inputTestDataFolderPath = inputTestData_Folder;
	}

	public String getGlobalTestDataFolderpath() {
		return globalTestDataFolderPath;
	}
	

	public Map<String, String> getEnvironmentGlobalVariablesMap() {
		return environmentGlobalVariablesMap;
	}

	public void setEnvironmentGlobalVariablesMap(Map<String, String> environmentGlobalVariablesMap) {
		this.environmentGlobalVariablesMap = environmentGlobalVariablesMap;
	}

	
}
