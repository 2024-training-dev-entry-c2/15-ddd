package com.monopoly.monopoly_managment.domain.property.events;

import com.monopoly.shared.domain.generic.DomainEvent;

public class MortgageCanceled extends DomainEvent {
  private final String ownerId;
  private final String propertyId;
  private final Double amount;

  public MortgageCanceled(String ownerId, String propertyId, Double amount) {
    super(EventsEnum.MORTGAGE_CANCELLED.name() );
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
