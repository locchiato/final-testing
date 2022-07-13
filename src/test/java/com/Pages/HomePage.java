package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver, String URL) {
        super(driver, URL);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"loginPanel\"]/form[1]/div[1]/input[1]")
    public WebElement usernameInput;

    @FindBy(xpath = "//*[@id=\"loginPanel\"]/form[1]/div[2]/input[1]")
    public WebElement passwordInput;

    @FindBy(xpath = "//*[@id=\"loginPanel\"]/form[1]/div[3]/input[1]")
    public WebElement buttonLogin;

    @FindBy(xpath = "//*[@id=\"loginPanel\"]/p[2]/a[1]")
    public WebElement buttonRegister;


    public void clickRegister() {
        buttonRegister.click();
    }

    public void clickLogin() {
        buttonLogin.click();
    }

    public void quit() {
        getDriver().quit();
    }

    public void loginUser(String username, String password) {
        waitForElementToAppear(By.xpath("//*[@id=\"loginPanel\"]/form[1]/div[1]/input[1]"));
        setInputField(usernameInput,username);
        waitForElementToAppear(By.xpath("//*[@id=\"loginPanel\"]/form[1]/div[2]/input[1]"));
        setInputField(passwordInput,password);
        //+ Keys.ENTER);
        clickLogin();
    }
}