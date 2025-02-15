package com.monopoly.monopoly_managment.domain.property.values;

import com.monopoly.shared.domain.generic.Identity;

public class PropertyId extends Identity {
  public PropertyId() {
    super();
  }

  private PropertyId(String value){
    super(value);
  }

  public static PropertyId of(String value){
    return new PropertyId(value);
  }
}
