package com.monopoly.monopoly_managment.application.banck_account.registertransaction;

import com.monopoly.monopoly_managment.domain.bank_account.values.TypeEnum;
import com.monopoly.shared.application.Request;

public class CreateTransactionRequest extends Request {
  private Double amount;
  private TypeEnum type;
  private String destiny;
  private String origin;

  public CreateTransactionRequest(String aggregateId, Double amount, TypeEnum type, String destiny, String origin) {
    super(aggregateId);
    this.amount = amount;
    this.type = type;
    this.destiny = destiny;
    this.origin = origin;
  }

  public CreateTransactionRequest(){
    super(null);
  }

  public Double getAmount() {
    return amount;
  }

  public TypeEnum getType() {
    return type;
  }

  public String getDestiny() {
    return destiny;
  }

  public String getOrigin() {
    return origin;
  }
}
