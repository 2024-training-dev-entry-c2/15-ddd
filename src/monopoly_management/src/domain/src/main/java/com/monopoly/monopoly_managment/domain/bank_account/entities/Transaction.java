package com.monopoly.monopoly_managment.domain.bank_account.entities;

import com.monopoly.monopoly_managment.domain.bank_account.values.Amount;
import com.monopoly.monopoly_managment.domain.bank_account.values.TransactionId;
import com.monopoly.monopoly_managment.domain.bank_account.values.Type;
import com.monopoly.shared.domain.generic.Entity;

public class Transaction extends Entity<TransactionId> {
  private Amount amount;
  private Type type;

  public Transaction(TransactionId identity, Amount amount, Type type) {
    super(identity);
    this.amount = amount;
    this.type = type;
  }

  public Transaction(Amount amount, Type type) {
    super(new TransactionId());
    this.amount = amount;
    this.type = type;
  }

  public Amount getAmount() {
    return amount;
  }

  public void setAmount(Amount amount) {
    this.amount = amount;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }
}
