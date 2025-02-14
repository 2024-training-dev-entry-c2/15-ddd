package com.monopoly.monopoly_managment.domain.bank_account.values;

public class Amount {
  private final Double value;

  public Amount(Double value){
    if (value < 0) {
      throw new IllegalArgumentException("Amount cannot be negative");
    }
    this.value = value;
  }

  public Double getValue() {
    return value;
  }

}
