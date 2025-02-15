package com.monopoly.shared.domain.utils.validations;

public class ValidateMax {
  public static void execute(Double value, Double max, String fieldName) {
    ValidateNull.execute(value, fieldName);
    if ( value > max) {
      throw new IllegalArgumentException(fieldName + " cannot be greater than " + max);
    }
  }
}