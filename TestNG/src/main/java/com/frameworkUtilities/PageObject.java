package com.frameworkUtilities;

import java.util.List;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.helpers.ElementLocator;
import com.helpers.fluentWait;
import com.moduleActionSteps.BaseTest;
import com.selenium.actions.WebUIElement;

public class PageObject {
	private WebDriver driver;
	private WebUIElement webUIElement = new WebUIElement();
	private static final Logger LOGGER = LogManager.getLogger(PageObject.class);
	private WebElement webElement;

	public PageObject() {
		this.driver = BaseTest.getInstance().getDriver();
	}
	
	protected WebDriver getDriver() {
		return driver;
	}

	protected void setDriver(WebDriver driver) {
		this.driver = driver;
		BaseTest.getInstance().setDriver(driver);
	}

	

	public void switchWindow(String switchType, String switchReference) {
		WebDriver switchedDriver = getDriver();
		new fluentWait(getDriver()).waitUntil("waitTillNewWindowOpens");
		switch (switchType.toUpperCase()) {
		case "SWITCHWINDOWBYTITLENOTCONTAINS":
			switchedDriver = webUIElement.switchWindowByTitleNotContains(driver, "Oracle Banking Platform");
			break;
		default:
			LOGGER.error("Invalid Switch Type ");
		}
		BaseTest.getInstance().setDriver(switchedDriver);

	}

	public int enterText(String fieldName, String inputData) {

		int exitcode = ERRORCODES.DEFAULT_CODE;
		try {
			webElement = null;
			webElement = ElementLocator.getInstance().findElement(driver, fieldName);
			if (!inputData.isEmpty() && (webElement != null)) {
				webElement.clear();
				webElement.sendKeys(inputData);
				exitcode = ERRORCODES.PASS;
				LOGGER.info("Data has been sent to Element");
			} else {
				exitcode = ((webElement != null) ? ERRORCODES.ELEMENT_NOT_FOUND : ERRORCODES.TESTDATA_NOT_FOUND);
			}
		} catch (Exception e) {
			LOGGER.error("Exception in Entering Data : " + inputData + " in element :" + webElement + e);
		}
		return exitcode;
	}

	public int clickElement(String fieldName) {

		int exitcode = ERRORCODES.DEFAULT_CODE;
		try {
			webElement = null;
			webElement = ElementLocator.getInstance().findElement(driver, fieldName);
			if ((webElement != null)) {
				int attempts = 0;

				while (attempts < 2) {
					try {
						webElement.click();
						break;
					} catch (StaleElementReferenceException e) {
					}
					attempts++;
				}
				exitcode = ERRORCODES.PASS;
				LOGGER.info("Clicked on the element " + webElement);
			} else {
				exitcode = ERRORCODES.ELEMENT_NOT_FOUND;
			}
		} catch (Exception e) {
			LOGGER.error("Exception in Clicking Webelement : " + webElement + " " + e);
		}

		return exitcode;

	}
	
	public int getElementCount(String fieldName) {

		int exitcode = ERRORCODES.DEFAULT_CODE;
		int countOfElements =0;
		try {
			webElement = null;
			webElement = ElementLocator.getInstance().findElement(driver, fieldName);
			if ((webElement != null)) {
				int attempts = 0;

				while (attempts < 2) {
					try {
						List<WebElement> elements = ElementLocator.getInstance().findElements(driver, fieldName);
						countOfElements = elements.size();
						break;
					} catch (StaleElementReferenceException e) {
					}
					attempts++;
				}
				exitcode = ERRORCODES.PASS;
				LOGGER.info("Count of the given element : " + countOfElements);
			} else {
				exitcode = ERRORCODES.ELEMENT_NOT_FOUND;
			}
		} catch (Exception e) {
			LOGGER.error("Exception in getting count of element : " + webElement + " " + e);
		}

		return countOfElements;

	}


	public Boolean quickVerifyElementPresent(String fieldName) {
		Boolean flag = false;
		try {
			webElement = null;
			webElement = ElementLocator.getInstance().findElement(driver, fieldName);
			if ((webElement != null)) {
				flag = true;
			}

		} catch (Exception e) {
			System.out.println(this.getClass().getName() + " : " + "Exception in quickVerify : " + e);
		}

		return flag;

	}

	public WebDriver switchWindowByTitleNotContains(String nonTitleString) {
		int exitCode = ERRORCODES.DEFAULT_CODE;
		WebDriver SwitchedDriver = null;
		try {

			if (driver != null) {
				Set<String> winHandles = driver.getWindowHandles();

				for (String winHandle : winHandles) {
					System.out.println("Handle :" + winHandle);
					WebDriver popup = driver.switchTo().window(winHandle);
					String browserName = ((RemoteWebDriver) popup).getCapabilities().getBrowserName();
					System.out.println(browserName + " : : " + popup.getTitle());
					if (!(popup.getTitle().trim().contains(nonTitleString))) {

						SwitchedDriver = popup;
						break;
					}
				}
			} else {
				LOGGER.info("NO Driver available ");
			}
		} catch (Exception e) {

			LOGGER.error("Unknown Exception in Switching window by Title not contains " + e);

		}
		return SwitchedDriver;

	}

	public void navigateToURL(String url) {
		try {
			
			driver.get(url);
			Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
			String browserName = cap.getBrowserName().toLowerCase();
			System.out.println(browserName);
			String os = cap.getPlatform().toString();
			System.out.println(os);
			String v = cap.getVersion().toString();
			System.out.println(v);
			// if ((((RemoteWebDriver)
			// getWebdriver()).getCapabilities().getBrowserName()).equalsIgnoreCase("internet
			// explorer")) {
			if (browserName.trim().equalsIgnoreCase("INTERNETEXPLORER")
					|| browserName.trim().equalsIgnoreCase("REMOTEINTERNETEXPLORER")) {

				if (driver.findElement(By.id("overridelink")).isDisplayed()) {
					// ElementLocator.getInstance().ignoreSecurityCertificateErrorInIE(testcaseStep.getTestDataValue());

					driver.get("javascript:document.getElementById('overridelink').click();");

				}

			}
		} catch (Exception e) {

		}

	}

}
