package com.monopoly.monopoly_managment.domain.bank_account.entities;

import com.monopoly.monopoly_managment.domain.bank_account.values.Amount;
import com.monopoly.monopoly_managment.domain.bank_account.values.Destiny;
import com.monopoly.monopoly_managment.domain.bank_account.values.Origin;
import com.monopoly.monopoly_managment.domain.bank_account.values.TransactionId;
import com.monopoly.monopoly_managment.domain.bank_account.values.Type;
import com.monopoly.shared.domain.generic.Entity;
import com.monopoly.shared.domain.utils.Validator;

public class Transaction extends Entity<TransactionId> {
  private Amount amount;
  private Type type;
  private Destiny destiny;
  private Origin origin;

  public Transaction(TransactionId identity, Amount amount, Type type, Destiny destiny, Origin origin) {
    super(identity);
    this.amount = amount;
    this.type = type;
    this.destiny = destiny;
    this.origin = origin;
    validate();
  }

  public Transaction(Amount amount, Type type, Destiny destiny, Origin origin) {
    super(new TransactionId());
    this.amount = amount;
    this.type = type;
    this.destiny = destiny;
    this.origin = origin;
    validate();
  }

  public Destiny getDestiny() {
    return destiny;
  }

  public void setDestiny(Destiny destiny) {
    this.destiny = destiny;
  }

  public Origin getOrigin() {
    return origin;
  }

  public void setOrigin(Origin origin) {
    this.origin = origin;
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

  public void validate(){
    Validator.validateNull(amount, "Amount");
    Validator.validateNull(type, "Type");
    Validator.validateNegative(amount.getValue(), "Amount");
    Validator.validateMax(amount.getValue(), 10000.0, "Amount");
    Validator.validateNotEquals(destiny.getValue(), origin.getValue(), "Destiny and Origin");
  }

  public String toString() {
    return "Transaction{" +
            "amount=" + amount +
            ", type=" + type +
            ", destiny=" + destiny +
            ", origin=" + origin +
            '}';
  }
}
