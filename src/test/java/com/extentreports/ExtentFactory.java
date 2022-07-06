package com.extentreports;

import com.aventstack.extentreports.ExtentReports;


public class ExtentFactory {
	
	public static ExtentReports getInstance()
	{
		ExtentReports extent = new ExtentReports();
		extent.setSystemInfo("OS", "Windows 10");
		extent.setSystemInfo("Selenium Version", "4.2.1");
		extent.setSystemInfo("JUnit Version", "5.8.2");
		return extent;
	}
}
