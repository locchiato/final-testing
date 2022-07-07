package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends BasePage {

    public RegisterPage(WebDriver driver, String URL) {
        super(driver, URL);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "customer.firstName")
    public WebElement nameInput;

    @FindBy(id = "customer.lastName")
    public WebElement lastNameInput;

    @FindBy(id = "customer.address.street")
    public WebElement addressInput;

    @FindBy(id = "customer.address.city")
    public WebElement cityInput;

    @FindBy(id = "customer.address.state")
    public WebElement stateInput;

    @FindBy(id = "customer.address.zipCode")
    public WebElement zipCodeInput;

    @FindBy(id = "customer.phoneNumber")
    public WebElement phoneInput;

    @FindBy(id = "customer.ssn")
    public WebElement ssnInput;

    @FindBy(id = "customer.username")
    public WebElement usernameInput;

    @FindBy(id = "customer.password")
    public WebElement passwordInput;

    @FindBy(id = "repeatedPassword")
    public WebElement confirmInput;

    @FindBy(xpath = "//input[@value='Register']")
    public WebElement registrar;

    @FindBy(xpath = "//*[@id=\"rightPanel\"]/p[1]")
    public WebElement success;


    public void fillFields(String firstName, String lastName, String address, String city,
                           String state, String zipCode, String phone, String ssn,
                           String username, String password, String confirm) {

        setInputField(nameInput, firstName);
        setInputField(lastNameInput,lastName);
        setInputField(addressInput,address);
        setInputField(cityInput,city);
        setInputField(stateInput,state);
        setInputField(zipCodeInput,zipCode);
        setInputField(phoneInput,phone);
        setInputField(ssnInput,ssn);
        setInputField(usernameInput,username);
        setInputField(passwordInput,password);
        setInputField(confirmInput,confirm);
    }

    public void clickRegister() {
        registrar.click();
    }

    public String success() {
        return success.getText();
    }
}