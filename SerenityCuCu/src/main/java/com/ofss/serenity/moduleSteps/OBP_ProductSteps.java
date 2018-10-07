package com.ofss.serenity.moduleSteps;

import java.util.HashMap;
import java.util.Map;

import org.jboss.logging.Logger;

import com.ofss.Utilities.frameworkUtilities.TestData;
import com.ofss.serenity.moduleActionSteps.ProductDetailsSteps;

import net.thucydides.core.annotations.Steps;

public class OBP_ProductSteps {
	@Steps
	private ProductDetailsSteps productDetailsSteps;

	private static final Logger LOGGER = Logger.getLogger(OBP_ProductSteps.class.getName());
	
	

	public void FirstProduct_Add_PartyDetails_BasedOn_PartyType() {
		
		// Test Data Fields used in this method
		
		String FirstProduct_FirstApplicant_PartyType,FirstProduct_FirstApplicant_PartyID,FirstProduct_FirstApplicant_FirstName,FirstProduct_FirstApplicant_LastName,FirstProduct_FirstApplicant_ApplicantType,
				FirstProduct_SecondApplicant_PartyType,FirstProduct_SecondApplicant_PartyID,FirstProduct_SecondApplicant_FirstName,FirstProduct_SecondApplicant_LastName,FirstProduct_SecondApplicant_ApplicantType
				= null;
		
    	FirstProduct_FirstApplicant_PartyType	= TestData.getInstance().getTestData("TD_FirstProduct_FirstApplicant_PartyType".toUpperCase());
    	FirstProduct_SecondApplicant_PartyType	= TestData.getInstance().getTestData("TD_FirstProduct_SecondApplicant_PartyType".toUpperCase());
    	

		/*Selecting First Applicant for the first Product
		 * 
		 */
    	System.out.println( "FirstProduct_FirstApplicant_PartyType : " + FirstProduct_FirstApplicant_PartyType);
		if(FirstProduct_FirstApplicant_PartyType != null) {
				
				switch(FirstProduct_FirstApplicant_PartyType.toUpperCase()) {
				 
				case "INDIVIDUAL" :
					
					FirstProduct_FirstApplicant_PartyID 		= TestData.getInstance().getTestData("TD_FirstProduct_FirstApplicant_PartyID".toUpperCase());
					FirstProduct_FirstApplicant_FirstName 		= TestData.getInstance().getTestData("TD_FirstProduct_FirstApplicant_FirstName".toUpperCase());
					FirstProduct_FirstApplicant_LastName	 	= TestData.getInstance().getTestData("TD_FirstProduct_FirstApplicant_LastName".toUpperCase());
					FirstProduct_FirstApplicant_ApplicantType	= TestData.getInstance().getTestData("TD_FirstProduct_FirstApplicant_ApplicantType".toUpperCase());
					Map<String,String> searchDetailsMap = new HashMap<String,String>();
					searchDetailsMap.put("Party Id",FirstProduct_FirstApplicant_PartyID);
					searchDetailsMap.put("First Name",FirstProduct_FirstApplicant_FirstName);
					searchDetailsMap.put("Last Name",FirstProduct_FirstApplicant_LastName);
					
					productDetailsSteps.AdvanceSearchBasedOnPartyType(FirstProduct_FirstApplicant_PartyType);
					
					productDetailsSteps.enterPartyTypeSearchDetails(searchDetailsMap);
					
					productDetailsSteps.SearchAndAddTheFirstPartySearchResult();
					
					productDetailsSteps.SelectApplicantTypeAndAddPartyToProduct(FirstProduct_FirstApplicant_ApplicantType);
					
					break;
				default :
					LOGGER.error("Invalid first Product first Applicant Party Type");
				}
				
			}
		
		/*Selecting Second Applicant for the first Product
		 * 
		 */
		if(FirstProduct_SecondApplicant_PartyType != null) {
			
			switch(FirstProduct_SecondApplicant_PartyType.toUpperCase()) {
			 
			case "INDIVIDUAL" :
				
				FirstProduct_SecondApplicant_PartyID 		= TestData.getInstance().getTestData("TD_FirstProduct_SecondApplicant_PartyID".toUpperCase());
				FirstProduct_SecondApplicant_FirstName 		= TestData.getInstance().getTestData("TD_FirstProduct_SecondApplicant_FirstName".toUpperCase());
				FirstProduct_SecondApplicant_LastName 		= TestData.getInstance().getTestData("TD_FirstProduct_SecondApplicant_LastName".toUpperCase());
				FirstProduct_SecondApplicant_ApplicantType 	= TestData.getInstance().getTestData("TD_FirstProduct_SecondApplicant_ApplicantType".toUpperCase());
				
				Map<String,String> searchDetailsMap = new HashMap<String,String>();
				searchDetailsMap.put("Party Id",FirstProduct_SecondApplicant_PartyID);
				searchDetailsMap.put("First Name",FirstProduct_SecondApplicant_FirstName);
				searchDetailsMap.put("Last Name",FirstProduct_SecondApplicant_LastName);
				
				productDetailsSteps.AdvanceSearchBasedOnPartyType(FirstProduct_SecondApplicant_PartyType);
				
				productDetailsSteps.enterPartyTypeSearchDetails(searchDetailsMap);
				
				productDetailsSteps.SearchAndAddTheFirstPartySearchResult();
				
				productDetailsSteps.SelectApplicantTypeAndAddPartyToProduct(FirstProduct_SecondApplicant_ApplicantType);
				
				break;
			default :
				LOGGER.error("Invalid first Product Second Applicant Party Type");
			}
			
		}
				
				
		}


}
