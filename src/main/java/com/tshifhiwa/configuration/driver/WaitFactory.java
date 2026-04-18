package com.tshifhiwa.configuration.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitFactory {

    private static final long TIMEOUT_SECONDS = 45;
    private static final long POLLING_MS = 600;

    public Wait<WebDriver> getFluentWait() {
        WebDriver d = DriverFactory.getDriver();
        return new FluentWait<>(d)
                .withTimeout(Duration.ofSeconds(TIMEOUT_SECONDS))
                .pollingEvery(Duration.ofMillis(POLLING_MS))
                .ignoring(WebDriverException.class);
    }

    public WebDriverWait getExplicitWait() {
        return new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(TIMEOUT_SECONDS));
    }
}