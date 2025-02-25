package com.monopoly.monopoly_managment.application.banck_account.canceltransaction;

import com.monopoly.monopoly_managment.domain.bank_account.values.TypeEnum;
import com.monopoly.shared.application.Request;

public class CancelTransactionRequest extends Request {
  private String ownerId;
  private String transactionId;
  private TypeEnum type;
  private Double amount;
  private String origin;
  private String destiny;

  public CancelTransactionRequest(String aggregateId, String ownerId, String transactionId, TypeEnum type, Double amount, String origin, String destiny) {
    super(aggregateId);
    this.ownerId = ownerId;
    this.transactionId = transactionId;
    this.type = type;
    this.amount = amount;
    this.origin = origin;
    this.destiny = destiny;
  }

  public CancelTransactionRequest() {
    super(null);
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
