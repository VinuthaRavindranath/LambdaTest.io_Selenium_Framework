package org.lambda.ecomm.utils;

import org.lambda.ecomm.factory.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ElementUtil {
    private WebDriver driver;
    private JavaScriptUtil jsUtil;

    public ElementUtil(WebDriver driver) {
        this.driver = driver;
        jsUtil = new JavaScriptUtil(driver);
    }

    public WebElement getElement(By locator) {
        WebElement ele= driver.findElement(locator);
        if(Boolean.parseBoolean(DriverFactory.highlight)){
            jsUtil.flash(ele);
        }
        return ele;
    }

    public String getPageTitle() {
       return  driver.getTitle();
    }

    public List<WebElement> getElements(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        return elements;
    }

    public int getElementsSize(By locator) {
        int elements = driver.findElements(locator).size();
        return elements;
    }

    public void doSendKeys(By locator, String text) {
        getElements(locator).clear();
        getElement(locator).sendKeys(text);
    }

    public void doClick(By locator) {
        getElement(locator).click();
    }

    public String doGetText(By locator) {
        String text = getElement(locator).getText();
        return text;
    }

    public String doGetTextFromWebElement(WebElement element) {
        String text = element.getText();
        return text;
    }

    public void doClearField(By locator) {
        getElement(locator).clear();
    }

    public void clearAllFields(By locator) {
        List<WebElement> elements = getElements(locator);
        for (WebElement element : elements) {
            element.clear();
        }
    }

    public List<String> getElementsAttributeValue(By locator, String attribute) {
        List<String> eleValues = new ArrayList<String>();
        List<WebElement> eleAttributeValues = getElements(locator);
        for (WebElement element : eleAttributeValues) {
            String values = element.getAttribute(attribute);
            eleValues.add(values);
        }
        return eleValues;
    }

    public List<String> getElementsText(By locator) {
        List<String> eleValues = new ArrayList<String>();
        List<WebElement> eleAttributeValues = getElements(locator);
        for (WebElement element : eleAttributeValues) {
            String values = element.getText();
            eleValues.add(values);
        }
        return eleValues;
    }

    public void getElementsTextAndClick(By locator, String value) {
        List<WebElement> languages = getElements(locator);
        for (WebElement e : languages) {
            String text = e.getText();
            if (text.equals(value)) {
                e.click();
                break;
            }
        }
    }

    public void getElementsByAttributeAndClick(By locator, String attributeValue, String value) {
        List<WebElement> languages = getElements(locator);
        for (WebElement e : languages) {
            String val = e.getAttribute(attributeValue);
            if (val.contains(value)) {
                e.click();
                break;
            }
        }
    }

    /**
     * Select class utils
     */

    public void selectByVisibleText(By locator, String text) {
        Select select = new Select(getElement(locator));
        select.selectByVisibleText(text);
    }

    public void selectByIndex(By locator, int index) {
        Select select = new Select(getElement(locator));
        select.selectByIndex(index);
    }

    public void selectByValue(By locator, String value) {
        Select select = new Select(getElement(locator));
        select.selectByValue(value);
    }

    public List<String> selectGetOptions(By locator) {
        Select select = new Select(getElement(locator));
        List<String> values = new ArrayList<String>();
        List<WebElement> options = select.getOptions();
        for (WebElement element : options) {
            String optionsList = element.getText();
            values.add(optionsList);
        }
        return values;
    }

    // ************************Wait Utils **********************//

    /**
     * An expectation for checking that an element is present on the DOM of a page.
     * This does not necessarily mean that the element is visible.
     *
     * @param locator
     * @param timeOut
     * @return
     */
    public WebElement waitForElementPresence(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

    }

    /**
     * An expectation for checking that an element is present on the DOM of a page
     * and visible. Visibility means that the element is not only displayed but also
     * has a height and width that is greater than 0.
     *
     * @param locator
     * @param timeOut
     * @return
     */
    public WebElement waitForElementVisible(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public List<WebElement> waitForElementsVisible(List<WebElement> element, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }

    public boolean waitForElementInVisible(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public boolean waitForElementsInVisible(List<WebElement> element, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.invisibilityOfAllElements(element));
    }

    public Alert waitForAlertPresence(int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public WebElement waitForElementToBeClickable(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.ignoring(StaleElementReferenceException.class);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public String getAlertText(int timeOut) {
        return waitForAlertPresence(timeOut).getText();
    }

    public void acceptAlert(int timeOut) {
        waitForAlertPresence(timeOut).accept();
    }

    public void dismissAlert(int timeOut) {
        waitForAlertPresence(timeOut).dismiss();
    }

    public void alertSendKeys(int timeOut, String value) {
        waitForAlertPresence(timeOut).sendKeys(value);
    }

    public String waitForTitleContainsAndFetch(int timeOut, String titleFractionValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.titleContains(titleFractionValue));
        return driver.getTitle();
    }

    public String waitForTitleIsAndFetch(int timeOut, String titleValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.titleIs(titleValue));
        return driver.getTitle();
    }

    public String waitForURLContainsAndFetch(int timeOut, String urlFractionValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.urlContains(urlFractionValue));
        return driver.getCurrentUrl();
    }

    public String waitForURLIsAndFetch(int timeOut, String urlValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.urlToBe(urlValue));
        return driver.getCurrentUrl();
    }

    public boolean waitForURLContains(int timeOut, String urlFractionValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.urlContains(urlFractionValue));
    }

    public void waitForFrameAndSwitchToItByIDOrName(int timeOut, String idOrName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
    }

    public void waitForFrameAndSwitchToItByIndex(int timeOut, int frameIndex) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
    }

    public void waitForFrameAndSwitchToItByFrameElement(int timeOut, WebElement frameElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
    }

    public void waitForFrameAndSwitchToItByFrameLoctor(int timeOut, By frameLocator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }

    /**
     * An expectation for checking an element is visible and enabled such that you can click it.
     *
     * @param timeOut
     * @param locator
     */
    public void clickWhenReady(int timeOut, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public WebElement waitForElementToBeClickable(int timeOut, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }


    public void doActionsSendKeys(By locator, String value) {
        Actions act = new Actions(driver);
        act.sendKeys(getElement(locator), value).build().perform();
    }

    public void doActionsClick(By locator) {
        Actions act = new Actions(driver);
        act.click(getElement(locator)).build().perform();
    }

    public void doActionsMoveToElement(By locator) {
        Actions act = new Actions(driver);
        act.moveToElement(getElement(locator)).build().perform();
    }


    public void doClickWithActionsAndWait(int timeOut, By locator) {
        WebElement ele = waitForElementToBeClickable(timeOut, locator);
        Actions act = new Actions(driver);
        act.click(ele).build().perform();
    }

    public WebElement waitForElementPresenceWithFluentWait(int timeOut, int pollingTime, By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(timeOut))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .withMessage("...element is not found on the page....");

        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

    }

    public void waitForAlertWithFluentWait(int timeOut, int pollingTime) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(timeOut))
                .ignoring(NoAlertPresentException.class)
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .withMessage("...Alert is not found on the page....");

        wait.until(ExpectedConditions.alertIsPresent());

    }


}
