package com.ofss.moduleActionSteps;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.ofss.helpers.fluentWait;
import com.ofss.selenium.actions.WebUIElement;

public class PageObject {
	private WebDriver driver;
	private WebUIElement webUIElement= new WebUIElement();
	private static final Logger LOGGER = LogManager.getLogger(PageObject.class);

	protected WebDriver getDriver() {
		return driver;
	}

	protected void setDriver(WebDriver driver) {
		this.driver = driver ;
		BaseTest.getInstance().setDriver(driver);
	}
	
	public PageObject() {
		this.driver = BaseTest.getInstance().getDriver();
	}
	
	
	public void switchWindow(String switchType, String switchReference) {
		WebDriver switchedDriver = getDriver();
		new fluentWait(getDriver()).waitUntil("waitTillNewWindowOpens");
		switch(switchType.toUpperCase()) {
		case "SWITCHWINDOWBYTITLENOTCONTAINS":
			switchedDriver = webUIElement.switchWindowByTitleNotContains(driver,"Oracle Banking Platform");
			break;
		default :
			LOGGER.error("Invalid Switch Type ");
		}
		BaseTest.getInstance().setDriver(switchedDriver);
	       
	}
	
}
