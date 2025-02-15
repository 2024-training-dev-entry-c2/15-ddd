package com.monopoly.monopoly_managment.domain.bank_account.values;

import com.monopoly.shared.domain.generic.IValueObject;
import com.monopoly.shared.domain.utils.Validator;

public class Amount implements IValueObject {
  private final Double value;

  private Amount(final Double value) {
    this.value = value;
    validate();
  }

  public static Amount of(final Double value) {
    return new Amount(value);
  }

  public Double getValue() {
    return value;
  }

  @Override
  public void validate() {
    Validator.validateNegative(value, "Amount value");
    Validator.validateMax(value, 1000000.0, "Amount value");
  }
}
