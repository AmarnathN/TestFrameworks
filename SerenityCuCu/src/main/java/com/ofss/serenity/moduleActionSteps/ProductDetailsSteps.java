package com.ofss.serenity.moduleActionSteps;

import java.util.Map;

import com.ofss.serenity.pages.ProductPartyDetailsPage;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class ProductDetailsSteps {
	@Steps
	private GenericSteps genericSteps;
	
    private ProductPartyDetailsPage productPartyDetailsPage ;
    
    @Step
    public void AdvanceSearchBasedOnPartyType(String partyType) {
    	productPartyDetailsPage.ClickOnAdvanceSearchLink();
    	
    	productPartyDetailsPage.SelectPartyType(partyType);
    }
    
    @Step
    public void enterPartyTypeSearchDetails(Map<String,String> searchDetailsMap) {
    	
    	for(String field : searchDetailsMap.keySet()) {
    		String searchValue = searchDetailsMap.get(field);
    		productPartyDetailsPage.EnterSearchDetails(field, searchValue);
    		genericSteps.InfoMessage("Entered field " + field + " Search Value ");
    	}
    	
    	
    	
    	
    }
    
    @Step
    public void SearchAndAddTheFirstPartySearchResult() {
    	
    	productPartyDetailsPage.SearchAndAddFirstResult();
		
    }
    
    
    @Step
    public void SelectApplicantTypeAndAddPartyToProduct(String applicantType) {
    	
    	productPartyDetailsPage.SelectApplicantTypeAndAddPartyToProduct(applicantType);
    }
    

    @Step
    public void GotoProductFacilityDetails() {
    	
    
		
    }
    
    

}
