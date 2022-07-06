package com.Base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    private static final int TIMEOUT = 5; // * Tiempo final de espera (segundos)
    private static final int SLEEP = 500; // * Tiempo de espera entre acciones (milisegundos)
    protected WebDriver driver; // * Driver de Selenium del navegador
    private final WebDriverWait wait; // * Objeto WebDriverWait para esperar por elementos
    private String URL; // * URL de la pagina

    public BasePage(WebDriver driver, String URL) {
        this.driver = driver;
        this.URL = URL;
        driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(200));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT), Duration.ofMillis(SLEEP));
    }

    public WebDriver getDriver() {
        return driver;
    }

    protected void waitForElementToAppear(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public String getTitlePage() {
        return driver.getTitle();
    }

    protected void setInputField(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }
}