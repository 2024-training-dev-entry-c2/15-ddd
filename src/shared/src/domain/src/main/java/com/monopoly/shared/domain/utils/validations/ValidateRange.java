package com.monopoly.shared.domain.utils.validations;

public class ValidateRange {
  public static void execute(int value, int min, int max, String fieldName) {
    if (value < min || value > max) {
      throw new IllegalArgumentException(
        String.format("%s must be between %d-%d", fieldName, min, max)
      );
    }
  }
}