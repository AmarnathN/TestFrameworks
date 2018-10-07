package com.reports;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentManager {

		
		private static ExtentReports extent;
		private ExtentTest test;
		private ExtentHtmlReporter htmlReporter;
		private String filePath = System.getProperty("user.dir")+ File.separator + 
										"ExtentReports" +File.separator + "extentreport.html";
		
		private ExtentManager() {
			
		}
		
		private static final ThreadLocal<ExtentManager> _localStorage = new ThreadLocal<ExtentManager>() {
			protected ExtentManager initialValue() {
				return new ExtentManager();
			}
		};
		
		public static ExtentManager getInstance() {
			return _localStorage.get();
		}
		
		
		public ExtentReports GetExtent(){
			if (extent != null)
	                    return extent; //avoid creating new instance of html file
	                extent = new ExtentReports();		
			extent.attachReporter(getHtmlReporter());
			return extent;
		}
	 
		private ExtentHtmlReporter getHtmlReporter() {
		
	        htmlReporter = new ExtentHtmlReporter(filePath);
			
		// make the charts visible on report open
	        htmlReporter.config().setChartVisibilityOnOpen(true);
			
	        htmlReporter.config().setDocumentTitle("QAV automation report");
	        htmlReporter.config().setReportName("Regression cycle");
	        return htmlReporter;
		}
		
		public ExtentTest createTest(String name, String description){
			test = extent.createTest(name, description);
			return test;
		}

}
