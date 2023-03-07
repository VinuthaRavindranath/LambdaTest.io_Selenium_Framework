package org.lambda.ecomm.tests;

import org.lambda.ecomm.base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartPageAndCheckoutTest extends BaseTest {

    @BeforeMethod
    public void clearCart(){
        cartPage =homePage.clearCart();
        cartPage.removeProductFromCart();
    }
}
