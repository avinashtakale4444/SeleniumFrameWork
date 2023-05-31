package com.qa.opencart.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ElementUtil {
    private WebDriver driver;

    public ElementUtil(WebDriver driver) {
        this.driver = driver;
    }

    public String getText(By locator) {
        return getElement(locator).getText();
    }

    public void doSendKeys(By locator, String value) {
        if (value == null) {
            System.out.println("null values are not allowed");
        }
        WebElement ele = getElement(locator);
        ele.clear();
        ele.sendKeys(value);
    }

    public int getElementsCount(By locator) {
        return getElements(locator).size();
    }

    public void doClick(By locator, int timeOut) {
        checkElementClickable(locator, timeOut).click();
    }

    public void search(By locator, String ele) {
        List<String> elementTextList = getElementTextList(locator);
        if (elementTextList.size() > 1) {
            for (int i = 0; i < elementTextList.size(); i++) {
                if (elementTextList.get(i).equalsIgnoreCase(ele)) {
                    System.out.println("Element found");
                    break;
                }
            }
        } else {
            System.out.println("Suggestions are blank");
        }
    }

    // ############### wait methods##################
    public WebElement checkElementClickable(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForElementVisible(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementPresence(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public String waitForFullTitleIsAndCapture(String titleValue, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        if (wait.until(ExpectedConditions.titleIs(titleValue))) {
            String title = driver.getTitle();
            return title;
        } else
            return null;
    }

    public String waitForTitleFractionAndCapture(String titleFractionValue, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        if (wait.until(ExpectedConditions.titleContains(titleFractionValue))) {
            String title = driver.getTitle();
            return title;
        } else
            return null;
    }

    public List<WebElement> waitForElementsPresence(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public List<WebElement> waitForElementsVisible(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public String getElementText(By locator) {
        return getElement(locator).getText();
    }

    public List<String> getElementTextList(By locator) {
        List<WebElement> locatorsList = getElements(locator);
        List<String> eleTxts = new ArrayList<>();
        if (locatorsList.size() > 0) {
            for (int i = 0; i < locatorsList.size(); i++) {
                eleTxts.add(locatorsList.get(i).getText());
            }
        }
        return eleTxts;
    }

    public WebElement getElement(By locator) {
        WebElement element = null;
        try {
            element = driver.findElement(locator);
        } catch (NoSuchElementException e) {
            element = waitForElementVisible(locator, 10);
        }
        return element;
    }

    public List<WebElement> getElements(By locator) {
        List<WebElement> elements = null;
        try {
            elements = driver.findElements(locator);
        } catch (NoSuchElementException e) {
            elements = waitForElementsPresence(locator, 10);
        }
        return elements;
    }

    public boolean isElementDisplayed(By locator) {
        List<WebElement> elements = getElements(locator);
        if (elements.size() > 0) {
            System.out.println(locator + " element is present on the page");
            return true;
        } else
            return false;
    }

    public void enterUserName(By locator, String userName) {
        doClearTextBox(locator);
        getElement(locator).sendKeys(userName);
    }

    public void enterPassword(By locator, String password) {
        doClearTextBox(locator);
        getElement(locator).sendKeys(password);
    }

    public void submit(By locator) {
        getElement(locator).click();
    }

    public void login(By locatorUserName, String userName, By locatorPassword, String password, By locatorSubmit) {
        enterUserName(locatorUserName, userName);
        enterPassword(locatorPassword, password);
        submit(locatorSubmit);
    }

    public void doClearTextBox(By locator) {
        getElement(locator).clear();
    }

    public void scrollDown(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    //#################### select class methods ####################//
    public void selectByVisibleText(By locator, String value) {
        Select select = new Select(getElement(locator));
        select.selectByVisibleText(value);
    }

    public void selectByIndex(By locator, int index) {
        Select select = new Select(getElement(locator));
        select.selectByIndex(index);
    }

    public void selectByValue(By locator, String value) {
        Select select = new Select(getElement(locator));
        select.selectByValue(value);
    }

    public void getAllOptions(By locator, String value) {
        Select select = new Select(getElement(locator));
        List<WebElement> options = select.getOptions();
        for (WebElement e : options) {
            if (e.getText().equalsIgnoreCase(value)) {
                e.click();
                break;
            }
        }
    }

    public void selectChoices(By locator, String... value) {
        List<WebElement> elements = getElements(locator);
        if (!value[0].equalsIgnoreCase("all")) {
            for (WebElement e : elements) {
                String s = e.getText();
                for (int i = 0; i < value.length; i++) {
                    if (s.equals(value[i])) {
                        e.click();
                        break;
                    }
                }
            }
        } else {
            for (WebElement e : elements) {
                String a = e.getText();
                if (!a.equalsIgnoreCase("−")) {
                    e.click();
                }
            }
        }
    }

    public void selectMultiple(By locator) {
        if (getElement(locator).equals("")) {

        }
    }

    // ****************Actions class Utils************************
    public void doActionsSendKeys(By locator, String value) {
        Actions actions = new Actions(driver);
        actions.sendKeys(getElement(locator), value).build().perform();
    }

    public void doActionsClick(By locator) {
        Actions actions = new Actions(driver);
        actions.click(checkElementClickable(locator, 10)).build().perform();
    }

    public void doActionsRightClick(By locator) {
        Actions actions = new Actions(driver);
        actions.contextClick(getElement(locator)).build().perform();
    }

    public void doActionsMoveToElement(By locator) {
        Actions actions = new Actions(driver);
        actions.moveToElement(getElement(locator)).build().perform();
    }
}
