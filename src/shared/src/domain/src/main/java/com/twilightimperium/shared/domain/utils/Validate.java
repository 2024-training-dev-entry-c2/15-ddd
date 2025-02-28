package com.twilightimperium.shared.domain.utils;

public class Validate {

    public static void validateNotNull(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty.");
        }
    }

    public static void validateNotEmpty(String value, String fieldName) {
        if (value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty.");
        }
    }

    public static void validateAlphanumeric(String value, String fieldName) {
        if (!value.matches("^[a-zA-Z0-9\\s]+$")) {
            throw new IllegalArgumentException(fieldName + " must contain only alphanumeric characters.");
        }
    }
    
    public static void validatePositiveNumber(Integer number, String fieldName) {
        if (number.doubleValue() <= 0) {
            throw new IllegalArgumentException(fieldName + " must be greater than 0.");
        }
    }

    public static void validateMoreThanOne(Integer number, String fieldName) {
        if (number.doubleValue() <= 1) {
            throw new IllegalArgumentException(fieldName + " must be greater than 1.");
        }
    }
}
