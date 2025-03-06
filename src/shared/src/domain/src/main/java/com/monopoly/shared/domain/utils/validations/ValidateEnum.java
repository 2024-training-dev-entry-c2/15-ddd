package com.monopoly.shared.domain.utils.validations;

import java.util.EnumSet;

public class ValidateEnum {
  public static <E extends Enum<E>> void excute(Class<E> enumClass, E value, String fieldName) {
    ValidateNull.execute(value, fieldName);
    if (!EnumSet.allOf(enumClass).contains(value)) {
      throw new IllegalArgumentException(fieldName + " is invalid");
    }
  }
}
