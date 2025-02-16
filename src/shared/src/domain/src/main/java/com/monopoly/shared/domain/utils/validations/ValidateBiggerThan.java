package com.monopoly.shared.domain.utils.validations;

public class ValidateBiggerThan {
  public static void execute(Double value, Double min, String fieldName) {
    ValidateNull.execute(value, fieldName);
    if ( value < min) {
      throw new IllegalArgumentException(fieldName + " cannot be less than " + min);
    }
  }
}
