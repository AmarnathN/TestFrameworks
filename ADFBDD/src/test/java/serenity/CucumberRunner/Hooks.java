package serenity.CucumberRunner;


import com.ofss.Utilities.frameworkUtilities.ExecutionSettings;
import com.ofss.selenium.helpers.FieldDefinitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import net.thucydides.core.steps.StepEventBus;
 
public class Hooks {
	
	@Before
    public void beforeScenario(){
        

    	if(!ExecutionSettings.getInstance().isAbortForMajorCheckpointFailureOn()) {
    		StepEventBus.getEventBus().enableSoftAsserts();
    		System.out.println("This will run before the Scenario " + ExecutionSettings.getInstance().isAbortForMajorCheckpointFailureOn());
    	}
        new FieldDefinitions();
        
    }	
	
	@After
    public void afterScenario(){
        System.out.println("This will run after the Scenario");
    }
}
