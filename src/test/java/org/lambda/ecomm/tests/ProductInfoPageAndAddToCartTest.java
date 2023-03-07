package org.lambda.ecomm.tests;

import io.qameta.allure.*;
import org.lambda.ecomm.base.BaseTest;
import org.lambda.ecomm.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;

@Epic("EPIC-03: Product Description Page Design")
@Story("US-ProductInfo:04 Product Description Page and Add to cart")
public class ProductInfoPageAndAddToCartTest extends BaseTest {
    @BeforeMethod()
    public void pdpPageSetUp()
    {
        searchLandingPage = homePage.searchWithKeyword(prop.getProperty("searchKeyword").trim());
        productDescriptionPage = searchLandingPage.clickOnProduct(AppConstants.PRODUCT_NAME);
    }

    @Description("Getting the product meta data information")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void productMetaDataInfoTest() {
        LinkedHashMap<String, String> actProductInfo= productDescriptionPage.getProductMetadata();
        softAssert.assertEquals(actProductInfo.get("productName"), AppConstants.PRODUCT_NAME);
        softAssert.assertEquals(actProductInfo.get("Product Code"), "Product 1");
        softAssert.assertEquals(actProductInfo.get("Brand"), "HTC");
        softAssert.assertEquals(actProductInfo.get("Reward Points"),"400");
        softAssert.assertEquals(actProductInfo.get("Availability"),"In Stock");
        softAssert.assertEquals(actProductInfo.get("price"),"$146.00");

        softAssert.assertAll();
    }

    @Description("Adding the product to wishlist as an anonymous user")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void addProductToWishListAsAnonymousUserTest() {
        productDescriptionPage.addProductToWishList();
        loginPage = productDescriptionPage.getWishlistWarningToastPopup();
        Assert.assertEquals(loginPage.getLoginPageTitle(), AppConstants.LOGIN_PAGE_TITLE);
    }

    @Description("Verifying the add product to cart on product description page")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void verifyAddToCartSuccessMessageOnPdpTest() {
        productDescriptionPage.addProductToCart();
        Assert.assertTrue(productDescriptionPage.verifyCartToCartSuccessMsgOnPdp());
    }

    @Description("Add product to cart and navigate to cart page")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void verifyProductAddedToCartTest() {
        productDescriptionPage.addProductToCart();
        cartPage =productDescriptionPage.navigateToCartPageFromPdp();
        softAssert.assertTrue(cartPage.getCartPageHeader());
        softAssert.assertEquals(cartPage.getProductNameInCart(),AppConstants.PRODUCT_NAME);

        softAssert.assertAll();
    }
}
