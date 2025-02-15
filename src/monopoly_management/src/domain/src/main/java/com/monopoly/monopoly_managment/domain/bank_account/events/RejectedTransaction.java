package com.monopoly.monopoly_managment.domain.bank_account.events;

import com.monopoly.monopoly_managment.domain.bank_account.values.TypeEnum;
import com.monopoly.shared.domain.generic.DomainEvent;

public class RejectedTransaction extends DomainEvent {

  private final String ownerId;
  private final String transactionId;
  private final TypeEnum type;
  private final Double amount;

  public RejectedTransaction(String ownerId, String transactionId, TypeEnum type, Double amount) {
    super(EventsEnum.REJECTED_TRANSACTION.name() );
    this.ownerId = ownerId;
    this.transactionId = transactionId;
    this.type = type;
    this.amount = amount;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public TypeEnum getType() {
    return type;
  }

  public Double getAmount() {
    return amount;
  }
}
