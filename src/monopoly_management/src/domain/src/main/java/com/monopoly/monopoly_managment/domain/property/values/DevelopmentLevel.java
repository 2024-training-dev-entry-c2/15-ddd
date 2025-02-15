package com.monopoly.monopoly_managment.domain.property.values;

import com.monopoly.shared.domain.generic.IValueObject;
import com.monopoly.shared.domain.utils.Validator;

public class DevelopmentLevel implements IValueObject {
  private final Integer value;

  private DevelopmentLevel(Integer value) {
    this.value = value;
    validate();
  }

  public static DevelopmentLevel of(Integer value) {
    return new DevelopmentLevel(value);
  }

  public Integer getValue() {
    return value;
  }

  @Override
  public void validate() {
    Validator.validateRange(this.value, 0, 8, "Development level");
  }
}
