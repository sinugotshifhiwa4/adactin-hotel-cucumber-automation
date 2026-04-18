package com.tshifhiwa.configuration.driver;

import org.openqa.selenium.WebDriver;

public class DriverInitializer {

    public static void init(String browser) {
        if (!DriverFactory.isDriverInitialised()) {
            WebDriver driver = BrowserManager.createDriver(browser);
            DriverFactory.setDriver(driver);
        }
    }
}
