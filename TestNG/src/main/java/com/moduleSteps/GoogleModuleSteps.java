package com.moduleSteps;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.aventstack.extentreports.ExtentTest;
import com.frameworkUtilities.TestData;
import com.moduleActionSteps.BaseTest;
import com.moduleActionSteps.GoogleSearch;
import com.moduleActionSteps.LoginSteps;

public class GoogleModuleSteps {

	GoogleSearch googleSearch = new GoogleSearch();
	private static final Logger LOGGER = Logger.getLogger(GoogleModuleSteps.class);
	private ExtentTest test;

	public GoogleModuleSteps(ExtentTest test) {
		this.test = test;
	}

	public void LoadHomePage() {

		ExtentTest childTest = test.createNode("LoadHomePage");

		LOGGER.info("Level2 :" + BaseTest.getInstance().getDriver());

		googleSearch.isOnGoogleHomePage();
		childTest.pass("The application launched");

	}

	public void SearchgivenData() {

		ExtentTest childTest = test.createNode("SearchgivenData");

		String givenSearchData = TestData.getInstance().getTestData("TD_SearchInput") ;
	    
		LOGGER.info("Level2 :" + BaseTest.getInstance().getDriver());
		
		googleSearch.searchGivenData(givenSearchData);
		childTest.pass("The given Data is Searched");

	}
	
	public void moveToLastSearch() {

		ExtentTest childTest = test.createNode("moveToLastSearch");

		
		googleSearch.selectLastResult();
		childTest.pass("The given Data is Searched");

	}

	
	
}
