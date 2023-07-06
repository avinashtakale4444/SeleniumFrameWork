package com.qa.opencart.factory;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.Properties;

public class DriverFactory {
    private WebDriver driver;
    Properties prop;
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public WebDriver initDriver(Properties pro) {
        String browserName = pro.getProperty("browser");
        switch (browserName.toLowerCase().trim()) {
            case "chrome":
                // driver = new ChromeDriver();
                if (Boolean.parseBoolean(prop.getProperty("remote"))) {
                    //init_remoteDriver("chrome");
                } else {
                    tlDriver.set(new ChromeDriver());
                }
                break;
            case "firefox":
                //  driver = new FirefoxDriver();
                tlDriver.set(new FirefoxDriver());
                break;
            case "edge":
                //  driver = new EdgeDriver();
                tlDriver.set(new EdgeDriver());
                break;
            default:
                System.out.println("plz pass the right browser :" + browserName);
        }
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        getDriver().get(pro.getProperty("url"));

        return getDriver();
    }

    private void init_remoteDriver(String chrome) throws MalformedURLException {

    }

    public synchronized static WebDriver getDriver() {
        return tlDriver.get();
    }

    public Properties readProperties() {
        FileInputStream ip = null;
        try {
            ip = new FileInputStream("src/main/resources/config/config.properties");
            prop = new Properties();
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    /**
     * take screenshot
     */
    public static String getScreenshot(String tcName) {
        byte[] srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
        String base64Path = Base64.getEncoder().encodeToString(srcFile);

        /*File src = new File(srcFile);

        String path = System.getProperty("user.dir") + "/screenshots/" + tcName + ".png";
        File destination = new File(path);
        try {

            FileUtils.copyFile(src, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return base64Path;

    }
}
