package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AccountPageTest extends BaseTest {
    @BeforeClass
    public void accPageSetup(){
        accountPage=loginPage.doLogin(properties.getProperty("username"), properties.getProperty("password"));
    }

    @Test
    public void accPageTitleTest() {
        String accountPageTitle = accountPage.getAccountPageTitle();
        Assert.assertEquals(accountPageTitle, AppConstants.ACCOUNT_PAGE_TITLE);
    }

    @Test
    public void isLogoutLinkExistTest() {
        Assert.assertTrue(accountPage.isLogoutLinkExist());
    }
    @Test
    public void isAccountLinkExistTest() {
        Assert.assertTrue(accountPage.isMyAccountLinkExist());
    }
}
