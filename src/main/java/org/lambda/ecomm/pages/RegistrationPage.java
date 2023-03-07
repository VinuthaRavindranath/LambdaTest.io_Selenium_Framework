package org.lambda.ecomm.pages;

import io.qameta.allure.Step;
import org.lambda.ecomm.constants.AppConstants;
import org.lambda.ecomm.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class RegistrationPage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    private By firstName = By.id("input-firstname");
    private By lastName = By.id("input-lastname");
    private By email = By.id("input-email");
    private By telephone = By.id("input-telephone");
    private By password = By.id("input-password");
    private By confirmPassword = By.id("input-confirm");
    private By SubscribeYesRadioBtn=By.xpath("//input[@id='input-newsletter-yes']");
    private By SubscribeNoRadioBtn=By.xpath("//input[@id='input-newsletter-no']");
    private By privacyCheckbox =By.xpath("//input[@id='input-agree'and @type='checkbox']");
    private By submitButton =By.xpath("//input[@type='submit']");
    private By logoutButton=By.xpath("//a[@class='list-group-item' and normalize-space()='Logout']");
    private By registerSuccessMessage=By.cssSelector("div#content h1");
    private By registerLink=By.xpath("//a[@class='list-group-item' and normalize-space()='Register']");


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    @Step("Verify mandatory fields on registration registration page")
    public List<String> checkMandatoryFields() {
        List<String> stars= new ArrayList<String>();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (String field : AppConstants.MANDATORY_INPUTS) {
            String script = "return window.getComputedStyle(document.querySelector(\"label[for='" + field + "']\"), '::after').getPropertyValue('content')";
            stars.add(js.executeScript(script).toString().replace("\"", ""));
        }
        return stars;
    }

    @Step("Registering a new user")
    public boolean doRegisterUser(String firstName, String lastName, String email, String telephone, String password, String subscribe)
    {
        elementUtil.waitForElementVisible(this.firstName,AppConstants.DEFAULT_SHORT_TIME_OUT);
        elementUtil.doSendKeys(this.firstName,firstName);
        elementUtil.doSendKeys(this.lastName,lastName);
        elementUtil.doSendKeys(this.email,email);
        elementUtil.doSendKeys(this.telephone,telephone);
        elementUtil.doSendKeys(this.password,password);
        elementUtil.doSendKeys(this.confirmPassword,password);
        if (subscribe.equalsIgnoreCase("yes")) {
            elementUtil.doActionsClick(SubscribeYesRadioBtn);
        }
        else {
            elementUtil.doActionsClick(SubscribeNoRadioBtn);
        }
        elementUtil.doActionsClick(privacyCheckbox);
        elementUtil.doClick(submitButton);
        elementUtil.waitForElementVisible(registerSuccessMessage,AppConstants.DEFAULT_LONG_TIME_OUT);
        String registerSuccessMsg=elementUtil.doGetText(registerSuccessMessage);
        if (registerSuccessMsg.contains(AppConstants.REGISTER_SUCCESS_MSG)) {
            elementUtil.doClick(logoutButton);
            elementUtil.doClick(registerLink);
            return true;
        }
        return false;
    }
}
