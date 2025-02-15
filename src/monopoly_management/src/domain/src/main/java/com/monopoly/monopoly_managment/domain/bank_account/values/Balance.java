package com.monopoly.monopoly_managment.domain.bank_account.values;

import com.monopoly.shared.domain.generic.IValueObject;
import com.monopoly.shared.domain.utils.Validator;

public class Balance implements IValueObject {
  private final Double value;

  private Balance(final Double value) {
    this.value = value;
    validate();
  }

  public static Balance of(final Double value) {
    return new Balance(value);
  }

  public Double getValue() {
    return value;
  }

  @Override
  public void validate() {
    Validator.validateNegative(value, "Balance value");
    Validator.validateMax(value, 1000000.0, "Balance value");
  }
}
