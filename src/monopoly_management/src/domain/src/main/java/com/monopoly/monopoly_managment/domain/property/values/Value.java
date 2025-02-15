package com.monopoly.monopoly_managment.domain.property.values;

import com.monopoly.shared.domain.generic.IValueObject;
import com.monopoly.shared.domain.utils.Validator;

public class Value implements IValueObject {
  private final Double value;

  private Value(final Double value) {
    this.value = value;
    validate();
  }

  public static Value of(final Double value) {
    return new Value(value);
  }

  public Double getValue() {
    return value;
  }

  @Override
  public void validate() {
    Validator.validateNegative(value, "Value");
  }
}
