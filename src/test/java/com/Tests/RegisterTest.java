package com.Tests;

import com.Base.BaseTest;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RegisterTest extends BaseTest {
	private static final String registerProcessingMessage = "You are now logged in";

	@Test
	@Order(0)
	@Tag("Smoke")
	@DisplayName("Proceso de registro")
	public void registerProcessing() {
		homePage.clickRegister();

		registerPage.fillFields("Jackson", "Atkins", "2184 Nonummy",
				"Rosario", "Santa Fe", "313298", "12341234",
				"1111", USERNAME , PASSWORD,PASSWORD);
		registerPage.clickRegister();

		Assertions.assertTrue(registerPage.success().contains(registerProcessingMessage));

	}

	@AfterAll
	public static void end() {
		homePage.quit();
	}
}