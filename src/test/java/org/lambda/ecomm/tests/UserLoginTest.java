package org.lambda.ecomm.tests;

import io.qameta.allure.*;
import org.lambda.ecomm.base.BaseTest;
import org.lambda.ecomm.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("EPIC-07: Login Page Design")
@Story("US-Login:08 Login page and forgot password")
public class UserLoginTest extends BaseTest {
    @BeforeClass
    public void loginPageSetup() {
        loginPage=homePage.clickOnLoginLink();
    }

    @Description("Getting the title of login page")
    @Severity(SeverityLevel.MINOR)
    @Test(priority = 1)
    public void loginPageTitleTest() {
        String loginPageTitle=loginPage.getLoginPageTitle();
        Assert.assertEquals(loginPageTitle, AppConstants.LOGIN_PAGE_TITLE);
    }

    @Description("Do user login")
    @Severity(SeverityLevel.BLOCKER)
    @Test(priority = 3)
    public void doLoginTest() {
        myAccountPage =loginPage.doLogin(prop.getProperty("userName").trim(),prop.getProperty("password").trim());
        Assert.assertTrue(myAccountPage.isMyAccountHeaderDisplayed());

    }

    @Description("Do forgot password")
    @Severity(SeverityLevel.BLOCKER)
    @Test(priority = 2)
    public void forgotPasswordTest() {
        forgotPasswordPage =loginPage.clickOnForgotPasswordLink();
        forgotPasswordPage.enterEmail(prop.getProperty("userName").trim());
        forgotPasswordPage.clickOnContinueButton();
        String forgotPasswordSuccessMsg=forgotPasswordPage.getForgotPasswordSuccessMessage();
        Assert.assertEquals(forgotPasswordSuccessMsg.trim(),AppConstants.FORGOT_PASSWORD_SUCCESS_Message);
    }

    @Description("Add product to wishlist once after logged in")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 4)
    public void addProductToWishListAsLoggedInUserTest() {
        searchLandingPage=homePage.searchWithKeyword(prop.getProperty("searchKeyword").trim());
        productDescriptionPage =searchLandingPage.clickOnProduct(prop.getProperty("productName").trim());
        productDescriptionPage.addProductToWishList();
        wishlistPage= productDescriptionPage.verifyWishlistSuccessToastPopup();
        Assert.assertEquals(wishlistPage.getProductNameAddedToWishlist(),AppConstants.PRODUCT_NAME);
        Assert.assertEquals(wishlistPage.removeProductFromWishList(),AppConstants.EMPTY_WISHLIST_MESSAGE);
    }
}
