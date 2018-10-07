package com.ofss.serenity.cucumberSteps;

import org.apache.log4j.Logger;

import com.ofss.serenity.moduleSteps.OBPModuleSteps;
import com.ofss.serenity.moduleSteps.OBP_ProductSteps;

import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class OBPStepDefinitions {

	private static final Logger LOGGER = Logger.getLogger(OBPStepDefinitions.class.getName());
	@Steps
	private OBPModuleSteps obpModuleSteps;
	@Steps
	private OBP_ProductSteps obp_ProductSteps;
	
	@When("^User Logs in to Application Form$")
	public void user_Logs_in_to_Application_Form() throws Exception {
		
		obpModuleSteps.Logs_Into_The_Application();
		
	}

	@When("^Selects the Products with required offers and Start Application$")
	public void selects_the_Product_with_required_offers_and_Start_Application() throws Exception {
		
		obpModuleSteps.Select_The_Products_For_Application();
		
	}
	

	@When("^Add the Party Details of the First Product$")
	public void Add_the_Party_Details_of_the_First_Product() throws Exception {
		
		obp_ProductSteps.FirstProduct_Add_PartyDetails_BasedOn_PartyType();
		
	}

	
	
}
