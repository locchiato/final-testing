package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

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

    @FindBy(xpath = "//*[@id=\"accountTable\"]/tbody/tr[1]/td[1]/a")
    public WebElement accountNumber;
    @FindBy(xpath = "//*[@id=\"rightPanel\"]/div/div[1]/h1")
    public WebElement titleAccount;
    @FindBy(id = "month")
    public WebElement to;
    @FindBy(id = "transactionType")
    public WebElement type;
    protected static final String goBtn = "//*[@id=\"rightPanel\"]/div/div[2]/form/table/tbody/tr[3]/td[2]/input";


    public String goToAccountDetail() {
        waitForElementToAppear(By.xpath("//*[@id=\"accountTable\"]/tbody/tr[1]/td[1]/a"));
        accountNumber.click();

        waitForElementToAppear(By.xpath("//*[@id=\"rightPanel\"]/div/div[1]/h1"));
        return titleAccount.getText();
    }

    public void checkActivity(){

        waitForElementToAppear(By.id("month"));
        Select selectTo = new Select(to);

        waitForElementToAppear(By.id("transactionType"));
        Select selectType = new Select(type);

        waitUntilSelectOptionsPopulated(selectTo);
        waitUntilSelectOptionsPopulated(selectType);

        selectTo.selectByIndex(0);
        selectType.selectByIndex(0);

        waitForElementToBeClickable(By.xpath(goBtn));
        WebElement btn_go = getWebElement(By.xpath(goBtn));
        btn_go.click();
    }


    public void clickOpenAccount() {
        waitForElementToBeClickable(By.xpath("//*[@id=\"leftPanel\"]/ul[1]/li[1]/a[1]"));
        openAccountLink.click();
    }

    public void clickTransferFunds() {
        waitForElementToBeClickable(By.xpath("//*[@id=\"leftPanel\"]/ul[1]/li[3]/a[1]"));
        transferFundsLink.click();
    }

    public String message() {
        return message.getText();
    }
}
