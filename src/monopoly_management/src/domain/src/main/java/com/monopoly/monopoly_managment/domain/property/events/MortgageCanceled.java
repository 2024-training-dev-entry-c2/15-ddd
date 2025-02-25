package com.monopoly.monopoly_managment.domain.property.events;

import com.monopoly.shared.domain.generic.DomainEvent;

public class MortgageCanceled extends DomainEvent {
  private String ownerId;
  private String propertyId;
  private Double amount;

  public MortgageCanceled(String ownerId, String propertyId, Double amount) {
    super(EventsEnum.MORTGAGE_CANCELLED.name() );
    this.ownerId = ownerId;
    this.propertyId = propertyId;
    this.amount = amount;
  }

  public MortgageCanceled() {
    super(EventsEnum.MORTGAGE_CANCELLED.name() );
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
