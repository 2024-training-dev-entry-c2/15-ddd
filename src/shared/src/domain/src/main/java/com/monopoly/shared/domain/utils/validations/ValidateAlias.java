package com.monopoly.shared.domain.utils.validations;

import java.util.regex.Pattern;

public class ValidateAlias {
  public static void execute(String alias, String fieldName) {
    ValidateNull.execute(alias, fieldName);
    if (alias.length() > 30) {
      throw new IllegalArgumentException(fieldName + " cannot be longer than 30 characters");
    }
    validateAliasMatches(alias, fieldName);
  }

  private static void validateAliasMatches(String alias, String fieldName){
    if (!Pattern.matches("^[a-zA-Z0-9._-]+$", alias)) {
      throw new IllegalArgumentException(fieldName + " contains invalid characters");
    }
  }
}
