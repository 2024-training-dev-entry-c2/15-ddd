package com.monopoly.monopoly_managment.domain.property.values;

import com.monopoly.shared.domain.generic.IValueObject;
import com.monopoly.shared.domain.utils.Validator;

public class Name implements IValueObject {
  private final String value;

  private Name(String value) {
    this.value = value;
    validate();
  }

  public static Name of(String value) {
    return new Name(value);
  }

  public String getValue() {
    return value;
  }

  @Override
  public void validate() {
    Validator.validateString(value, "Name");
  }
}
