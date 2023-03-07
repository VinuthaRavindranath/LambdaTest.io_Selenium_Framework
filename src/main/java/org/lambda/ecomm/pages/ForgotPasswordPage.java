package org.lambda.ecomm.pages;

import io.qameta.allure.Step;
import org.lambda.ecomm.constants.AppConstants;
import org.lambda.ecomm.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    private By emailInput=By.id("input-email");
    private By continueButton=By.xpath("//button[text()='Continue']");
    private By forgotPasswordSuccessMessage=By.xpath("//div[contains(@class,'alert-success')]");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        elementUtil= new ElementUtil(driver);
    }

    @Step("enter email")
    public void enterEmail(String email) {
        elementUtil.doSendKeys(emailInput, email);
    }

    @Step("click continue button")
    public void clickOnContinueButton() {
        elementUtil.doClick(continueButton);
    }

    @Step("verify forgot password success message")
    public String getForgotPasswordSuccessMessage() {
        elementUtil.waitForElementVisible(forgotPasswordSuccessMessage, AppConstants.DEFAULT_SHORT_TIME_OUT);
        return elementUtil.doGetText(forgotPasswordSuccessMessage);
    }

}
