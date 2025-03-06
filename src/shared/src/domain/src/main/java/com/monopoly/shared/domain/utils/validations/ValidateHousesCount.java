package com.monopoly.shared.domain.utils.validations;

public class ValidateHousesCount {
  public static void execute(Integer value, String fieldName) {
    ValidateNull.execute(value, fieldName);
    ValidateRange.execute(value, 0, 5, fieldName);
  }
}
