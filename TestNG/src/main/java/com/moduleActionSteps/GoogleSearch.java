package com.moduleActionSteps;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.frameworkUtilities.ExecutionSettings;
import com.pages.GoogleHomePage;
import com.pages.GoogleSearchResultsPage;

public class GoogleSearch {
	private static final Logger LOGGER = Logger.getLogger(GoogleSearch.class);
	GoogleHomePage googleHomePage = new GoogleHomePage();
	GoogleSearchResultsPage googleSearchResultsPage = new GoogleSearchResultsPage();
	
	public void isOnGoogleHomePage() {
			
		googleHomePage.openURL(ExecutionSettings.getApplicationURL("GOOGLE"));
		googleHomePage.verifyOnHomePage();
		LOGGER.info("Is On Home Page");
    }
 
	public void searchGivenData(String searchData) {
		
		googleHomePage.searchText(searchData);
		googleHomePage.search();
		googleSearchResultsPage.verifySearchResultsLoaded();
		
	}
	
	public void selectLastResult() {
		googleSearchResultsPage.clickOnLastSearchResult();
	}

}
