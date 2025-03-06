package com.monopoly.shared.domain.utils.validations;

import java.util.UUID;

public class ValidateUUID {
  public static void execute(String uuid, String fieldName) {
    ValidateNull.execute(uuid, fieldName);
    try {
      UUID.fromString(uuid);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid UUID format for " + fieldName);
    }
  }
}
