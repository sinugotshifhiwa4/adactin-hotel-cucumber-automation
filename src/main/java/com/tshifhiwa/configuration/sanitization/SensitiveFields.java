package com.tshifhiwa.configuration.sanitization;

import java.util.Set;

public class SensitiveFields {

    private SensitiveFields() {}

    public static final Set<String> MASKED_FIELDS = Set.of(
            "username",
            "password",
            "apikey",
            "secretkey",
            "authorization",
            "auth",
            "authentication",
            "token",
            "accesstoken",
            "refreshtoken",
            "bearertoken",
            "cookie",
            "jwt",
            "secret"
    );

    public static boolean isSensitive(String key) {
        String lower = key.toLowerCase().replaceAll("[_\\-\\s]", "");
        return MASKED_FIELDS.stream().anyMatch(lower::contains);
    }

    public static String mask(String key, String value) {
        return isSensitive(key) ? "***" : value;
    }
}
