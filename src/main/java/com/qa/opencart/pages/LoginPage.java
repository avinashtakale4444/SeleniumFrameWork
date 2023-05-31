package com.qa.opencart.pages;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class LoginPage {
    private WebDriver driver;
    private ElementUtil elementUtil;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(this.driver);
    }

    private By emailId = By.id("input-email");
    private By password = By.id("input-password");
    private By loginBtn = By.xpath("//input[@value='Login']");
    private By forgotPwdLink = By.linkText("Forgotten Password");
    private By footerLinks = By.xpath("//footer//a");

    public String getLoginPageTitle() {
        return elementUtil.waitForFullTitleIsAndCapture(AppConstants.LOGIN_PAGE_TITLE_VALUE, AppConstants.MEDIUM_DEFAULT_WAIT);
    }

    public String getLoginPageURL() {
        return driver.getCurrentUrl();
    }

    public boolean isForgotPwdLinkExist() {
        return elementUtil.isElementDisplayed(forgotPwdLink);
    }

    public List<String> getFooterLinksList() {
        List<WebElement> footerLinksList = elementUtil.getElements(footerLinks);
        List<String> footerTextList = new ArrayList<>();
        for (WebElement e : footerLinksList) {
            String text = e.getText();
            footerTextList.add(text);
        }
        return footerTextList;
    }

    public AccountPage doLogin(String userName, String pwd) {
        elementUtil.login(emailId,userName,password,pwd,loginBtn);
        return new AccountPage(driver);
    }
}
