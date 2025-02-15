package com.monopoly.monopoly_managment.domain.property.values;

import com.monopoly.shared.domain.generic.IValueObject;
import com.monopoly.shared.domain.utils.Validator;

public class Rate implements IValueObject {
  private final Double value;

  private Rate(Double value) {
    this.value = value;
    validate();
  }

  public static Rate of(Double value) {
    return new Rate(value);
  }

  public Double getValue() {
    return value;
  }

  @Override
  public void validate() {
    Validator.validateNegative(this.value, "Rate");
    Validator.validateMax(this.value, 2000.0, "Rate");
  }
}
