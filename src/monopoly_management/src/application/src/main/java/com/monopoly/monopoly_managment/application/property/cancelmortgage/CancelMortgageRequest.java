package com.monopoly.monopoly_managment.application.property.cancelmortgage;

import com.monopoly.shared.application.Request;

public class CancelMortgageRequest extends Request {
  private String ownerId;
  private String propertyId;
  private Double amount;

  public CancelMortgageRequest(String aggregateId,String ownerId, String propertyId, Double amount) {
    super(aggregateId);
    this.ownerId = ownerId;
    this.propertyId = propertyId;
    this.amount = amount;
  }

  public CancelMortgageRequest() {
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
