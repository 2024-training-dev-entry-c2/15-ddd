package com.monopoly.shared.domain.utils.validations;

public class ValidateString {
  public static void execute(String value, String fieldName) {
    ValidateNull.execute(value, fieldName);
    if ( value.trim().isEmpty()) {
      throw new IllegalArgumentException(fieldName + " cannot be empty");
    }
  }
}