package com.qa.opencart.dataProviders;

import org.testng.annotations.DataProvider;

public class ProductDataProvider {

    @DataProvider(name = "productDataWithName")
    public Object[][] getProductData() {
        return new Object[][]{
                {"Macbook","MacBook Pro"},
                {"iMac","iMac"},
                {"Samsung","Samsung SyncMaster 941BW"}
        };
    }
    @DataProvider(name = "productDataWithSearchKey")
    public Object[][] getProductSearchKeyData() {
        return new Object[][]{
                {"Macbook"},
                {"iMac"},
                {"Samsung"}
        };
    }
}
