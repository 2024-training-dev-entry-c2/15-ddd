package com.monopoly.shared.domain.utils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;


public class Validator {

  private Validator() {}


  public static void validateString(String value, String fieldName) {
    if (value == null || value.trim().isEmpty()) {
      throw new IllegalArgumentException(fieldName + " cannot be empty");
    }
  }

  public static void validateNegative(Double number, String fieldName) {
    if (number < 0 || Double.isNaN(number)) {
      throw new IllegalArgumentException(fieldName + " must be a non-negative number");
    }
  }

  public static void validateNull(Object object, String fieldName) {
    if (object == null) {
      throw new IllegalArgumentException(fieldName + " cannot be null");
    }
  }

  public static void validatePropertyGroup(String group) {
    if (!Set.of("BROWN", "LIGHT_BLUE", "PINK", "ORANGE",
      "RED", "YELLOW", "GREEN", "DARK_BLUE").contains(group)) {
      throw new IllegalArgumentException("Invalid property group");
    }
  }

  public static void validateUUID(String uuid, String fieldName) {
    try {
      UUID.fromString(uuid);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid UUID format for " + fieldName);
    }
  }

  public static void validateCollection(Collection<?> collection, String fieldName) {
    if (collection == null || collection.isEmpty()) {
      throw new IllegalArgumentException(fieldName + " cannot be empty");
    }
  }

  public static void validateHousesCount(int houses) {
    if (houses < 0 || houses > 4) {
      throw new IllegalArgumentException("Houses must be between 0-4");
    }
  }

  public static void validateHotelConversion(int houses, boolean hasHotel) {
    if (hasHotel) {
      throw new IllegalArgumentException("Property already has a hotel");
    }
    if (houses < 4) {
      throw new IllegalArgumentException("Need 4 houses to build hotel");
    }
  }

  public static void validateRange(int value, int min, int max, String fieldName) {
    if (value < min || value > max) {
      throw new IllegalArgumentException(
        String.format("%s must be between %d-%d", fieldName, min, max)
      );
    }
  }
}