package com.ofss.serenity.pages;

import com.ofss.Utilities.frameworkUtilities.GenericUtilities;
import com.ofss.selenium.actions.WebUIElement;

import net.serenitybdd.core.pages.PageObject;

public class ProductPartyDetailsPage extends PageObject {

	 private WebUIElement webUIElement = new WebUIElement();
	 
	 public void ClickOnAdvanceSearchLink() {
		 
		 webUIElement.clickElement(getDriver(), "RTD_Link_WithText|Advanced Search");
		 
		 webUIElement.waitForElementToBeVisible(getDriver(), "RTD_PartySearch_BasedOn_RadioButton|Party Type");
		 
		 GenericUtilities.customAssertTrue(webUIElement.quickVerifyElementPresent(getDriver(), "RTD_PartySearch_BasedOn_RadioButton|Party Type"));
		 
	 }
	 
	 public void SelectPartyType(String partyTypeDropDownLabel) {
		 
		 webUIElement.clickElement(getDriver(), "RTD_PartySearch_BasedOn_RadioButton|Party Type");
		 
		 webUIElement.waitForElementToBeVisible(getDriver(), "PartyType_DropdownList");
		
		 webUIElement.selectFromDropdownByLabel(getDriver(), "PartyType_DropdownList", partyTypeDropDownLabel);
		 
		 webUIElement.waitForElementToBeVisible(getDriver(), "RTD_Input_WIthLabel|Party ID");
		 
		 GenericUtilities.customAssertTrue(webUIElement.quickVerifyElementPresent(getDriver(), "RTD_Input_WIthLabel|Party ID"));
			
	 }
	 
	 public void EnterSearchDetails(String fieldNameData, String searchValue) {
		 
		 GenericUtilities.customAssertTrue(webUIElement.quickVerifyElementPresent(getDriver(), "RTD_Input_WIthLabel|"+fieldNameData));
			
		 webUIElement.enterText(getDriver(), "RTD_Input_WIthLabel|" + fieldNameData, searchValue);
		 
	 }

	public void SearchAndAddFirstResult() {
		
		GenericUtilities.customAssertTrue(webUIElement.quickVerifyElementPresent(getDriver(), "RTD_Button_WithText|Search"));
		
		webUIElement.clickElement(getDriver(),  "RTD_Button_WithText|Search");
		
		webUIElement.waitForElementToBeVisible(getDriver(), "Product_AdvancedSerach_SearchResults_FirstRowSelect");
		
		webUIElement.clickElement(getDriver(), "Product_AdvancedSerach_SearchResults_FirstRowSelect");
		
		webUIElement.clickElement(getDriver(), "Party_AdvanceSearch_Ok_button");
		
		
	}

	public void SelectApplicantTypeAndAddPartyToProduct(String applicantType) {
		// TODO Auto-generated method stub
		
		webUIElement.clickElement(getDriver(), "RTD_Button_WithText|Ok");
		
		
	}
	 
}
