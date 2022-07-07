package com.Pages;

import com.Base.BasePage;
import org.bouncycastle.asn1.cms.KEKIdentifier;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.Collection;

public class TransferPage extends BasePage {

    public TransferPage(WebDriver driver, String URL) {
        super(driver, URL);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"rightPanel\"]/div[1]/div[1]/h1[1]")
    public WebElement title;

    @FindBy(id = "amount")
    public WebElement amount;

    @FindBy(id = "fromAccountId")
    public WebElement fromAccount;

    @FindBy(id = "toAccountId")
    public WebElement toAccount;

    public void fillFields(String amount){
        setInputField(this.amount, amount);

        waitForElementToAppear(By.id("fromAccountId"));
        waitForElementToAppear(By.id("toAccountId"));
        Select selectFrom = new Select(this.fromAccount);
        Select selectTo = new Select(this.toAccount);

        waitUntilSelectOptionsPopulated(selectFrom);
        waitUntilSelectOptionsPopulated(selectTo);
        selectFrom.selectByIndex(1);
        selectTo.selectByIndex(2);


    }
    public String getText() {
        return title.getText();
    }


}
