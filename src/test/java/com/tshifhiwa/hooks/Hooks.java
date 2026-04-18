package com.tshifhiwa.hooks;

import com.tshifhiwa.configuration.driver.DriverFactory;
import com.tshifhiwa.configuration.driver.DriverInitializer;
import com.tshifhiwa.pages.adactinHotel.LoginPage;
import com.tshifhiwa.pages.context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    private static final Logger logger = LogManager.getLogger(Hooks.class);
    private final TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before
    public void initialiseDriver() {
        String browser = System.getProperty("browser", "chrome");
        logger.info("Initialising driver [browser={}]", browser);
        DriverInitializer.init(browser);
        context.loginPage = new LoginPage();
    }

    @AfterStep
    public void captureScreenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed() && DriverFactory.isDriverInitialised()) {
            logger.warn("Step failed — capturing screenshot [scenario={}]", scenario.getName());
            byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
    }

    @After
    public void quitDriver(Scenario scenario) {
        logger.info("Scenario completed [scenario={}] [status={}]",
                scenario.getName(),
                scenario.getStatus());
        DriverFactory.quitDriver();
    }
}