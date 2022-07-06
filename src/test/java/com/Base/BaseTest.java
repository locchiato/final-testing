package com.Base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.extentreports.ExtentFactory;

public class BaseTest {

	protected static ExtentSparkReporter spark;
	protected static ExtentReports extent;
	protected ExtentTest test;
	
	private static boolean started = false;
	
	
	@BeforeAll
	public static void setUpBaseTest()
	{
		// El if es para asegurarme que el extent/spark se inicialicen una vez sola para todos los tests (simula un @BeforeSuite)
		if (!started)
		{
			spark = new ExtentSparkReporter("target/SparkReport.html");
			extent = ExtentFactory.getInstance();
			extent.attachReporter(spark);
			started = true;
		}
	}
	
	@AfterAll
	public static void tearDownBaseTest()
	{
		extent.flush();
	}
}
