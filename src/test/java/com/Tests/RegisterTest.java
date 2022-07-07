package com.Tests;

import com.Base.BaseTest;
import com.Pages.HomePage;
import com.Pages.OpenAccountPage;
import com.Pages.RegisterPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;

class RegisterTest extends BaseTest {

	private static HomePage homePage;
	private static RegisterPage registerPage;

	@BeforeAll
	static void initTest() {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		homePage = new HomePage(driver, "https://parabank.parasoft.com/parabank/index.htm");
		registerPage = new RegisterPage(driver, "https://parabank.parasoft.com/parabank/register.htm");
	}

	@Test
	@Order(1)
	@DisplayName("Proceso de registro")
	public void registerProcessing() {
		homePage.clickRegister();

		// todo lo que necesito:
		// firstName, lastName, address, city, state,
		// zipCode, phone, ssn, username, password, repass

		registerPage.fillFields("Jackson", "Atkins", "2184 Nonummy",
				"Rosario", "Santa Fe", "313298", "12341234",
				"1111", "ruser1" , "123ari","123ari");
		registerPage.clickRegister();

		Assertions.assertTrue(registerPage.success().contains("You are now logged in."));

	}

	@AfterAll
	public static void end() {
		homePage.quit();
	}
}