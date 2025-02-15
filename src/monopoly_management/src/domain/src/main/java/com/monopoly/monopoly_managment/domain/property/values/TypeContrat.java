package com.monopoly.monopoly_managment.domain.property.values;

import com.monopoly.shared.domain.generic.IValueObject;
import com.monopoly.shared.domain.utils.Validator;

public class TypeContrat implements IValueObject {
  private final TypeImprovementEnum value;

  private TypeContrat(final TypeImprovementEnum value) {
    this.value = value;
    validate();
  }

  public static TypeContrat of(final TypeImprovementEnum value) {
    return new TypeContrat(value);
  }

  public TypeImprovementEnum getValue() {
    return value;
  }

  @Override
  public void validate() {
    Validator.validateEnum(TypeImprovementEnum.class, this.value, "TypeContrat value");
  }
}
