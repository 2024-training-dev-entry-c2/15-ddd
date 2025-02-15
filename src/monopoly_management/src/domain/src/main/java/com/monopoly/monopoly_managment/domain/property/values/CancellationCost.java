package com.monopoly.monopoly_managment.domain.property.values;

import com.monopoly.shared.domain.generic.IValueObject;
import com.monopoly.shared.domain.utils.Validator;

public class CancellationCost implements IValueObject {
  private final Double value;

  private CancellationCost(final Double value) {
    this.value = value;
    validate();
  }

  public static CancellationCost of(final Double value) {
    return new CancellationCost(value);
  }

  public Double getValue() {
    return value;
  }

  @Override
  public void validate() {
    Validator.validateNegative(value, "CancellationCost value");
  }
}
