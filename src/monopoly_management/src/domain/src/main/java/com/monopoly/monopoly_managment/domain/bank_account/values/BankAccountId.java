package com.monopoly.monopoly_managment.domain.bank_account.values;

import com.monopoly.shared.domain.generic.Identity;

public class BankAccountId extends Identity {
  public BankAccountId() {
    super();
  }

  private BankAccountId(String value){
    super(value);
  }

  public static BankAccountId of(String value){
    return new BankAccountId(value);
  }
}
