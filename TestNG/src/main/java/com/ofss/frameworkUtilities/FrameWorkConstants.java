package com.ofss.frameworkUtilities;

import java.io.File;

public class FrameWorkConstants {
	
	public static final String TESTARTIFACTS_ROOTFOLDERPREFIX = "TAF_" ;
	
	

    public static final String FIELD_DEFINITIONS_FILENAME = "FieldDefinitions";

     public static final String TESTDATA_FOLDERNAME_PREFIX = "TestData_";
    
    public static final String TESTDATA_FILENAME_PREFIX = "TD_";
	
    public static final String GLOBAL_SETTINGS_FOLDERNAME = "Settings";
    
    public static final String GLOBAL_SETTINGS_FILENAME = "GlobalSettings";
    
    public static final String BROWSERSPROPERTIESFILENAME = "Browsers.properties";
    
	public static final String LOG4JPROPERTIESFILENAME = "log4j.properties";

	public static final String MASTERDBPROPERTIESFILENAME = "Masterdb.properties";
    
  	public static final String DBCONSTANTS_FILENAME = "DBConstants.csv";



	public static final String ENVIRONMENTGLOBALVARIABLES_FILENAME = "EnvironmentGlobalVariables";
  	
  	

    /**
     * The Enum ExecutionSettingsColumnNames.
     */
    public static enum ExecutionSettingsColumnNames {
        EXECUTIONVARIABLETYPE, EXECUTIONVARIABLENAME, EXECUTIONVARIABLEVALUE
    }

    /**
     * The Enum FieldDefinitionsColumnNames.
     */
    public static enum FieldDefinitionsColumnNames {
        SCREENNAME, FIELDNAME, LOCATORTYPE, FIELDDEFINITION
    }
    
    /**
     * The Enum EnvironmentGlobalVariablesColumnNames.
     */
    public static enum EnvironmentGlobalVariablesColumnNames {
        
    	VARIABLENAME, VARIABLEVALUE
    }
    
    
	/**
	 * The Enum BrowserNames.
	 */
	public static enum BrowserNames {
		INTERNETEXPLORER, GOOGLECHROME, FIREFOX, UNKNOWN, REMOTEINTERNETEXPLORER, REMOTECHROME
	}
	

	/**
	 * The Enum LocatorType.
	 */
	public static enum LocatorType {
		CLASSNAME, CSSSELECTOR, ID, LINKTEXT, NAME, PARTIALLINKTEXT, TAGNAME, XPATH
	}

	/**
	 * The Enum TestdataType.
	 */
	public static enum TestdataType {
		CONSTANT, TDPARAMETER, ENVIRONMENTVARIABLE, RUNTIMEVARIABLE, RANDOMMOBILE, RANDOMTELEPHONENO,
		RANDOMEMAILID, RANDOMSTRING, RANDOMNUMBER, RANDOMDATE, RANDOMDATERANGE, RANDOMPHONE, DBVARIABLE, GLOBALVARIABLE
	}
	

}
