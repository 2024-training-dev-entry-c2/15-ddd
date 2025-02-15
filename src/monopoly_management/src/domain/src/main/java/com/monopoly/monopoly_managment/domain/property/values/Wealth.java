package com.monopoly.monopoly_managment.domain.property.values;

import com.monopoly.shared.domain.generic.IValueObject;
import com.monopoly.shared.domain.utils.Validator;

public class Wealth implements IValueObject {
  private final Double value;

  private Wealth(final Double value) {
    this.value = value;
  }

  public static Wealth of(final Double value) {
    return new Wealth(value);
  }

  public Double getValue() {
    return value;
  }

  @Override
  public void validate() {
    Validator.validateNegative(value, "Wealth");
    Validator.validateMax(value, 1000000.0, "Wealth");
  }
}
