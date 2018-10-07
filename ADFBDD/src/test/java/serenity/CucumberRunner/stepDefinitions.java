package serenity.CucumberRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;

import com.ofss.Utilities.fileUtilities.ExcelUtilities;
import com.ofss.Utilities.frameworkUtilities.ExecutionSettings;
import com.ofss.Utilities.frameworkUtilities.TestData;
import com.ofss.selenium.helpers.FieldDefinitions;
import com.ofss.serenity.moduleActionSteps.ProductPartyDetailsSteps;
import com.ofss.serenity.moduleActionSteps.ProductSelectionSteps;
import com.ofss.serenity.moduleSteps.OBPModuleSteps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.StepEventBus;
import serenity.features.authentication.OriginationTest;

public class stepDefinitions {
	
	private static final Logger LOGGER = Logger.getLogger(stepDefinitions.class.getName());
    @Steps
    private OBPModuleSteps obpModuleSteps;


    
    @Given("^test data sheet name \"([^\"]*)\" and test case ID \"([^\"]*)\"$")
    public void test_data_sheet_name_and_test_case_ID(String testDataSheetName, String testcaseID) throws Throwable {
    	
    	ArrayList<Map<String, String>> testDataList = null;
    	
    	
		try {
			testDataList = (new ExcelUtilities().getTestDataMapList(testDataSheetName));
			if(testDataList!=null) {
				
				for (int i=0 ;i< testDataList.size();i++) {
					if(testDataList.get(i).get("UNIQUETESTCASEID").equalsIgnoreCase(testcaseID)) {
						TestData.addRuntimeData("TestDataMap", testDataList.get(i)) ;
						LOGGER.info("The test Data Used is : " + TestData.getRuntimeData("TestDataMap"));
						break;
						
					};
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
				LOGGER.info("Exception In reading Test Data ");
				Assert.assertTrue(false);
		}
			


    }


    @When("^User Login to Application Form screen with username \"([^\"]*)\" and Password \"([^\"]*)\"$")
    public void user_Login_to_Application_Form_screen_with_username_and_Password(String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
    		System.out.println(arg1 + arg2);
	    	String userName = TestData.getTestData(arg1) ;
	    	String passWord = TestData.getTestData(arg2) ;
	    	System.out.println(userName + passWord);
	    	obpModuleSteps.Logs_Into_The_Application(userName,passWord,"OR097");   
    	 
    	 
    	 }
    @When("^Select \"([^\"]*)\" of \"([^\"]*)\" Product with offer \"([^\"]*)\" and Start Application$")
    public void select_of_Product_with_offer_and_Start_Application(String arg1, String arg2, String arg3) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
    	

    	String applicationType = TestData.getTestData(arg1) ;
    	String productType = TestData.getTestData(arg2) ;
    	String productOffer = TestData.getTestData(arg3) ;
    	
    	obpModuleSteps.Select_The_Products_For_Application(applicationType,productType,productOffer);

     
    }

    @Then("^search party  based on \"([^\"]*)\" search by \"([^\"]*)\" with search details :PartyId \"([^\"]*)\",First Name \"([^\"]*)\",Last Name \"([^\"]*)\",Email Id \"([^\"]*)\" and add as \"([^\"]*)\"$")
    public void search_party_based_on_search_by_with_search_details_PartyId_First_Name_Last_Name_Email_Id_and_add_as(String ApplicantBasedOn, String ApplicantPartyType, String ApplicantPartyId, String ApplicantFirstName, String ApplicantLastName, String ApplicantEmailId, String ApplicantType) throws Throwable {

        switch (ApplicantType.toUpperCase()){
            case "PRIMARY APPLICANT":
              //  ProductPartyDetailsSteps.gotoAdvanceSearch();

                break;
            case "JOINT APPLICANT":
                break;
            default :
                System.out.println("Improper Applicant type");
                Assert.assertFalse(false);

        }

        // Write code here that turns the phrase above into concrete actions
     System.out.println("Inside");



    }


  
  

}
