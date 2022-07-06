package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends BasePage {

    public RegisterPage(WebDriver driver, String URL) {
        super(driver, URL);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "input-firstname")
    public WebElement nameInput;

    @FindBy(id = "input-lastname")
    public WebElement lastNameInput;

    @FindBy(id = "input-email")
    public WebElement emailInput;

    @FindBy(id = "input-telephone")
    public WebElement telephoneInput;

    @FindBy(id = "input-password")
    public WebElement passwordInput;

    @FindBy(id = "input-confirm")
    public WebElement confirmInput;

    @FindBy(xpath = "//*[@id=\"content\"]/form/fieldset[3]/div/div/label[2]/input")
    public WebElement buttonNo;

    @FindBy(xpath = "//*[@id=\"content\"]/form/div/div/input[1]")
    public WebElement acceptFlag;

    @FindBy(xpath = "//*[@id=\"content\"]/form/div/div/input[2]")
    public WebElement buttonContinue;

    @FindBy(xpath = "//*[@id=\"content\"]/p[1]")
    public WebElement success;

    public void fillFields(String name, String lastName, String email, String telephone, String password,
            String confirm) {
        nameInput.clear();
        lastNameInput.clear();
        emailInput.clear();
        telephoneInput.clear();
        passwordInput.clear();
        confirmInput.clear();
        nameInput.sendKeys(name);
        lastNameInput.sendKeys(lastName);
        emailInput.sendKeys(email);
        telephoneInput.sendKeys(telephone);
        passwordInput.sendKeys(password);
        confirmInput.sendKeys(confirm);
    }

    public void clickNoAndFlagAndContinue() {
        buttonNo.click();
        acceptFlag.click();
        buttonContinue.click();
    }

    public String success() {
        return success.getText();
    }
}