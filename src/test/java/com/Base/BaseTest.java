package com.Base;

import com.Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.extentreports.ExtentFactory;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

	protected static final String USERNAME = "finaluserAUTO";
	protected static final String PASSWORD = "finalpass";

	protected static final String baseUrl = "https://parabank.parasoft.com/parabank";
	protected static HomePage homePage;
	protected static RegisterPage registerPage;
	protected static OverviewPage overviewPage;
	protected static OpenAccountPage openAccountPage;
	protected static TransferPage transferPage;
	protected static ExtentSparkReporter spark;
	protected static ExtentReports extent;
	private static boolean started = false;


	@BeforeAll
	static void initTest() {

		if (!started)
		{
			spark = new ExtentSparkReporter("target/SparkReport.html");
			extent = ExtentFactory.getInstance();
			extent.attachReporter(spark);
			started = true;
		}

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		homePage = new HomePage(driver, baseUrl + "/index.htm");
		registerPage = new RegisterPage(driver, baseUrl + "/register.htm");
		overviewPage = new OverviewPage(driver, baseUrl + "/overview.htm");
		openAccountPage = new OpenAccountPage(driver, baseUrl + "/openaccount.htm");
		transferPage = new TransferPage(driver, baseUrl + "/transfer.htm");
	}

	@AfterAll
	public static void tearDownBaseTest()
	{
		extent.flush();
	}

}
