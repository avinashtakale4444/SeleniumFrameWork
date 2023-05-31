package com.qa.opencart.base;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.ResultPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import java.util.Properties;

public class BaseTest {
    protected LoginPage loginPage;
    protected AccountPage accountPage;
    protected ResultPage resultPage;
    protected ProductInfoPage productInfoPage;
    protected SoftAssert softAssert;
    protected DriverFactory df;
    protected Properties properties;
    WebDriver driver;

    @Parameters({"browser"})
    @BeforeTest
    public void setup(String browserName) {
        df = new DriverFactory();
        properties = df.readProperties();
        if (browserName != null) {
            properties.setProperty("browser", browserName);
        }
        driver = df.initDriver(properties);
        loginPage = new LoginPage(driver);
        softAssert = new SoftAssert();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
