package com.Base;

import com.Pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class BasePage {
    private static final int TIMEOUT = 5; // * Tiempo final de espera (segundos)
    private static final int SLEEP = 3000; // * Tiempo de espera entre acciones (milisegundos)
    private WebDriver driver; // * Driver de Selenium del navegador
    private final WebDriverWait wait; // * Objeto WebDriverWait para esperar por elementos
    private String URL; // * URL de la pagina
    private static final String logoImgXPath = "//a[@class='logo-img']";

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

    protected void waitForElementToBeClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public String getTitlePage() {
        return driver.getTitle();
    }

    protected void setInputField(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public void openApp()
    {
        driver.get(URL);
    }

    public WebElement getWebElement(By locator)
    {
        WebElement elem = null;
        try
        {
            elem = driver.findElement(locator);
        }
        catch(Exception e)
        {
            System.out.println("There is no element: " + locator);
            System.out.println("Exception message: " + e);
        }

        return elem;
    }

    protected void waitUntilSelectOptionsPopulated(final Select select) {
        new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofMillis(10))
                .until(d -> (select.getOptions().size() > 0 && select.getFirstSelectedOption().getText() != "undefined"));
    }

    public HomePage goToHomePageHeader()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(logoImgXPath)));
        WebElement logoImgButton = getWebElement(By.xpath(logoImgXPath));
        wait.until(ExpectedConditions.elementToBeClickable(logoImgButton));
        logoImgButton.click();
        return new HomePage(driver, "https://parabank.parasoft.com/parabank/index.htm");
    }

    public static boolean hasQuit(WebDriver driver)
    {
        return ((RemoteWebDriver) driver).getSessionId() == null;
    }
}