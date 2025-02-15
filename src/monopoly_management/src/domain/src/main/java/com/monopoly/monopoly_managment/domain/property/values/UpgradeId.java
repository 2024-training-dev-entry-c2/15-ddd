package com.monopoly.monopoly_managment.domain.property.values;

import com.monopoly.shared.domain.generic.Identity;

public class UpgradeId extends Identity {
  public UpgradeId() {
    super();
  }

  private UpgradeId(String value){
    super(value);
  }

  public static UpgradeId of(String value){
    return new UpgradeId(value);
  }
}
