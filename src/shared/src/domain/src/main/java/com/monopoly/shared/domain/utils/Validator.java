package com.monopoly.shared.domain.utils;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

public class Validator {

  private Validator() {}

  public static void validateString(String value, String fieldName) {
    validateNull(value, fieldName);
    if (value.trim().isEmpty()) {
      throw new IllegalArgumentException(fieldName + " cannot be empty");
    }
  }

  public static void validateMax(Double value, Double max, String fieldName) {
    validateNull(value, fieldName);
    validateIsNan(value, fieldName);
    if (value > max) {
      throw new IllegalArgumentException(fieldName + " cannot be greater than " + max);
    }
  }

  public static void validateAlias(String alias, String fieldName) {
    validateNull(alias, fieldName);
    if (alias.length() > 30) {
      throw new IllegalArgumentException(fieldName + " cannot be longer than 30 characters");
    }
    validateAliasMatches(alias, fieldName);
  }

  public static void validateAliasMatches(String alias, String fieldName){
    if (!Pattern.matches("^[a-zA-Z0-9._-]+$", alias)) {
      throw new IllegalArgumentException(fieldName + " contains invalid characters");
    }
  }

  public static <E extends Enum<E>> void validateEnum(Class<E> enumClass, E value, String fieldName) {
    validateNull(value, fieldName);
    if (!EnumSet.allOf(enumClass).contains(value)) {
      throw new IllegalArgumentException(fieldName + " is invalid");
    }
  }

  public static void validateNegative(Double number, String fieldName) {
    validateNull(number, fieldName);
    validateIsNan(number, fieldName);
    if (number < 0) {
      throw new IllegalArgumentException(fieldName + " must be a non-negative number");
    }
    validateIsNan(number, fieldName);
  }

  public static void validateIsNan(Double number, String fieldName) {
    validateNull(number, fieldName);
    if (Double.isNaN(number)) {
      throw new IllegalArgumentException(fieldName + " must be a number");
    }
  }

  public static void validateNull(Object object, String fieldName) {
    if (object == null) {
      throw new IllegalArgumentException(fieldName + " cannot be null");
    }
  }

  public static void validatePropertyGroup(String group) {
    validateNull(group, "Property group");
    if (!Set.of("BROWN", "LIGHT_BLUE", "PINK", "ORANGE", "RED", "YELLOW", "GREEN", "DARK_BLUE").contains(group)) {
      throw new IllegalArgumentException("Invalid property group");
    }
  }

  public static void validateUUID(String uuid, String fieldName) {
    validateNull(uuid, fieldName);
    try {
      UUID.fromString(uuid);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid UUID format for " + fieldName);
    }
  }

  public static void validateCollection(Collection<?> collection, String fieldName) {
    validateNull(collection, fieldName);
    if (collection.isEmpty()) {
      throw new IllegalArgumentException(fieldName + " cannot be empty");
    }
  }

  public static void validateHousesCount(int houses) {
    validateNull(houses, "Houses");
    validateRange(houses, 0, 4, "Houses");
  }

  public static void validateHotelConversion(int houses, boolean hasHotel) {
    validateNull(houses, "Houses");
    if (hasHotel) {
      throw new IllegalArgumentException("Property already has a hotel");
    }
    validateRange(houses, 4, 4, "Houses");
  }

  public static void validateRange(int value, int min, int max, String fieldName) {
    validateNull(value, fieldName);
    validateNull(min, "Min");
    validateNull(max, "Max");
    if (value < min || value > max) {
      throw new IllegalArgumentException(
        String.format("%s must be between %d-%d", fieldName, min, max)
      );
    }
  }
}