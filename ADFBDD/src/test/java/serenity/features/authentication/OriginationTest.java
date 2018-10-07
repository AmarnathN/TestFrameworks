package serenity.features.authentication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.apache.log4j.xml.DOMConfigurator;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.ofss.Utilities.fileUtilities.ExcelUtilities;
import com.ofss.Utilities.frameworkUtilities.ExecutionSettings;
import com.ofss.serenity.moduleSteps.OBPModuleSteps;
import com.ofss.serenity.pages.BasePageofOrigination;
import com.ofss.serenity.requirements.Requirements;


import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Narrative;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.steps.StepEventBus;
import net.thucydides.junit.annotations.TestData;

@Narrative(text={"1. In order to choose the best flight for my travels",
        "As a traveller",
        "I want to be able to search for flights between specific destinations"})
@RunWith(SerenityParameterizedRunner.class)

@Story(Requirements.BussinessConfig.Homeloan2.class)

public class OriginationTest{

  //  private String applicationType,productType,productOffer;
	Map<String, String> testDataMap = null;


	
	@TestData
    public static Collection< Object[] > testData() {
    	
    	ArrayList<Map<String, String>> testDataList = null;
    	Collection<Object[]> finalTestData = new ArrayList<Object[]>() ;
    	
		try {
			testDataList = (new ExcelUtilities().getTestDataMapList(OriginationTest.class.getSimpleName()));
			if(testDataList!=null) {
				
				for (int i=0 ;i< testDataList.size();i++) {
					Object[] testDataMaps = new Object[1];
					testDataMaps[0]=testDataList.get(i);
					finalTestData.add(testDataMaps);
				}
				System.out.println(finalTestData);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
		}
    	return finalTestData ;

    }

    public OriginationTest (Object testdata){
    	this.testDataMap = (Map<String, String>) testdata;
    }


    @Steps
    private OBPModuleSteps obpModuleSteps;
    @Steps
    private BasePageofOrigination basePageofOrigination ;



    @Managed
    public WebDriver userBrowser;
    
    @Before
    public void BeforeMethod() {
    	if(!ExecutionSettings.getInstance().isAbortForMajorCheckpointFailureOn()) {
    		
    		StepEventBus.getEventBus().enableSoftAsserts();
    	}
    }
    @Test

    public void Submit_The_Application_Form1() {
    	if(testDataMap!=null) {
    		 obpModuleSteps.Logs_Into_The_Application("OFSSUSER","welcome1","OR097");

    	     obpModuleSteps.Select_The_Products_For_Application(testDataMap.get("Application Type"),testDataMap.get("Product Type"),testDataMap.get("Product Offer"));

    	}else {
    		Assert.assertNotNull(testDataMap);
    	}
       
    }
    
    @After
    public void AfterMethod() {

       
    }

}
