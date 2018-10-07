package com.ofss.serenity.cucumberRunnerClasses;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
//@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-html-report"},
    //  plugin = {"pretty", "com.epam.reportportal.cucumber.ScenarioReporter"},
		glue = {"com.ofss.serenity.cucumberSteps"},
        features = "src\\test\\resources\\features\\BisConfig"
)
public class CucumberRunner {

}
