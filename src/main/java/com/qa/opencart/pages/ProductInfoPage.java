package com.qa.opencart.pages;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductInfoPage {
    private WebDriver driver;
    private ElementUtil elementUtil;
    private By productImage = By.cssSelector("a.thumbnail img");
    private By metadataInfo = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");////div[@id='content']//ul[@class='list-unstyled'][1]
    private By metadataPriceInfo = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
    private By productHeaderName = By.cssSelector("div#content h1");
    protected Map<String, String> productInfo;

    public ProductInfoPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(this.driver);
    }


    public int getProductImageCount() {
        return elementUtil.waitForElementsVisible(productImage, AppConstants.MEDIUM_DEFAULT_WAIT).size();
    }

    public String getHeaderName() {
        return elementUtil.getElementText(productHeaderName);
    }

    public Map<String, String> getMetadataInfo() {
        productInfo = new HashMap<>();
        getProductMetadata();
        getProductPrice();
        String value = elementUtil.waitForElementVisible(productHeaderName, AppConstants.MEDIUM_DEFAULT_WAIT).getText();
        productInfo.put("productHeaderName", value);
        return productInfo;
    }

    public void getProductMetadata() {
        List<WebElement> elements = elementUtil.getElements(metadataInfo);
        for (WebElement e : elements) {
            String text = e.getText();
            String[] split = text.split(":");
            productInfo.put(split[0].trim(), split[1].trim());
        }
    }

    public void getProductPrice() {
        List<WebElement> elements = elementUtil.getElements(metadataPriceInfo);
        String price = elements.get(0).getText();
        String exTaxPrice = elements.get(1).getText();
        String priceExTax = exTaxPrice.split(":")[1].trim();
        productInfo.put("productPrice", price);
        productInfo.put("productPriceExcludingTax", priceExTax);
    }
}
