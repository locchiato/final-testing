package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class OpenAccountPage extends BasePage {

    public OpenAccountPage(WebDriver driver, String URL) {
        super(driver, URL);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "type")
    public WebElement typeAccount;
    @FindBy(id = "fromAccountId")
    public WebElement fromAccount;

    @FindBy(xpath = "//form[1]/div[1]/input[1]")
    public WebElement openNewAccountInput;

    public void selectTypeSaving() {
        waitForElementToAppear(By.id("type"));
        Select selectType = new Select(typeAccount);
        waitUntilSelectOptionsPopulated(selectType);
        selectType.selectByIndex(1);
    }
    public void selectAccount() {
        waitForElementToAppear(By.id("fromAccountId"));
        Select selectAccount = new Select(fromAccount);
        waitUntilSelectOptionsPopulated(selectAccount);
        selectAccount.selectByIndex(1);
    }

    public void clickOpenNewAccount() {
        openNewAccountInput.click();
    }

    public String success() {
        return getDriver().findElement(By.xpath("//*[@id=\"rightPanel\"]/div[1]/div[1]/p[1]")).getText();
    }

}