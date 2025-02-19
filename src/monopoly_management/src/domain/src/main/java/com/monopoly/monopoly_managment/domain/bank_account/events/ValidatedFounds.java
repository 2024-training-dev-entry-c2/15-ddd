package com.monopoly.monopoly_managment.domain.bank_account.events;

import com.monopoly.monopoly_managment.domain.bank_account.values.TypeEnum;
import com.monopoly.shared.domain.generic.DomainEvent;

public class ValidatedFounds extends DomainEvent {
  private final String transactionId;
  private final Double amount;
  private final TypeEnum type;

  public ValidatedFounds(String transactionId, Double amount, TypeEnum type) {
    super(EventsEnum.VALIDATED_FOUNDS.name());
    this.transactionId = transactionId;
    this.amount = amount;
    this.type = type;
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
}