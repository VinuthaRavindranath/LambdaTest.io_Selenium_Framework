package org.lambda.ecomm.pages;

import io.qameta.allure.Step;
import org.lambda.ecomm.constants.AppConstants;
import org.lambda.ecomm.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.LinkedHashMap;
import java.util.List;

public class ProductDescriptionPage {
    private WebDriver driver;
    private ElementUtil elementUtil;

    private By productTitle = By.cssSelector("h1.h3");
    private By productPrice = By.cssSelector(".price-new");
    private By addToWishListButton = By.xpath("(//button[@title='Add to Wish List'])[1]");
    private By wishListWarningToastPopup = By.cssSelector("#notification-box-top span.mr-auto");
    private By loginButtonOnToastPopup = By.cssSelector("a.btn-danger");

    private By successToastPopup = By.cssSelector("#notification-box-top > div ");
    private By wishlistButton = By.linkText("Wish List (1)");
    private By addToCartButton = By.xpath("(//button[@title='Add to Cart'][normalize-space()='Add to Cart'])[2]");
    private By viewCartButton = By.xpath("//a[@class='btn btn-primary btn-block']");

    private By productInfo = By.cssSelector("ul.list-unstyled li");

    private By addToCartSuccessToastMsg=By.cssSelector("#notification-box-top p");

    private LinkedHashMap<String, String> productInfoMap;

    public ProductDescriptionPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    @Step("Getting the product name")
    public String getProductName() {
        elementUtil.waitForElementVisible(productTitle, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
        return elementUtil.doGetText(productTitle);
    }

    @Step("Adding the product to wish list")
    public void addProductToWishList() {
        elementUtil.doClick(addToWishListButton);
    }

    @Step("Getting the Warning Toast Popup for Wish List for an anonymous user")
    public LoginPage getWishlistWarningToastPopup() {
        elementUtil.waitForElementVisible(wishListWarningToastPopup, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
        elementUtil.getElement(wishListWarningToastPopup).isDisplayed();
        elementUtil.doClick(loginButtonOnToastPopup);
        return new LoginPage(driver);
    }

    @Step("Getting the Success Toast Popup for Wish List for an Logged in user")
    public WishListPage verifyWishlistSuccessToastPopup() {
        elementUtil.waitForElementVisible(successToastPopup, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
        elementUtil.getElement(successToastPopup).isDisplayed();
        elementUtil.doClick(wishlistButton);
        return new WishListPage(driver);
    }

    @Step("Adding the product to cart")
    public void addProductToCart() {
        elementUtil.doClick(addToCartButton);
    }

    @Step("Getting the Success Toast Popup for Adding product to Cart")
    public boolean verifyCartToCartSuccessMsgOnPdp(){
        elementUtil.waitForElementVisible(addToCartSuccessToastMsg, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
        String addToCartSuccessMsg=elementUtil.doGetText(addToCartSuccessToastMsg).replace("\n", " ").trim();
        if (addToCartSuccessMsg.equals(AppConstants.ADD_TO_CART_SUCCESS_MESSAGE.trim())){
            return true;
        }
        return false;
    }

    @Step("Navigate to the cart page")
    public CartPage navigateToCartPageFromPdp(){
        elementUtil.waitForElementVisible(successToastPopup, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
        elementUtil.getElement(successToastPopup).isDisplayed();
        elementUtil.doClick(viewCartButton);
        return new CartPage(driver);
    }

    @Step("Getting the product metadata info")
    private void getProductInfo() {
        List<String> productsInfo = elementUtil.getElementsText(productInfo);
        for (String productInfo : productsInfo) {
            String[] metaData = productInfo.split(":");
            String key = metaData[0].trim();
            String value = metaData[1].trim();
            productInfoMap.put(key, value);
        }
    }

    @Step("Getting the product price")
    private void getProductPriceData() {
        String prodPrice = elementUtil.doGetText(productPrice);
        productInfoMap.put("price", prodPrice);
    }

    @Step("Getting the product metadata info and product price")
    public LinkedHashMap<String, String> getProductMetadata() {
        productInfoMap = new LinkedHashMap<String, String>();
        String productName = elementUtil.doGetText(productTitle);
        productInfoMap.put("productName", productName);
        getProductInfo();
        getProductPriceData();
        System.out.println(productInfoMap);
        return productInfoMap;

    }
}
