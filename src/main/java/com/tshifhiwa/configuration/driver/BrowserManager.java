package com.tshifhiwa.configuration.driver;

import com.tshifhiwa.configuration.environment.EnvironmentDetector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserManager {

    private static final Logger logger = LogManager.getLogger(BrowserManager.class);

    private BrowserManager() {}

    public static WebDriver createDriver(String browser) {
        logger.info("Creating driver [browser={}]", browser);
        boolean isCI = EnvironmentDetector.isCI();

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                if (isCI) {
                    chromeOptions.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
                }
                return new ChromeDriver(chromeOptions);

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                if (isCI) {
                    firefoxOptions.addArguments("--headless");
                }
                return new FirefoxDriver(firefoxOptions);

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                if (isCI) {
                    edgeOptions.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
                }
                return new EdgeDriver(edgeOptions);

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }
}