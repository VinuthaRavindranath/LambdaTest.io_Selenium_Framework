package org.lambda.ecomm.tests;

import io.qameta.allure.*;
import org.lambda.ecomm.base.BaseTest;
import org.lambda.ecomm.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("EPIC-01: Home Page Design")
@Story("US-Home:02 Home Page Design and Category Navigation")
public class HomePageAndNavigationTest  extends BaseTest {

    @Description("Getting the title of the home page")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void homePageTitleTest(){
        String homePageTitle=homePage.getHomePageTitle();
        Assert.assertEquals(homePageTitle, AppConstants.HOME_PAGE_TITLE);
    }

    @Description("Validate Taxonomy and search results")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void searchSuggestionsTest(){
        homePage.enterSearchKeyword(prop.getProperty("searchKeyword"));
        Assert.assertTrue(homePage.getSearchSuggestions());
    }

    @Description("Validate Category Navigation via Shop By Category option")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProvider = "getShopByCategoryData")
    public void navigateToCategoryViaShopCategoryTest(String category){
        productLandingPage =homePage.clickOnShopByCategory(category);
        String plpPageTitle=productLandingPage.getProductLandPageTitle();
        Assert.assertEquals(plpPageTitle,category);
    }

    @Description("Validate Category Navigation via Mega Menu Category option")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProvider = "getMegaMenuCategoryData")
    public void navigateToCategoryViaMegaMenuTest(String category){
        productLandingPage = homePage.selectCategoryFromMegaMenuDropdown(category);
        String plpPageTitle=productLandingPage.getProductLandPageTitle();
        Assert.assertEquals(plpPageTitle,category);
    }

    @DataProvider
    public Object[][] getShopByCategoryData() {
        return new Object[][]{
                {"Cameras"},
                {"MP3 Players"},
                {"Components"},
                {"Web Cameras"},
                {"Mice and Trackballs"}
        };
    }

    @DataProvider
    public Object[][] getMegaMenuCategoryData() {
        return new Object[][]{
                {"Apple"},
                {"HTC"}
        };
    }
}
