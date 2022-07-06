package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver, String URL) {
        super(driver, URL);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"top-links\"]/ul/li[2]/a/span[1]")
    public WebElement buttonMyAccount;

    @FindBy(xpath = "//*[@id=\"top-links\"]/ul/li[2]/ul/li[1]/a")
    public WebElement buttonRegister;

    @FindBy(xpath = "//*[@id=\"search\"]/input")
    public WebElement searchField;

    @FindBy(xpath = "//*[@id=\"search\"]/span/button")
    public WebElement buttonSearch;

    By buttonCart = By.xpath("//*[@id=\"content\"]/div[3]/div/div/div[2]/div[2]/button[1]");

    @FindBy(xpath = "//*[@id=\"product-search\"]/div[1]")
    public WebElement success;

    public void clickMyAccountAndRegister() {
        buttonMyAccount.click();
        buttonRegister.click();
    }

    public void addToCart() {
        waitForElementToAppear(buttonCart);
        driver.findElement(buttonCart).click();
    }

    public void inputSearch(String product) {
        searchField.clear();
        searchField.sendKeys(product);
    }

    public void clickSearch() {
        buttonSearch.click();
    }

    public boolean success() {
        return success.isDisplayed();
    }

    public void quit() {
        driver.quit();
    }
}