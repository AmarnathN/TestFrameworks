package com.ofss.selenium.helpers;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.LoggerFactory;

import com.ofss.Utilities.frameworkUtilities.ExecutionSettings;

import net.serenitybdd.core.webdriver.driverproviders.ChromeDriverCapabilities;
import net.thucydides.core.webdriver.DriverSource;

public class DriverManager implements DriverSource {


	final private static Logger LOGGER = LoggerFactory.getLogger(DriverManager.class);

	@Override
	public WebDriver newDriver(){

		String browser = ExecutionSettings.getInstance().getExecutionBrowsers().get(0);
		WebDriver driver = null;
		try {
		switch (browser.toUpperCase()) {
		
		case "FIREFOX":
				{
					System.setProperty("webdriver.gecko.driver","Artifacts\\resourses\\geckodriver.exe");
					DesiredCapabilities dc = DesiredCapabilities.firefox();
					dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

					FirefoxProfile profile = new FirefoxProfile();
					profile.setAcceptUntrustedCertificates(true);

					dc.setCapability(FirefoxDriver.PROFILE, profile);

					// this is the important line - i.e. don't use Marionette
					dc.setCapability(FirefoxDriver.MARIONETTE, false);
					driver = new FirefoxDriver(dc);

					LOGGER.info("Firefox has been opned for testing");
					break;
				}
				
		case "GOOGLECHROME" :
				{
					System.setProperty("webdriver.chrome.driver","Artifacts\\resourses\\chromedriver.exe");
					DesiredCapabilities capabilities = DesiredCapabilities.chrome();
					ChromeOptions options = new ChromeOptions();
					options.addArguments("test-type");
					capabilities.setCapability("chrome.binary", "Artifacts\\resourses\\chromedriver.exe");
					capabilities.setCapability(ChromeOptions.CAPABILITY, options);
					driver = new ChromeDriver(capabilities);
					System.out.println(driver);
					LOGGER.info("Chrome has been opned for testing");
					break;
				}
		
		case "INTERNETEXPLORER"     :
				{
					System.setProperty("webdriver.ie.driver","Artifacts\\resourses\\IEDriverServer.exe");
					
					DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
					capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS,true);
				//	capabilities.setCapability(InternetExplorerDriver.true);
					capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
					
					driver = new InternetExplorerDriver(capabilities);
					
					LOGGER.info("Internet Explorer has been opned for testing");
					break;
				
				}
		
		
		default :
				{
					LOGGER.error("Improper Browser has been Choosen");
				}
		
			
		}
		}catch(Exception e) {
			LOGGER.error("Exception in getting driver " + e);
		}
      
		return driver;
	}

	@Override
	public boolean takesScreenshots() {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	

	
}
