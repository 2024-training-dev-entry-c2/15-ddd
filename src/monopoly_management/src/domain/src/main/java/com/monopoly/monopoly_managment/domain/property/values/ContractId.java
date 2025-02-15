package com.monopoly.monopoly_managment.domain.property.values;

import com.monopoly.shared.domain.generic.Identity;

public class ContractId extends Identity {
  public ContractId() {
    super();
  }

  private ContractId(String value){
    super(value);
  }

  public static ContractId of(String value){
    return new ContractId(value);
  }
}
