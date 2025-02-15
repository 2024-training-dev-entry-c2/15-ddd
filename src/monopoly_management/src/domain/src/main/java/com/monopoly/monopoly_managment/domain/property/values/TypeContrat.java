package com.monopoly.monopoly_managment.domain.property.values;

import com.monopoly.shared.domain.generic.IValueObject;
import com.monopoly.shared.domain.utils.Validator;

public class TypeContrat implements IValueObject {
  private final TypeContratEnum value;

  private TypeContrat(final TypeContratEnum value) {
    this.value = value;
    validate();
  }

  public static TypeContrat of(final TypeContratEnum value) {
    return new TypeContrat(value);
  }

  public TypeContratEnum getValue() {
    return value;
  }

  @Override
  public void validate() {
    Validator.validateEnum(TypeContratEnum.class, this.value, "TypeContrat value");
  }
}
