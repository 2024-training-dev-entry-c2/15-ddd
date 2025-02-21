package com.monopoly.monopoly_managment.application.banck_account.validatefounds;

import com.monopoly.monopoly_managment.domain.bank_account.values.TypeEnum;
import com.monopoly.shared.application.Request;

public class ValidateFoundsRequest extends Request {
  private final String transactionId;
  private final Double amount;
  private final TypeEnum type;
  private final String ownerId;

  public ValidateFoundsRequest(String aggregateId, String transactionId, Double amount, TypeEnum type, String ownerId) {
    super(aggregateId);
    this.transactionId = transactionId;
    this.amount = amount;
    this.type = type;
    this.ownerId = ownerId;
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
}
