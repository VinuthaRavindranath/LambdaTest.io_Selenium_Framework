package org.lambda.ecomm.base;


import org.lambda.ecomm.factory.DriverFactory;
import org.lambda.ecomm.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.Properties;


public class BaseTest {
    DriverFactory driverFactory;

    protected WebDriver driver;
    protected Properties prop;
    protected SearchLandingPage searchLandingPage;
    protected HomePage homePage;
    protected ProductLandingPage productLandingPage;
    protected LoginPage loginPage;
    protected MyAccountPage myAccountPage;
    protected ForgotPasswordPage forgotPasswordPage;
    protected ProductDescriptionPage productDescriptionPage;
    protected WishListPage wishlistPage;
    protected CartPage cartPage;
    protected RegistrationPage registrationPage;

    protected SoftAssert softAssert;

    @BeforeTest
    public WebDriver startDriver() {
        driverFactory = new DriverFactory();
        prop = driverFactory.initProp();
        driver= driverFactory.initDriver(prop);
        homePage= new HomePage(driver);
        softAssert = new SoftAssert();
        return driver;
    }

    @AfterTest
    public void closeDriver() {
        if (driver != null) {
            driver.close();
        }
    }
}
