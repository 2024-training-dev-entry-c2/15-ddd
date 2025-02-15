package com.monopoly.monopoly_managment.domain.bank_account.values;

import com.monopoly.shared.domain.generic.IValueObject;
import com.monopoly.shared.domain.utils.Validator;

public class Type implements IValueObject {
  private final TypeEnum value;

  private Type(final TypeEnum value) {
    this.value = value;
    validate();
  }

  public static Type of(final TypeEnum value) {
    return new Type(value);
  }

  public TypeEnum getValue() {
    return value;
  }

  @Override
  public void validate() {
    Validator.validateEnum(TypeEnum.class, value, "Type value");
  }
}
