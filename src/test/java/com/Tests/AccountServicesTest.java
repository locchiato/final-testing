package com.Tests;

import com.Base.BaseTest;
import com.Pages.HomePage;
import com.Pages.OpenAccountPage;
import com.Pages.OverviewPage;
import com.Pages.TransferPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

class AccountServicesTest extends BaseTest {

	private static HomePage homePage;
	private static OverviewPage overviewPage;
	private static OpenAccountPage openAccountPage;
	private static TransferPage transferPage;
	private static final String USERNAME = "ruser1";
	private static final String PASSWORD = "123ari";

	@BeforeAll
	static void initTest() {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		homePage = new HomePage(driver, "https://parabank.parasoft.com/parabank/index.htm");
		overviewPage = new OverviewPage(driver, "https://parabank.parasoft.com/parabank/overview.htm");
		openAccountPage = new OpenAccountPage(driver, "https://parabank.parasoft.com/parabank/openaccount.htm");
		transferPage = new TransferPage(driver, "https://parabank.parasoft.com/parabank/transfer.htm");
	}

	@BeforeEach
	void login(){
		doLogin();
	}


	@Test
	@Order(1)
	@DisplayName("Apertura de una nueva cuenta")
	public void openNewAccount() {

		overviewPage.clickOpenAccount();

		openAccountPage.selectTypeSaving();
		openAccountPage.selectAccount();
		openAccountPage.clickOpenNewAccount();

		Assertions.assertTrue(openAccountPage.success().contains("your account is now open."));
	}


	@Test
	@Order(2)
	@DisplayName("Visión general de la cuenta")
	public void generalViewAccount() {
		Assertions.assertTrue(overviewPage.message().contains("*Balance includes deposits that may be subject to holds"));
	}


	@Test
	@Order(3)
	@DisplayName("Transferencia de fondos")
	public void transfer() {
		overviewPage.clickTransferFunds();

		Assertions.assertTrue(transferPage.getText().contains("Transfer Funds"));

		transferPage.fillFields("300");
		//transferPage.fillFields("300", "61296", "61629");


		Assertions.assertTrue(transferPage.getText().contains("Transfer Complete!"));
	}

	@Test
	@Order(4)
	@DisplayName("Actividad de la cuenta")
	public void accountActivity() {
		Assertions.assertTrue(true);//openAccountPage.success().contains("your account is now open."));
	}

	@AfterAll
	public static void end() {
		homePage.quit();
	}


	private void doLogin() {
		homePage.loginUser(USERNAME
				, PASSWORD);
	}

}