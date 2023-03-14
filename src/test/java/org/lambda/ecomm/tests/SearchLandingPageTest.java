package org.lambda.ecomm.tests;

import io.qameta.allure.*;
import org.lambda.ecomm.base.BaseTest;
import org.lambda.ecomm.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("EPIC-05: Search Landing Page Design")
@Story("US-Search:06 Search Landing Page and filters")
public class SearchLandingPageTest extends BaseTest {

    @Description("Sorting products by price using Low to high filter")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProvider ="getSearchKeywords")
    public void sortProductsByPriceLowToHighTest(String searchKeyword) {
        searchLandingPage = homePage.searchWithKeyword(searchKeyword);
        searchLandingPage.sortProductsByPriceLowToHigh();
        Assert.assertEquals(searchLandingPage.getProductsPrices(), searchLandingPage.sortPriceByAscendingOrder());
    }

    @Description("Sorting products by price using hig to low filter")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProvider ="getSearchKeywords")
    public void sortProductsByPriceHighToLowTest(String searchKeyword) {
        searchLandingPage = homePage.searchWithKeyword(searchKeyword);
        searchLandingPage.sortProductsByPriceHighToLow();
        Assert.assertEquals(searchLandingPage.getProductsPrices(), searchLandingPage.sortPriceByDescendingOrder());
    }

    @Description("Filtering products by Manufacturer filter")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProvider="getManufacturerData")
    public void applyManufacturerFilterTest(String manufacturerName, String prodName) throws InterruptedException {
        searchLandingPage = homePage.searchWithKeyword(prop.getProperty("searchKeyword").trim());
        searchLandingPage.applyManufacturerFilter(manufacturerName);
        Assert.assertTrue(searchLandingPage.validateFilteredProducts(prodName));
    }

    @Description("Verfying the product count is greater than 0 on search landing page")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProvider ="getSearchKeywords")
    public void searchProductCountTest(String searchKeyword) {
        searchLandingPage = homePage.searchWithKeyword(searchKeyword);
        Assert.assertTrue(searchLandingPage.getSearchProductsCount()>0);
    }

    @Description("Search and navigate to product page")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void NavigateToProductPageTest(){
        searchLandingPage = homePage.searchWithKeyword(prop.getProperty("searchKeyword").trim());
        productDescriptionPage =searchLandingPage.clickOnProduct(prop.getProperty("productName").trim());
        Assert.assertEquals(productDescriptionPage.getProductName(), AppConstants.PRODUCT_NAME);
    }

    @DataProvider
    public Object[][] getSearchKeywords() {
        return new Object[][]{
                {"Touch"},
                {"Htc"},
                {"Samsung"},
                {"canon"}
        };
    }

    @DataProvider
    public Object[][] getManufacturerData() {
        return new Object[][]{
                {"HTC","HTC"}
        };
    }
}
