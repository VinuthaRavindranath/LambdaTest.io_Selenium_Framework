package org.lambda.ecomm.pages;

import io.qameta.allure.Step;
import org.lambda.ecomm.constants.AppConstants;
import org.lambda.ecomm.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage {
    private WebDriver driver;
    private ElementUtil elementUtil;
    private By myAccountHeader=By.xpath("//h2[text()='My Account']");

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        elementUtil= new ElementUtil(driver);
    }

    @Step("Verifying my account header")
    public boolean isMyAccountHeaderDisplayed(){
        boolean flag=elementUtil.waitForElementVisible(myAccountHeader, AppConstants.DEFAULT_SHORT_TIME_OUT).isDisplayed();
        return flag;
    }


}
