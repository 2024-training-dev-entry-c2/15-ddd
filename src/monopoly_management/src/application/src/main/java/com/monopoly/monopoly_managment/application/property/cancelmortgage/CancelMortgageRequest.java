package com.monopoly.monopoly_managment.application.property.cancelmortgage;

import com.monopoly.shared.application.Request;

public class CancelMortgageRequest extends Request {
  private final String ownerId;
  private final String propertyId;
  private final Double amount;

  public CancelMortgageRequest(String aggregateId,String ownerId, String propertyId, Double amount) {
    super(aggregateId);
    this.ownerId = ownerId;
    this.propertyId = propertyId;
    this.amount = amount;
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
}
