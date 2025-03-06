package com.monopoly.monopoly_managment.domain.bank_account.events;

import com.monopoly.monopoly_managment.domain.bank_account.values.TypeEnum;
import com.monopoly.shared.domain.generic.DomainEvent;

public class ValidatedFounds extends DomainEvent {
  private String transactionId;
  private Double amount;
  private TypeEnum type;

  public ValidatedFounds(String transactionId, Double amount, TypeEnum type) {
    super(EventsEnum.VALIDATED_FOUNDS.name());
    this.transactionId = transactionId;
    this.amount = amount;
    this.type = type;
  }

  public ValidatedFounds() {
    super(EventsEnum.VALIDATED_FOUNDS.name());
  }

  public String getTransactionId() {
    return transactionId;
  }

  public Double getAmount() {
    return amount;
  }

  public TypeEnum getType() {
    return type;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }
}