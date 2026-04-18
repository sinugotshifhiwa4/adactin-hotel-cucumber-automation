package com.tshifhiwa.configuration.environment;

import com.tshifhiwa.configuration.sanitization.SensitiveFields;
import io.github.cdimascio.dotenv.Dotenv;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EnvironmentManager {

    private static final Logger logger = LogManager.getLogger(EnvironmentManager.class);

    private static Dotenv dotenv;

    private EnvironmentManager() {
    }

    public static void init() {
        if (dotenv == null) {
            String stage = EnvironmentDetector.getCurrentStage().name().toLowerCase();
            String filename = ".env." + stage;
            boolean isCI = EnvironmentDetector.isCI();

            logger.info("Loading environment file [file={}] [ci={}]", filename, isCI);

            if (isCI) {
                // In CI, environment variables are injected by the pipeline
                // No .env file needed - use system environment directly
                dotenv = Dotenv.configure()
                        .ignoreIfMissing()
                        .systemProperties()
                        .load();
            } else {
                // Locally, load from .env file
                dotenv = Dotenv.configure()
                        .directory("./envs")
                        .filename(filename)
                        .load();
            }
        }
    }

    public static String get(String key) {
        ensureLoaded();
        String value = dotenv.get(key);

        if (value == null || value.isBlank()) {
            throw new IllegalStateException(
                    "Missing required environment variable: " + key
                            + " [stage=" + EnvironmentDetector.getCurrentStage() + "]"
            );
        }

        logger.debug("Resolved [{}={}]", key, SensitiveFields.mask(key, value));
        return value;
    }

    private static void ensureLoaded() {
        if (dotenv == null) {
            throw new IllegalStateException(
                    "EnvironmentManager not initialised. Call init() before resolving variables."
            );
        }
    }
}