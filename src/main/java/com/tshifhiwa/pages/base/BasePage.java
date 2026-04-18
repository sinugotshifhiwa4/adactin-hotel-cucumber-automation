package com.tshifhiwa.pages.base;

import com.tshifhiwa.configuration.driver.DriverFactory;
import com.tshifhiwa.configuration.driver.WaitFactory;
import com.tshifhiwa.configuration.logger.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePage {

    private final Logger log = LoggerUtil.getLogger(BasePage.class);

    protected WebDriver driver() {
        return DriverFactory.getDriver();
    }

    protected WaitFactory waitFactory() {
        return new WaitFactory();
    }

    public void navigateToPortal(String url) {
        driver().get(url);
    }

    public void clickElement(By by, String elementName) {
        try {
            WebElement element = waitFactory().getExplicitWait()
                    .until(ExpectedConditions.elementToBeClickable(by));
            element.click();
            log.info("Clicked element [element={}]", elementName);
        } catch (TimeoutException e) {
            log.error("Element not clickable within timeout [element={}]", elementName, e);
            throw e;
        }
    }

    public void clearElement(By by, String elementName) {
        try {
            WebElement element = waitFactory().getExplicitWait()
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
            element.clear();
            log.info("Cleared element [element={}]", elementName);
        } catch (TimeoutException e) {
            log.error("Element not visible within timeout [element={}]", elementName, e);
            throw e;
        }
    }

    public void fillElement(By by, String value, String elementName) {
        try {
            WebElement element = waitFactory().getExplicitWait()
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
            element.sendKeys(value);
            log.info("Filled element [element={}] [value={}]", elementName, value);
        } catch (TimeoutException e) {
            log.error("Element not visible within timeout [element={}]", elementName, e);
            throw e;
        }
    }

    public WebElement waitForElementVisible(By by, String elementName) {
        try {
            WebElement element = waitFactory().getExplicitWait()
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
            log.info("Element is visible [element={}]", elementName);
            return element;
        } catch (TimeoutException e) {
            log.error("Element not visible within timeout [element={}]", elementName, e);
            throw e;
        }
    }

    public void waitForElementNotVisible(By by, String elementName) {
        try {
            waitFactory().getExplicitWait()
                    .until(ExpectedConditions.invisibilityOfElementLocated(by));
            log.info("Element is not visible [element={}]", elementName);
        } catch (TimeoutException e) {
            log.error("Element still visible within timeout [element={}]", elementName, e);
            throw e;
        }
    }

    public String getPageTitle() {
        String title = driver().getTitle();
        log.info("Page title [title={}]", title);
        return title;
    }
}