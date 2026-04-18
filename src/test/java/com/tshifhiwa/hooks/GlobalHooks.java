package com.tshifhiwa.hooks;

import com.tshifhiwa.configuration.environment.EnvironmentDetector;
import com.tshifhiwa.configuration.environment.EnvironmentManager;
import io.cucumber.java.BeforeAll;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GlobalHooks {

    private static final Logger logger = LogManager.getLogger(GlobalHooks.class);

    @BeforeAll
    public static void setup() {
        EnvironmentManager.init();
        logger.info("Environment initialised [stage={}] [ci={}]",
                EnvironmentDetector.getCurrentStage(),
                EnvironmentDetector.isCI());
    }
}
