package com.monopoly.monopoly_managment.domain.property.values;

import com.monopoly.shared.domain.generic.IValueObject;
import com.monopoly.shared.domain.utils.Validator;

public class TypeImprovement implements IValueObject {
  private final TypeImprovementEnum value;

  private TypeImprovement(final TypeImprovementEnum value) {
    this.value = value;
    validate();
  }

  public static TypeImprovement of(final TypeImprovementEnum value) {
    return new TypeImprovement(value);
  }

  public TypeImprovementEnum getValue() {
    return value;
  }

  @Override
  public void validate(){
    Validator.validateEnum(TypeImprovementEnum.class, this.value, "TypeImprovement value");
  }
}
