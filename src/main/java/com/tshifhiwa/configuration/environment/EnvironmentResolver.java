package com.tshifhiwa.configuration.environment;

import com.tshifhiwa.configuration.sanitization.SensitiveFields;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EnvironmentResolver {

    private static final Logger logger = LogManager.getLogger(EnvironmentResolver.class);

    private EnvironmentResolver() {
    }

    public static String getBaseUrl() {
        return resolve("CI_PORTAL_BASE_URL", "PORTAL_BASE_URL");
    }

    public static String getUsername() {
        return resolve("CI_PORTAL_USERNAME", "PORTAL_USERNAME");
    }

    public static String getPassword() {
        return resolve("CI_PORTAL_PASSWORD", "PORTAL_PASSWORD");
    }

    private static String resolve(String ciKey, String localKey) {
        String key = EnvironmentDetector.isCI() ? ciKey : localKey;
        return EnvironmentManager.get(key);
    }
}
