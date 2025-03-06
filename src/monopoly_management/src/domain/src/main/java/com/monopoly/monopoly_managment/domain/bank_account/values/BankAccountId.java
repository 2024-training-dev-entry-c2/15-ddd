package com.monopoly.monopoly_managment.domain.bank_account.values;

import com.monopoly.shared.domain.generic.Identity;

import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BankAccountId that = (BankAccountId) o;
    return Objects.equals(getValue(), that.getValue());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getValue());
  }
}
