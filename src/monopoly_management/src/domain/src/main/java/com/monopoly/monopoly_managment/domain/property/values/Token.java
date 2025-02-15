package com.monopoly.monopoly_managment.domain.property.values;

import com.monopoly.shared.domain.generic.IValueObject;
import com.monopoly.shared.domain.utils.Validator;

public class Token implements IValueObject {
  private final String value;

  private Token(String value) {
    this.value = value;
    validate();
  }

  public static Token of(String value) {
    return new Token(value);
  }

  public String getValue() {
    return value;
  }

  @Override
  public void validate() {
    Validator.validateUUID(value, "Token");
  }
}
