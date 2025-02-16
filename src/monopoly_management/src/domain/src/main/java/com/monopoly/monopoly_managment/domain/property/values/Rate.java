package com.monopoly.monopoly_managment.domain.property.values;

import com.monopoly.shared.domain.generic.IValueObject;
import com.monopoly.shared.domain.utils.Validator;

public class Rate implements IValueObject {
  private final Double base;
  private final Double withHotel;

  private Rate(Double base, Double withHotel) {
    this.base = base;
    this.withHotel = withHotel;
    validate();
  }

  public static Rate of(Double base, Double withHotel) {
    return new Rate( base, withHotel);
  }

  public Double getBase() {
    return base;
  }

  public Double getWithHotel() {
    return withHotel;
  }

  @Override
  public void validate() {
    Validator.validateNegative(base, "Base rate must be positive");
    Validator.validateNegative(withHotel, "Rate with hotel must be positive");
    Validator.validateMax(base, withHotel, "Base rate must be less than rate with hotel");
    Validator.validateMax(withHotel, 100.0, "Rate with hotel must be less than 100");
    Validator.validateBiggerThan(base, withHotel, "Base rate must be greater than rate with hotel");
  }
}
