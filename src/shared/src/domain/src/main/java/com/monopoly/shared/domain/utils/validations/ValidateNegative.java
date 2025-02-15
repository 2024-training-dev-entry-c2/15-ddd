package com.monopoly.shared.domain.utils.validations;

public class ValidateNegative {
  public static void execute(Double value, String fieldName) {
    ValidateNull.execute(value, fieldName);
    if (value < 0) {
      throw new IllegalArgumentException(fieldName + " cannot be negative");
    }
  }
}
