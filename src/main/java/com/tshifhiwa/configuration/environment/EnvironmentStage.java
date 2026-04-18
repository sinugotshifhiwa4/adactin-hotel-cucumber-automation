package com.tshifhiwa.configuration.environment;

public enum EnvironmentStage {
    DEV, QA, UAT, PREPROD, PROD;

    public static EnvironmentStage fromString(String value) {
        try {
            return valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return DEV;
        }
    }
}
