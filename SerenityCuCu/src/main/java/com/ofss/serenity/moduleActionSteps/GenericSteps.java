package com.ofss.serenity.moduleActionSteps;

import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Assert;

import com.ofss.Utilities.fileUtilities.ExcelUtilities;
import com.ofss.Utilities.frameworkUtilities.TestData;
import com.ofss.serenity.cucumberSteps.OBPStepDefinitions;

import net.thucydides.core.annotations.Step;

public class GenericSteps {
	private static final Logger LOGGER = Logger.getLogger(GenericSteps.class.getName());
	
	@Step
	public void setTestDataforGivenFeature(String testDataSheetName, String testcaseID) {
		 ArrayList<Map<String, String>> testDataList = null;
	    	
		    //GenericUtilities.intiateLoggers();
				try {
					testDataList = (new ExcelUtilities().getTestDataMapList(testDataSheetName));
					if(testDataList!=null) {
						
						for (int i=0 ;i< testDataList.size();i++) {
							if(testDataList.get(i).get("UNIQUETESTCASEID").equalsIgnoreCase(testcaseID)) {
								TestData.getInstance().addRuntimeData("TestDataMap".toUpperCase(), testDataList.get(i)) ;
								LOGGER.info("The test Data Used is : " + TestData.getInstance().getRuntimeData("TESTDATAMAP"));
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
	
	@Step
	public void InfoMessage(String info){
		
	}
	
	

}
