package com.monopoly.monopoly_managment.domain.property.values;

import com.monopoly.shared.domain.generic.IValueObject;
import com.monopoly.shared.domain.utils.Validator;

public class Price implements IValueObject {
  private final Double value;

  private Price(Double value) {
    this.value = value;
    validate();
  }

  public static Price of(Double value) {
    return new Price(value);
  }

  public Double getValue() {
    return value;
  }

  @Override
  public void validate() {
    Validator.validateNegative(value, "Price");
  }
}
