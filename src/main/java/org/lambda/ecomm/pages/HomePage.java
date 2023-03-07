package org.lambda.ecomm.pages;

import io.qameta.allure.Step;
import org.lambda.ecomm.constants.AppConstants;
import org.lambda.ecomm.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class HomePage {
    private WebDriver driver;
    private ElementUtil elementUtil;

    private By searchbar = By.xpath("(//input[@name='search' ])[1]");
    private By searchButton = By.xpath("//button[text()='Search']");
    private By searchSuggestions = By.cssSelector("div.dropdown  h4>a");
    private By shopByCategoryLink = By.xpath("//*[text()=' Shop by Category']");
    private By megaMenuLink=By.xpath("//li[contains(@class,'dropdown-hoverable')]//span[normalize-space()='Mega Menu']");
    private By myAccountDropDown=By.xpath("//a[contains(@class,'dropdown-toggle')]//span[normalize-space()='My account']");
    private By loginLink =By.xpath("//a[contains(@href,'index.php?route=account/login')]");
    private By registerLink =By.xpath("//a[contains(@href,'index.php?route=account/register')]");
    private By cartIcon=By.xpath("(//div[@class='cart-icon'])[1]");


    public HomePage(WebDriver driver) {
        this.driver = driver;
        elementUtil= new ElementUtil(driver);
    }

    @Step("Getting home page title")
    public String getHomePageTitle(){
       String homePageTitle= elementUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT,AppConstants.HOME_PAGE_TITLE);
        return homePageTitle;
    }

    @Step("Entering search keyword")
    public void enterSearchKeyword(String searchKeyword) {
        elementUtil.doSendKeys(searchbar, searchKeyword);
    }

    @Step("Performing search")
    public SearchLandingPage searchWithKeyword(String searchKeyword) {
        elementUtil.doClearField(searchbar);
        elementUtil.doSendKeys(searchbar, searchKeyword);
        elementUtil.doClick(searchButton);
        return new SearchLandingPage(driver);
    }

    @Step("Getting search suggestions")
    public boolean getSearchSuggestions() {
        elementUtil.waitForElementVisible(searchSuggestions, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
        List<String> productsTitle = elementUtil.getElementsText(searchSuggestions);
        for (String productTitle : productsTitle) {
            if (productTitle.contains(AppConstants.DEFAULT_SEARCH_TERM)) {
                return true;
            }
        }
        return false;
    }

    @Step("Clicking on shop by category links")
    public ProductLandingPage clickOnShopByCategory(String categoryName) {
        elementUtil.doClick(shopByCategoryLink);
        By categoryLink = By.xpath("//a[contains(@class,'nav-link')]//span[normalize-space()='"+categoryName+"']");
        elementUtil.waitForElementVisible(categoryLink, AppConstants.DEFAULT_SHORT_TIME_OUT);
        elementUtil.doClick(categoryLink);
        return new ProductLandingPage(driver);
    }

    @Step("Clicking on mega menu category links")
    public ProductLandingPage selectCategoryFromMegaMenuDropdown(String categoryName) {
        elementUtil.doActionsMoveToElement(megaMenuLink);
        By categoryLink = By.xpath("//a[@title='"+categoryName+"']");
        elementUtil.waitForElementVisible(categoryLink,AppConstants.DEFAULT_SHORT_TIME_OUT);
        elementUtil.doClick(categoryLink);
        return new ProductLandingPage(driver);
    }

    @Step("Hovering on my account dropdown and clicking on login link")
    public LoginPage clickOnLoginLink(){
        elementUtil.doActionsMoveToElement(myAccountDropDown);
        elementUtil.waitForElementVisible(loginLink,AppConstants.DEFAULT_SHORT_TIME_OUT);
        elementUtil.doClick(loginLink);
        return new LoginPage(driver);
    }

    @Step("Hovering on my account dropdown and clicking on register link")
    public RegistrationPage navigateToRegisterPage() {
        elementUtil.doActionsMoveToElement(myAccountDropDown);
        elementUtil.waitForElementVisible(registerLink,AppConstants.DEFAULT_SHORT_TIME_OUT);
        elementUtil.doClick(registerLink);
        return new RegistrationPage(driver);
    }

    @Step("Clearing the cart")
    public CartPage clearCart(){
        elementUtil.waitForElementVisible(cartIcon,AppConstants.DEFAULT_SHORT_TIME_OUT);
        elementUtil.doClick(cartIcon);
        return new CartPage(driver);

    }
}
