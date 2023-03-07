package org.lambda.ecomm.pages;

import io.qameta.allure.Step;
import org.lambda.ecomm.constants.AppConstants;
import org.lambda.ecomm.utils.ElementUtil;
import org.openqa.selenium.WebDriver;

public class ProductLandingPage {
    private WebDriver driver;
    private ElementUtil elementUtil;

    public ProductLandingPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    @Step("Getting the product landing page title")
    public String getProductLandPageTitle(){
        String PlpPageTitle=elementUtil.getPageTitle();
        return PlpPageTitle;
    }

}
