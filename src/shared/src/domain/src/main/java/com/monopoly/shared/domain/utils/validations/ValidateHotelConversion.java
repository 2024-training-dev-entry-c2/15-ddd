package com.monopoly.shared.domain.utils.validations;

public class ValidateHotelConversion {
  public static void validate(int houses, boolean hasHotel) {
    ValidateNull.execute(houses, "houses");
    if (hasHotel) {
      throw new IllegalArgumentException("Property already has a hotel");
    }
    ValidateRange.execute(houses, 4, 4, "Houses");
  }
}
