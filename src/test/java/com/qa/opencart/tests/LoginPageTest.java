package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Epic 100: Login page design")
@Severity(SeverityLevel.MINOR)
@Story("US 101 : Design tc's for login page")
public class LoginPageTest extends BaseTest {
    @Test
    public void loginPageTitleTest() {
        String actual = loginPage.getLoginPageTitle();
        Assert.assertEquals(actual, AppConstants.LOGIN_PAGE_TITLE_VALUE);
    }
    @Test
    public void loginPageUrlTest() {
        String actual = loginPage.getLoginPageURL();
        Assert.assertTrue(actual.contains("account/login"));
    }
    @Test
    public void forgotPasswordLinkExistTest() {
        Assert.assertTrue(loginPage.isForgotPwdLinkExist());
    }
    @Test
    public void loginTest() {
        loginPage.doLogin(properties.getProperty("username"), properties.getProperty("password"));
    }
}
