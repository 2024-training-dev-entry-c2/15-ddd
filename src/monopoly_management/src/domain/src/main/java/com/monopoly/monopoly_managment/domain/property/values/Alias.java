package com.monopoly.monopoly_managment.domain.property.values;

import com.monopoly.shared.domain.generic.IValueObject;
import com.monopoly.shared.domain.utils.Validator;

public class Alias implements IValueObject {
  private final String value;

  private Alias(final String value) {
    this.value = value;
    validate();
  }

  public static Alias of(final String value) {
    return new Alias(value);
  }

  public String getValue() {
    return value;
  }

  @Override
  public void validate() {
    Validator.validateString(value, "Alias value");
  }
}
