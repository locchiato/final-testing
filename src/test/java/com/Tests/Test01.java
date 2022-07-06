package com.Tests;

import com.Pages.HomePage;
import com.Pages.RegisterPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;

class Test01 {

	private static HomePage homePage;
	private static RegisterPage registerPage;

	@BeforeAll
	static void initTest() {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		homePage = new HomePage(driver, "https://parabank.parasoft.com/parabank/index.htm");
		registerPage = new RegisterPage(driver, "https://opencart.abstracta.us/index.php?route=common/home");
	}

	@Test
	@Order(1)
	@DisplayName("Registro")
	public void registro() {
		homePage.clickMyAccountAndRegister();
		registerPage.fillFields("Sergio", "Pace", "prueba@hola.com", "3233274454", "password", "password");
		registerPage.clickNoAndFlagAndContinue();
		Assertions.assertTrue(registerPage.success().contains("Congratulations! Your new account"));
		homePage.inputSearch("Iphone");
		homePage.clickSearch();
		homePage.addToCart();
		Assertions.assertTrue(homePage.success());
	}

	@AfterAll
	public static void end() {
		homePage.quit();
	}
}