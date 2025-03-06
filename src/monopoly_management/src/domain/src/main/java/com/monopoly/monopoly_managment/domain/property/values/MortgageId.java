package com.monopoly.monopoly_managment.domain.property.values;

import com.monopoly.shared.domain.generic.Identity;

public class MortgageId extends Identity {
  public MortgageId() {
    super();
  }

  private MortgageId(String value){
    super(value);
  }

  public static MortgageId of(String value){
    return new MortgageId(value);
  }
}
