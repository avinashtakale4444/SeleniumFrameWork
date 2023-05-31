package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

public class ProductInfoPageTest extends BaseTest {
    @BeforeClass
    public void searchTestSetup() {
        accountPage = loginPage.doLogin(properties.getProperty("username"), properties.getProperty("password"));
    }

    @Test
    public void productInfo() {
        resultPage = accountPage.doSearch("Samsung");
        productInfoPage = resultPage.selectProduct("Samsung SyncMaster 941BW");
        Map<String, String> metadataInfo = productInfoPage.getMetadataInfo();
        softAssert.assertEquals(metadataInfo.get("Availability"), "2-3 Days");
        softAssert.assertEquals(metadataInfo.get("Product Code"), "Product 6");
        softAssert.assertEquals(metadataInfo.get("productHeaderName"), "Samsung SyncMaster 941BW");
        softAssert.assertEquals(metadataInfo.get("productPriceExcludingTax"), "$200.00");
        softAssert.assertEquals(metadataInfo.get("productPrice"), "$242.00");
        softAssert.assertAll();
    }
}
