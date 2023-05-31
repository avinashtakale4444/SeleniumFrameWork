package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private WebDriver driver;
    private ElementUtil elementUtil;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(this.driver);
    }

    private By registerLink = By.linkText("Register");
    private By firstName=By.id("input-firstname");
    private By lastName=By.id("input-lastname");
    private By email=By.id("input-email");
    private By telephone=By.id("input-telephone");
    private By password=By.id("input-password");
    private By confirmPassword=By.id("input-confirm");
    private By subscribeYes=By.xpath("(//*[@class='radio-inline'])[1]/input");
    private By subscribeNo=By.xpath("(//*[@class='radio-inline'])[2]/input");
    private By agree=By.name("agree");
    private By submitBtn=By.xpath("//input[@type='submit']");

}
