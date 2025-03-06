package com.monopoly.shared.domain.utils.validations;

public class ValidateNotEqual {
  public static void execute(String value1, String value2, String fieldName) {
    ValidateNull.execute(value1, fieldName);
    ValidateNull.execute(value2, fieldName);
    if (value1.equals(value2)) {
      throw new IllegalArgumentException(fieldName + " cannot be equal to " + value2);
    }
  }
}
