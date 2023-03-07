package org.lambda.ecomm.pages;

import io.qameta.allure.Step;
import org.lambda.ecomm.constants.AppConstants;
import org.lambda.ecomm.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WishListPage {
    private WebDriver driver;
    private ElementUtil elementUtil;
    private By productNameInWishList= By.cssSelector("table td.text-left a");
    private By removeFromWishList= By.cssSelector("td.text-right > a > i");

    private By noResultEle= By.cssSelector("div#content p");

    public WishListPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    public String getWishlistPageTitle(){
        String wishlistPageTitle=elementUtil.getPageTitle();
        return wishlistPageTitle;
    }

    @Step("Getting the product Name from Wish List")
    public String getProductNameAddedToWishlist(){
        elementUtil.waitForElementVisible(productNameInWishList, AppConstants.DEFAULT_LONG_TIME_OUT);
        return elementUtil.doGetText(productNameInWishList);
    }

    @Step("Removing the product from Wish List")
    public String removeProductFromWishList(){
        elementUtil.waitForElementVisible(removeFromWishList, AppConstants.DEFAULT_LONG_TIME_OUT);
        elementUtil.doClick(removeFromWishList);
        elementUtil.waitForElementVisible(noResultEle, AppConstants.DEFAULT_LONG_TIME_OUT);
        return elementUtil.doGetText(noResultEle);

    }

}
