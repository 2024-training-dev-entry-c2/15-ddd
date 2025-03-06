package com.monopoly.monopoly_managment.domain.property.values;

import com.monopoly.shared.domain.generic.IValueObject;
import com.monopoly.shared.domain.utils.Validator;

public class IsActive implements IValueObject {
  private final Boolean value;

  private IsActive(Boolean value) {
    this.value = value;
    validate();
  }

  public static IsActive of(Boolean value) {
    return new IsActive(value);
  }

  public Boolean getValue() {
    return value;
  }

  @Override
  public void validate() {
    Validator.validateNull(this.value, "Is active");
  }
}
