package com.pages;

import com.frameworkUtilities.PageObject;
import com.helpers.ElementLocator;
import com.helpers.fluentWait;

public class GoogleSearchResultsPage  extends PageObject{
	
	public void clickOnLastSearchResult() {
		int count  = getElementCount("SiteSearchResults");
		
		clickElement("RTD_SiteSearchResults|"+count);
		
		new fluentWait(getDriver()).waitUntil("pageLoaded");
		
	}
	
	  public void verifySearchResultsLoaded() {
	    	new fluentWait(getDriver()).waitUntil("elementToBeVisible", ElementLocator.getInstance().getLocator("RTD_Element_WithIDContains|hdtbSum"));
	        quickVerifyElementPresent("RTD_Element_WithIDContains|hdtbSum");
	    }
}
