package com.tshifhiwa.configuration.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class DriverFactory {

    private static final Logger logger = LogManager.getLogger(DriverFactory.class);
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverFactory() {
    }


    public static void setDriver(WebDriver webDriver) {
        if (driver.get() != null) {
            logger.warn("Driver already set on this thread, quitting existing before replacing");
            driver.get().quit();
        }
        driver.set(webDriver);
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalStateException("Driver is not initialised");
        }

        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    public static boolean isDriverInitialised() {
        return driver.get() != null;
    }
}
