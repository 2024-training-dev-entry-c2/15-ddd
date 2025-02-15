package com.monopoly.shared.domain.utils.validations;

public class ValidateNull {
  public static void execute(Object value, String fieldName) {
    if (value == null) {
      throw new IllegalArgumentException(fieldName + " cannot be null");
    }
  }
}
