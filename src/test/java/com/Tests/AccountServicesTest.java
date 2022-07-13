package com.Tests;

import com.Base.BaseTest;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountServicesTest extends BaseTest {

	private String openNewAccountMessage = "Congratulations, your account is now open";
	private String generalViewAccountMessage = "Balance includes deposits that may be subject to holds";
	private String transferFundsFirstMessage = "Transfer Funds";
	private String transferFundsSecondMessage = "Transfer Complete!";
	private String accountActivityMessage = "Account Details";

	@BeforeEach
	void login(){
		homePage.loginUser(USERNAME, PASSWORD);
	}


	@Test
	@Order(1)
	@Tag("Smoke")
	@DisplayName("Apertura de una nueva cuenta")
	public void openNewAccount() {

		overviewPage.clickOpenAccount();

		openAccountPage.selectTypeSaving();
		openAccountPage.selectAccount();
		openAccountPage.clickOpenNewAccount();

		Assertions.assertTrue(openAccountPage.success().contains(openNewAccountMessage));
	}


	@Test
	@Order(2)
	@Tag("Regression")
	@DisplayName("Visión general de la cuenta")
	public void generalViewAccount() {
		Assertions.assertTrue(overviewPage.message().contains(generalViewAccountMessage));
	}


	@Test
	@Order(3)
	@Tag("Smoke")
	@DisplayName("Transferencia de fondos")
	public void transferFunds() {
		overviewPage.clickTransferFunds();

		Assertions.assertTrue(transferPage.getText().contains(transferFundsFirstMessage));

		transferPage.fillFields("200");

		Assertions.assertTrue(transferPage.getText().contains(transferFundsSecondMessage));
	}

	@Test
	@Order(4)
	@Tag("Smoke")
	@DisplayName("Actividad de la cuenta")
	public void accountActivity() {

		Assertions.assertTrue(overviewPage.goToAccountDetail().contains(accountActivityMessage));
		overviewPage.checkActivity();

	}

	@AfterAll
	public static void end() {
		homePage.quit();
	}


}