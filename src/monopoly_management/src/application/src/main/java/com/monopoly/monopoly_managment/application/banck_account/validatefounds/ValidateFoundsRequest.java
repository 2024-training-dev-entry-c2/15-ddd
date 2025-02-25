package com.monopoly.monopoly_managment.application.banck_account.validatefounds;

import com.monopoly.monopoly_managment.domain.bank_account.values.TypeEnum;
import com.monopoly.shared.application.Request;

public class ValidateFoundsRequest extends Request {
  private String transactionId;
  private Double amount;
  private TypeEnum type;
  private String ownerId;

  public ValidateFoundsRequest(String aggregateId, String transactionId, Double amount, TypeEnum type, String ownerId) {
    super(aggregateId);
    this.transactionId = transactionId;
    this.amount = amount;
    this.type = type;
    this.ownerId = ownerId;
  }

  public ValidateFoundsRequest() {
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
