package com.monopoly.monopoly_managment.domain.bank_account.events;

import com.monopoly.monopoly_managment.domain.bank_account.values.Origin;
import com.monopoly.monopoly_managment.domain.bank_account.values.TypeEnum;
import com.monopoly.shared.domain.generic.DomainEvent;

public class CompletedTransaction extends DomainEvent {
  private final String accountId;
  private final String ownerId;
  private final String transactionId;
  private final TypeEnum type;
  private final Double amount;
  private final String origin;
  private final String destiny;

  public CompletedTransaction(String accountId, String ownerId, String transactionId, TypeEnum type, Double amount, String origin, String destiny) {
    super(EventsEnum.COMPLETED_TRANSACTION.name() );
    this.accountId = accountId;
    this.ownerId = ownerId;
    this.transactionId = transactionId;
    this.type = type;
    this.amount = amount;
    this.origin = origin;
    this.destiny = destiny;
  }

  public String getAccountId() {
    return accountId;
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

  public String getOrigin() {
    return origin;
  }
  public String getDestiny() {
    return destiny;
  }
}
