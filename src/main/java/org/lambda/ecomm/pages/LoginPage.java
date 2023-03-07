package org.lambda.ecomm.pages;

import io.qameta.allure.Step;
import org.lambda.ecomm.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    private ElementUtil elementUtil;

    private By emailInput=By.id("input-email");
    private By passwordInput=By.id("input-password");
    private By loginButton=By.xpath("//input[@type='submit']");
    private By forgotPasswordLink=By.linkText("Forgotten Password");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        elementUtil= new ElementUtil(driver);
    }

    @Step("Getting login page Title")
    public String getLoginPageTitle(){
        String loginPageTitle=elementUtil.getPageTitle();
        return loginPageTitle;
    }

    @Step("Login with email: {0} and password: {1}")
    public MyAccountPage doLogin(String email, String password){
        elementUtil.doSendKeys(emailInput,email);
        elementUtil.doSendKeys(passwordInput,password);
        elementUtil.doClick(loginButton);
        return new MyAccountPage(driver);
    }

    @Step("Clicking on forgot password link")
    public ForgotPasswordPage clickOnForgotPasswordLink(){
        elementUtil.doClick(forgotPasswordLink);
        return new ForgotPasswordPage(driver);
    }
}
