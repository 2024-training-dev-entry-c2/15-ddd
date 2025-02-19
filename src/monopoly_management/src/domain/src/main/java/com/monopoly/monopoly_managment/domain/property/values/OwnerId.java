package com.monopoly.monopoly_managment.domain.property.values;

import com.monopoly.shared.domain.generic.Identity;

public class OwnerId extends Identity {
  public OwnerId() {
    super();
  }

  public OwnerId(String value){
    super(value);
  }

  public static OwnerId of(String value){
    return new OwnerId(value);
  }
}
