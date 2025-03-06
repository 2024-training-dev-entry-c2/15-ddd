package com.monopoly.shared.domain.utils.validations;

import java.util.Set;

public class ValidatePropertyGroup {
  public static void execute(String group) {
    ValidateNull.execute(group, "group");
    if (!Set.of("BROWN", "LIGHT_BLUE", "PINK", "ORANGE", "RED", "YELLOW", "GREEN", "DARK_BLUE").contains(group)) {
      throw new IllegalArgumentException("Invalid property group");
    }
  }
}
