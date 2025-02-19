
  package com.monopoly.monopoly_managment.domain.bank_account.values;

import com.monopoly.shared.domain.generic.Identity;
import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TransactionId that = (TransactionId) o;
    return Objects.equals(getValue(), that.getValue());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getValue());
  }
}