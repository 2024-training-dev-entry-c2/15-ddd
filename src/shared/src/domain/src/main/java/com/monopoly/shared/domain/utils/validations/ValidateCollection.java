package com.monopoly.shared.domain.utils.validations;

import java.util.Collection;

public class ValidateCollection {
  public static void execute(Collection<?> collection, String fieldName) {
    ValidateNull.execute(collection, fieldName);
    if ( collection.isEmpty()) {
      throw new IllegalArgumentException(fieldName + " cannot be empty");
    }
  }
}
