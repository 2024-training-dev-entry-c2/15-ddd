package com.monopoly.monopoly_managment.application.property.makemortgage;

import com.monopoly.shared.application.Request;

public class MakeMortgageRequest extends Request {
  private String mortgageId;
  private String ownerId;
  private Double amount;
  private Boolean mortgaged;

  public MakeMortgageRequest(String mortgageId ,String aggregateId,String ownerId, Double amount) {
    super(aggregateId);
    this.mortgageId = mortgageId;
    this.ownerId = ownerId;
    this.amount = amount;
    this.mortgaged = true;
  }

  public String getMortgageId() {
    return mortgageId;
  }

  public Boolean getMortgaged() {
    return mortgaged;
  }

  public void setMortgaged(Boolean mortgaged) {
    mortgaged = mortgaged;
  }

  public void setMortgageId(String mortgageId) {
    this.mortgageId = mortgageId;
  }

  public MakeMortgageRequest() {
    super(null);
  }

  public String getOwnerId() {
    return ownerId;
  }


  public Double getAmount() {
    return amount;
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }
}
