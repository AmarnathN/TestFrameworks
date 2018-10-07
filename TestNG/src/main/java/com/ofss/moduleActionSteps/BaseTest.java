package com.ofss.moduleActionSteps;

import org.openqa.selenium.WebDriver;

public class BaseTest {
	
	private static BaseTest instance;
	
	private  BaseTest() {
		
	}
	
	private static final ThreadLocal<BaseTest> _localStorage = new ThreadLocal<BaseTest>() {
		protected BaseTest initialValue() {
			return new BaseTest();
		}
	};
		  
	public static BaseTest getInstance() {
		return _localStorage.get();
	}
	
	private WebDriver driver;
		
	
	public WebDriver getDriver() {
		
		return this.driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	
	


}
