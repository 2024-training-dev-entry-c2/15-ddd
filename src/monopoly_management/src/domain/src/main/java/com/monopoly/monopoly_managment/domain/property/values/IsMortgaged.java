package com.monopoly.monopoly_managment.domain.property.values;

import com.monopoly.shared.domain.generic.IValueObject;
import com.monopoly.shared.domain.utils.Validator;

public class IsMortgaged implements IValueObject {
  private final Boolean value;

  private IsMortgaged(Boolean value) {
    this.value = value;
    validate();
  }

  public static IsMortgaged of(Boolean value) {
    return new IsMortgaged(value);
  }

  public Boolean getValue() {
    return value;
  }

  @Override
  public void validate() {
    Validator.validateNull(value, "Is mortgaged");
  }
}
