package org.lambda.ecomm.pages;

import io.qameta.allure.Step;
import org.lambda.ecomm.constants.AppConstants;
import org.lambda.ecomm.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;

public class CartPage {
    private WebDriver driver;
    private ElementUtil elementUtil;

    private By cartPageHeader=By.cssSelector("h1.page-title");
    private By productNameInCart=By.cssSelector("table td.text-left a");
    private By removeProductButton=By.cssSelector("button.btn-danger");


    public CartPage(WebDriver driver) {
        this.driver = driver;
        elementUtil= new ElementUtil(driver);
    }

    @Step("Getting the cart page header")
    public Boolean getCartPageHeader(){
        String cartHeader= elementUtil.doGetText(cartPageHeader);
        String[] header=cartHeader.split("&");
        Boolean flag=Arrays.stream(header).anyMatch(name->name.contains("Shopping Cart"));
        return flag;
    }

    @Step("Getting the product name in the cart")
    public String getProductNameInCart(){
        return elementUtil.doGetText(productNameInCart);
    }

    @Step("Removing the product from the cart")
    public void removeProductFromCart(){
        elementUtil.waitForElementVisible(removeProductButton, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
        elementUtil.doClick(removeProductButton);
    }


}
