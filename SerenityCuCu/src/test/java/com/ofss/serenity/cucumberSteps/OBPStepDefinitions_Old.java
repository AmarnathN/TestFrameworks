package com.ofss.serenity.cucumberSteps;

import org.apache.log4j.Logger;

import com.ofss.serenity.moduleSteps.OBPModuleSteps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class OBPStepDefinitions_Old {
	private static final Logger LOGGER = Logger.getLogger(OBPStepDefinitions.class.getName());
	@Steps
	private OBPModuleSteps obpModuleSteps;
	
	@When("^User Login to Application Form screen with username \"([^\"]*)\" and Password \"([^\"]*)\"$")
	public void user_Login_to_Application_Form_screen_with_username_and_Password(String arg1, String arg2)
			throws Throwable {
		/*
		String userName = TestData.getTestData(arg1.toUpperCase());
		String passWord = TestData.getTestData(arg2.toUpperCase());

		// obpModuleSteps.Logs_Into_The_Application(userName,passWord,"OR097");
		 * 
		 */
	}

	@When("^Select \"([^\"]*)\" of \"([^\"]*)\" Product with offer \"([^\"]*)\" and Start Application$")
	public void select_of_Product_with_offer_and_Start_Application(String arg1, String arg2, String arg3)
			throws Throwable {
/*
		// System.out.println(arg1 + " " + arg2 + " " + arg3);
		String applicationType = TestData.getTestData(arg1.toUpperCase());
		String productType = TestData.getTestData(arg2.toUpperCase());
		String productOffer = TestData.getTestData(arg3.toUpperCase());

		// System.out.println(applicationType + " " + productType + " " + productOffer);

		// obpModuleSteps.Select_The_Products_For_Application(applicationType,productType,productOffer);
*/
	}

	@Then("^search party  based on \"([^\"]*)\" search by \"([^\"]*)\" with search details :PartyId \"([^\"]*)\",First Name \"([^\"]*)\",Last Name \"([^\"]*)\",Email Id \"([^\"]*)\" and add as \"([^\"]*)\"$")
	public void search_party_based_on_search_by_with_search_details_PartyId_First_Name_Last_Name_Email_Id_and_add_as(
			String ApplicantBasedOn, String ApplicantPartyType, String ApplicantPartyId, String ApplicantFirstName,
			String ApplicantLastName, String ApplicantEmailId, String ApplicantType) throws Throwable {

		switch (ApplicantType.toUpperCase()) {
		case "PRIMARY APPLICANT":
			// ProductPartyDetailsSteps.gotoAdvanceSearch();

			break;
		case "JOINT APPLICANT":
			break;
		default:
			System.out.println("Improper Applicant type");
			// Assert.assertFalse(false);

		}

		// Write code here that turns the phrase above into concrete actions
		System.out.println("Inside");

	}

}
