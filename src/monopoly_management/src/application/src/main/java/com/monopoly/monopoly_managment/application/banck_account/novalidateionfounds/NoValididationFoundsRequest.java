package com.monopoly.monopoly_managment.application.banck_account.novalidateionfounds;

import com.monopoly.monopoly_managment.domain.bank_account.values.TypeEnum;
import com.monopoly.shared.application.Request;

public class NoValididationFoundsRequest extends Request {
  private String transactionId;
  private Double amount;
  private TypeEnum type;
  private String ownerId;

  public NoValididationFoundsRequest(String aggregateId, String transactionId, Double amount, TypeEnum type, String ownerId) {
    super(aggregateId);
    this.transactionId = transactionId;
    this.amount = amount;
    this.type = type;
    this.ownerId = ownerId;
  }

  public NoValididationFoundsRequest() {
    super(null);
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

  public String getOwnerId() {
    return ownerId;
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

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }
}
