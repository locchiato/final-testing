package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OverviewPage extends BasePage {

    public OverviewPage(WebDriver driver, String URL) {
        super(driver, URL);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"accountTable\"]/tfoot[1]/tr[1]/td[1]")
    public WebElement message;
    @FindBy(xpath = "//*[@id=\"leftPanel\"]/ul[1]/li[1]/a[1]")
    public WebElement openAccountLink;
    @FindBy(xpath = "//*[@id=\"leftPanel\"]/ul[1]/li[3]/a[1]")
    public WebElement transferFundsLink;


    public void clickOpenAccount() {
        openAccountLink.click();
    }

    public void clickTransferFunds() {
        transferFundsLink.click();
    }

    public String message() {
        return message.getText();
    }
}
