package com.ofss.serenity.cucumberSteps;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;

import com.ofss.Utilities.fileUtilities.ExcelUtilities;
import com.ofss.Utilities.frameworkUtilities.GenericUtilities;
import com.ofss.Utilities.frameworkUtilities.TestData;
import com.ofss.serenity.moduleActionSteps.GenericSteps;

import cucumber.api.java.en.Given;
import net.thucydides.core.annotations.Steps;

public class GenericStepDefinitions {
	@Steps
	private GenericSteps genericSteps;
	
    @Given("^test data sheet name \"([^\"]*)\" and test case ID \"([^\"]*)\"$")
    public void test_data_sheet_name_and_test_case_ID(String testDataSheetName, String testcaseID) throws Throwable {
    	
    	genericSteps.setTestDataforGivenFeature(testDataSheetName,testcaseID);

    }
  


}
