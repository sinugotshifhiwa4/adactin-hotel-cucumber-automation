package com.tshifhiwa.configuration.environment;

public class EnvironmentDetector {

    private EnvironmentDetector() {
    }

    /**
     * Determines if the current execution environment is a CI/CD pipeline.
     * Checks known environment variables set automatically by major CI platforms.
     */
    public static boolean isCI() {
        return System.getenv("CI") != null
                || System.getenv("GITHUB_ACTIONS") != null
                || System.getenv("GITLAB_CI") != null
                || System.getenv("TRAVIS") != null
                || System.getenv("CIRCLECI") != null
                || System.getenv("JENKINS_URL") != null
                || System.getenv("BITBUCKET_BUILD_NUMBER") != null;
    }

    /**
     * Gets the current environment stage.
     * Reads the 'ENV' system property or environment variable, defaults to 'dev'.
     */
    public static EnvironmentStage getCurrentStage() {
        String env = System.getProperty("ENV",
                System.getenv().getOrDefault("ENV", "dev"));
        return EnvironmentStage.fromString(env);
    }

    public static boolean isDevelopment() {
        return getCurrentStage() == EnvironmentStage.DEV;
    }

    public static boolean isQA() {
        return getCurrentStage() == EnvironmentStage.QA;
    }

    public static boolean isUAT() {
        return getCurrentStage() == EnvironmentStage.UAT;
    }

    public static boolean isPreProd() {
        return getCurrentStage() == EnvironmentStage.PREPROD;
    }

    public static boolean isProduction() {
        return getCurrentStage() == EnvironmentStage.PROD;
    }
}
