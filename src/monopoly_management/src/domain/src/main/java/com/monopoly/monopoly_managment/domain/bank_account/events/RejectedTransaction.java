package com.monopoly.monopoly_managment.domain.bank_account.events;

import com.monopoly.monopoly_managment.domain.bank_account.values.TypeEnum;
import com.monopoly.shared.domain.generic.DomainEvent;

public class RejectedTransaction extends DomainEvent {
  private String ownerId;
  private String transactionId;
  private TypeEnum type;
  private Double amount;
  private String origin;
  private String destiny;

  public RejectedTransaction(String ownerId, String transactionId, TypeEnum type, Double amount, String origin, String destiny) {
    super(EventsEnum.REJECTED_TRANSACTION.name() );
    this.ownerId = ownerId;
    this.transactionId = transactionId;
    this.type = type;
    this.amount = amount;
    this.origin = origin;
    this.destiny = destiny;
  }

  public RejectedTransaction(String name) {
    super(name);
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
