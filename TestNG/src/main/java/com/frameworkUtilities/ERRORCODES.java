package com.frameworkUtilities;

public class ERRORCODES {
    public static final int DEFAULT_CODE = -1; // Code set as default and considered as Fail

	public static final int PASS = 0; // The test case step is pass

	public static final int OPERATION_FAILURE = 1; // Operation failure in the keyword
	public static final int TESTDATA_NOT_FOUND = 2; // Testdata not found
	public static final int ELEMENT_NOT_FOUND = 3; // Element not found
	public static final int UNKNOWN_ERROR_IN_KEYWORD = 4;
	public static final int VERIFICATION_FAILURE = 5; // All Verify keywords should have this error code for verification failures.
	public static final int NULL_VALUE_FOUND = 6;
	public static final int NOT_A_SELECTBOX = 7; // Invalid select element
	public static final int INVALID_TEST_DATA = 8;
	public static final int INVALID_TEST_DATA_TYPE = 9;
	public static final int FILE_NOT_FOUND = 10; // File not found
	public static final int WAIT_FOR_SERVER_INCOMPLETE = 11; // wait for server / wait for page to load failure
	public static final int TESTCASESTEP_UNKNOWN_EXCEPTION = 12; // Unknown exception in test case step run
	public static final int INVALID_KEYWORD = 13; // Invalid keyword
	public static final int CHILD_ELEMENT_NOT_FOUND = 14;
	public static final int NOT_A_TABLE_ELEMENT = 15;
	public static final int NOT_A_CHECKBOX_ELEMENT = 16; // Element is not a check box
	//public static final int WAIT_FOR_SERVER_TIMEOUT = 16;
	public static final int MISSING_OR_INVALID_ATTRIBUTE = 17;
	public static final int EXECUTE_STEP_ERROR = 20;
	//public static final int MODAL_DIALOG_FOUND = 21;
	public static final int ADF_DIALOG_NOT_FOUND = 25;

	public static final int ABORTED = 101; // The test case step is aborted
	public static final int SKIPPED = 102; // The test case step is skipped as part of a test step group
	public static final int NOT_EXECUTED = 103; // The test case step is not executed

	public static final int SOAP_CALL_NOT_EXECUTED = 203;
	public static final int SOAP_CONFIG_NOT_EXECUTED = 204;

	public static final int NULL_VALUE_RETURNED_FROM_DB = 301;
	public static final int DB_OPERATION_FAILED = 303;
	//public static final int INVALID_SQL_QUERY = 330;
	//public static final int INVALID_INSERT_QUERY = 331;
	//public static final int INVALID_UPDATE_QUERY = 332;
	//public static final int INVALID_SELECT_QUERY = 333;

	

	
}
