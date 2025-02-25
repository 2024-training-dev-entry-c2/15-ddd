package com.monopoly.monopoly_managment.domain.bank_account.entities;

import com.monopoly.monopoly_managment.domain.bank_account.values.Balance;
import com.monopoly.monopoly_managment.domain.bank_account.values.TransactionId;
import com.monopoly.monopoly_managment.domain.bank_account.values.TypeEnum;
import com.monopoly.shared.domain.generic.Entity;
import com.monopoly.shared.domain.utils.Validator;

public class Transaction extends Entity<TransactionId> {
  private Double amount;
  private TypeEnum type;
  private String destiny;
  private String origin;

  public Transaction(TransactionId identity, Double amount, TypeEnum type, String destiny, String origin) {
    super(identity);
    this.amount = amount;
    this.type = type;
    this.destiny = destiny;
    this.origin = origin;
    validate();
  }

  public Transaction(TransactionId identity) {
    super(identity);
  }

  public Transaction(Double amount, TypeEnum type, String destiny, String origin) {
    super(new TransactionId());
    this.amount = amount;
    this.type = type;
    this.destiny = destiny;
    this.origin = origin;
    validate();
  }

  public String getDestiny() {
    return destiny;
  }

  public void setDestiny(String destiny) {
    this.destiny = destiny;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public void validate(){
    Validator.validateNotEquals(destiny, origin, "Destiny and Origin");
  }

  public String toString() {
    return "Transaction{" +
            "amount=" + amount +
            ", type=" + type +
            ", destiny=" + destiny +
            ", origin=" + origin +
            '}';
  }
  public void isEnoughFunds(Transaction transaction, Balance balance){
    if (balance.getValue() < transaction.getAmount()){
      throw new IllegalArgumentException("Insufficient funds");
    }
  }

  public TypeEnum getTransactionType(){
    return type;
  }
}
