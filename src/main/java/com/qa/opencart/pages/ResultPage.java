package com.qa.opencart.pages;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResultPage {
    private WebDriver driver;
    private ElementUtil elementUtil;
    private By productResults = By.cssSelector("div.product-layout.product-grid");

    public ResultPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(this.driver);
    }

    public String getResultPageTitle(String key) {
        return elementUtil.waitForTitleFractionAndCapture(key, AppConstants.MEDIUM_DEFAULT_WAIT);
    }

    public int getProductResultCount() {
        return elementUtil.getElementsCount(productResults);
    }

    public ProductInfoPage selectProduct(String productName) {
        By productSelect = By.linkText(productName);
        elementUtil.getElement(productSelect).click();
        return new ProductInfoPage(driver);
    }
}
