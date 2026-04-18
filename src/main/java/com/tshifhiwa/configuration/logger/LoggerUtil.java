package com.tshifhiwa.configuration.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class LoggerUtil {

    private LoggerUtil() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    /**
     * Returns a logger for the given class.
     * Log4j2 handles caching internally - no wrapper cache needed.
     *
     * @param clazz The class to get the logger for
     * @return Logger instance for the class
     */
    public static Logger getLogger(Class<?> clazz) {
        return (Logger) LogManager.getLogger(clazz);
    }

    /**
     * Returns a named logger scoped under the test evidence hierarchy.
     * Ensures all test lifecycle events (start, pass, fail, screenshots)
     * route to the dedicated evidence appender in log4j2.xml.
     * <p>
     * Usage:
     * Logger testLog = LoggerUtils.getTestLogger(testName);
     * testLog.info("[TEST START] {} | Browser: {}", testName, browser);
     * testLog.info("[SCREENSHOT] {}", screenshotPath);
     * testLog.error("[TEST FAIL] {} | Reason: {}", testName, reason);
     *
     * @param testName The name of the current test or scenario
     * @return Logger routing to the test evidence appender
     */
    public static Logger getTestLogger(String testName) {
        return (Logger) LogManager.getLogger("com.tshifhiwa.tests." + testName);
    }

}
