package com.monopoly.monopoly_managment.domain.property.values;

import com.monopoly.shared.domain.generic.IValueObject;
import com.monopoly.shared.domain.utils.Validator;

public class ColorGroup implements IValueObject {
  private final String value;

  private ColorGroup(final String value) {
    this.value = value;
    validate();
  }

  public static ColorGroup of(final String value) {
    return new ColorGroup(value);
  }

  public String getValue() {
    return value;
  }

  @Override
  public void validate() {
    Validator.validatePropertyGroup(value);
  }
}
