package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.dataProviders.ProductDataProvider;
import com.qa.opencart.pages.ProductInfoPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SearchTestPage extends BaseTest {
    @BeforeClass
    public void searchTestSetup() {
        accountPage = loginPage.doLogin(properties.getProperty("username"), properties.getProperty("password"));
    }

    @Test(dataProvider = "productDataWithSearchKey",dataProviderClass = ProductDataProvider.class)
    public void searchPageTitle(String key) {
        resultPage = accountPage.doSearch(key);
        String resultPageTitle = resultPage.getResultPageTitle(key);
        System.out.println(resultPageTitle);
    }

    @Test(dataProvider = "productDataWithSearchKey", dataProviderClass = ProductDataProvider.class)
    public void searchPageResultCountTest(String searchKey) {
        System.out.println("searchKey:" + searchKey);
        resultPage = accountPage.doSearch(searchKey);
        int productResultCount = resultPage.getProductResultCount();
        System.out.println(productResultCount);
        Assert.assertTrue(productResultCount > 0);
    }

    //https://github.com/naveenanimation20/ExcelUtil/blob/master/src/main/java/com/navlabs/excel/reader/NALExcelXLSReader.java
    @Test(dataProvider = "productDataWithName", dataProviderClass = ProductDataProvider.class)
    public void selectProductTest(String searchKey, String productName) {
        resultPage = accountPage.doSearch(searchKey);
        ProductInfoPage productInfoPage = resultPage.selectProduct(productName);
        Assert.assertEquals(productName, productInfoPage.getHeaderName());
    }

    @Test
    public void productImageCountTest() {
        resultPage = accountPage.doSearch("Samsung");
        productInfoPage = resultPage.selectProduct("Samsung SyncMaster 941BW");
        Assert.assertEquals(1, productInfoPage.getProductImageCount());
    }
}
