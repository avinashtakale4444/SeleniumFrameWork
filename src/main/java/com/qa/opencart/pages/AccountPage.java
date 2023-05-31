package com.qa.opencart.pages;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AccountPage {
    private WebDriver driver;
    private ElementUtil elementUtil;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(this.driver);
    }

    private By logoutBtn = By.linkText("Logout");
    private By myAccountBtn = By.linkText("My Account");
    private By search = By.name("search");
    private By searchBtn = By.cssSelector("div#search button");
    private By accHeaders = By.cssSelector("div#content h2");

    public boolean isLogoutLinkExist() {
        return elementUtil.isElementDisplayed(logoutBtn);
    }

    public boolean isMyAccountLinkExist() {
        return elementUtil.isElementDisplayed(myAccountBtn);
    }

    public String getAccountPageTitle() {
        return elementUtil.waitForFullTitleIsAndCapture("My Account", AppConstants.MEDIUM_DEFAULT_WAIT);
    }

    public List<String> getAccountPageHeaderList() {
        List<WebElement> accHeader = elementUtil.getElements(accHeaders);
        List<String> accHeaderText = new ArrayList<>();
        for (WebElement e : accHeader) {
            String text = e.getText();
            accHeaderText.add(text);
        }
        return accHeaderText;
    }

    public ResultPage doSearch(String searchString) {
        elementUtil.waitForElementVisible(search, AppConstants.MEDIUM_DEFAULT_WAIT);
        elementUtil.doSendKeys(search, searchString);
        elementUtil.doClick(searchBtn, AppConstants.MEDIUM_DEFAULT_WAIT);
        return new ResultPage(driver);
    }
}
