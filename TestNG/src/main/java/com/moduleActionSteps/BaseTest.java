package com.moduleActionSteps;

import org.openqa.selenium.WebDriver;

public class BaseTest {
	
//	private static BaseTest instance;
	
	private  BaseTest() {
		
	}
	
	private static final ThreadLocal<BaseTest> _locaStorage = new ThreadLocal<BaseTest>() {
		protected BaseTest initialValue() {
			return new BaseTest();
		}
	};
		  
	public static BaseTest getInstance() {
		return _locaStorage.get();
	}
	
	private WebDriver driver;
		
	
	public WebDriver getDriver() {
		
		return this.driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	
	


}
