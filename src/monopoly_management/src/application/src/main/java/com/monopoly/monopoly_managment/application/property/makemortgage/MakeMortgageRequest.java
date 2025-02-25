package com.monopoly.monopoly_managment.application.property.makemortgage;

import com.monopoly.shared.application.Request;

public class MakeMortgageRequest extends Request {
  private String ownerId;
  private String propertyId;
  private Double amount;

  public MakeMortgageRequest(String aggregateId,String ownerId, String propertyId, Double amount) {
    super(aggregateId);
    this.ownerId = ownerId;
    this.propertyId = propertyId;
    this.amount = amount;
  }

  public MakeMortgageRequest() {
    super(null);
  }

  public String getOwnerId() {
    return ownerId;
  }

  public String getPropertyId() {
    return propertyId;
  }

  public Double getAmount() {
    return amount;
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }

  public void setPropertyId(String propertyId) {
    this.propertyId = propertyId;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }
}
