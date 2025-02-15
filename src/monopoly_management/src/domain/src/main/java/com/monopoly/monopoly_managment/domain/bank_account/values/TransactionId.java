package com.monopoly.monopoly_managment.domain.bank_account.values;

import com.monopoly.shared.domain.generic.Identity;

public class TransactionId extends Identity {
  public TransactionId() {
    super();
  }

  private TransactionId(String value){
    super(value);
  }

  public static TransactionId of(String value){
    return new TransactionId(value);
  }
}
