package com.monopoly.monopoly_managment.domain.bank_account.events;

import com.monopoly.monopoly_managment.domain.bank_account.values.Origin;
import com.monopoly.monopoly_managment.domain.bank_account.values.TypeEnum;
import com.monopoly.shared.domain.generic.DomainEvent;

public class CompletedTransaction extends DomainEvent {
  private String accountId;
  private String ownerId;
  private String transactionId;
  private TypeEnum type;
  private Double amount;
  private String origin;
  private String destiny;

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

  public CompletedTransaction() {
    super(EventsEnum.COMPLETED_TRANSACTION.name() );
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

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public void setDestiny(String destiny) {
    this.destiny = destiny;
  }
}
