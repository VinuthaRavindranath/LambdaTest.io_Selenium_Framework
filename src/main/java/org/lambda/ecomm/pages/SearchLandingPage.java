package org.lambda.ecomm.pages;

import io.qameta.allure.Step;
import org.lambda.ecomm.constants.AppConstants;
import org.lambda.ecomm.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;


public class SearchLandingPage {
    private WebDriver driver;
    private ElementUtil elementUtil;
    private ArrayList<String> arrPrices;
    private ArrayList<String> sortPricesAcc;
    private ArrayList<String> sortPricesDesc;
    private ArrayList<String> productsTitle;
    private By searchHeader = By.cssSelector("h1.h4");
    private By dropDown = By.xpath("(//select[contains(@id,'input-sort')])[1]");
    private By productsPrices = By.cssSelector("span.price-new");
    private By productsTitleEle = By.cssSelector("h4.title a");
    private By loader = By.cssSelector("div.mz-filter-loader div.loader-spinner");
    private By lazyLoad=By.cssSelector("div.carousel-item img.lazy-load");
    private By productImages = By.xpath("//a[contains(@id,'mz-product-grid-image')]");
    private By productPods=By.cssSelector("div.product-layout div.carousel-inner");


    public SearchLandingPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    public boolean getHeaderText() {
        String headerText = elementUtil.doGetText(searchHeader);
        if (headerText.equals(AppConstants.DEFAULT_SEARCH_PAGE_HEADER)) {
            return true;
        }
        return false;
    }

    @Step("Clicking on price low to high sort option")
    public void sortProductsByPriceLowToHigh() {
        elementUtil.selectByVisibleText(dropDown, AppConstants.PRICE_LOW_TO_HIGH_FILTER);
    }

    @Step("Clicking on price high to low sort option")
    public void sortProductsByPriceHighToLow() {
        elementUtil.selectByVisibleText(dropDown, AppConstants.PRICE_HIGH_TO_LOW_FILTER);
    }

    @Step("Getting the product prices")
    public ArrayList<String> getProductsPrices() {
        arrPrices = new ArrayList<>();
        List<WebElement> prices = driver.findElements(productsPrices);
        prices.stream().forEach(price -> arrPrices.add(price.getText()));
        return arrPrices;
    }

    @Step("Logic to sort prices in ascending order")
    public ArrayList<String> sortPriceByAscendingOrder() {
        sortPricesAcc = new ArrayList<>();
        getProductsPrices().stream().sorted().forEach(price -> sortPricesAcc.add(price));
        return sortPricesAcc;
    }

    @Step("Logic to sort prices in descending order")
    public ArrayList<String> sortPriceByDescendingOrder() {
        sortPricesDesc = new ArrayList<>();
        getProductsPrices().stream().sorted(Comparator.reverseOrder()).forEach(price -> sortPricesDesc.add(price));
        return sortPricesDesc;
    }

    @Step("Apply Manufacturer filter")
    public void applyManufacturerFilter(String manufacturerName) throws InterruptedException {
        By manufacturerFilter = By.xpath("//div[contains(@class,'manufacturer')]//label/img[@alt='" + manufacturerName + "']");
        elementUtil.doClick(manufacturerFilter);
        elementUtil.waitForElementInVisible(loader, AppConstants.DEFAULT_LONG_TIME_OUT);
        List<WebElement> lazyLoaders = driver.findElements(lazyLoad);
        elementUtil.waitForElementsInVisible(lazyLoaders,AppConstants.DEFAULT_LONG_TIME_OUT);
    }

    @Step("Getting the product titles")
    public ArrayList<String> getProductsTitle() {
        productsTitle = new ArrayList<>();
        List<String> productsName = elementUtil.getElementsText(productsTitleEle);
        productsName.stream().forEach(product -> productsTitle.add(product));
        return productsTitle;
    }

    @Step("Validating the filtered products is having manufacturer's name")
    public boolean validateFilteredProducts(String manufacturerName) {
        boolean flag = getProductsTitle().stream().allMatch(productName -> productName.contains(manufacturerName));
        return flag;
    }

    @Step("Checking if the search result's product count is greater than 0")
    public int getSearchProductsCount(){
        List<WebElement> products=elementUtil.getElements(productPods);
        int productCount=elementUtil.waitForElementsVisible(products,AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
        return productCount;
    }

    @Step("On Search Page, click on a product and naviagte to the product details page")
    public ProductDescriptionPage clickOnProduct(String productName) {
        By productNameEle = By.xpath("(//a[@class='text-ellipsis-2' and text()='"+productName+"'])[1]");
        elementUtil.doClick(productNameEle);
        return new ProductDescriptionPage(driver);
    }
}
