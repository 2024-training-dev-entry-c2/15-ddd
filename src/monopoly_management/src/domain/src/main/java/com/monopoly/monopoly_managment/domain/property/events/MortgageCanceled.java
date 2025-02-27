package com.monopoly.monopoly_managment.domain.property.events;

import com.monopoly.shared.domain.generic.DomainEvent;

public class MortgageCanceled extends DomainEvent {
  private String ownerId;
  private Double amount;

  public MortgageCanceled(String ownerId, Double amount) {
    super(EventsEnum.MORTGAGE_CANCELLED.name() );
    this.ownerId = ownerId;
    this.amount = amount;
  }

  public MortgageCanceled() {
    super(EventsEnum.MORTGAGE_CANCELLED.name() );
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public String getOwnerId() {
    return ownerId;
  }


  public Double getAmount() {
    return amount;
  }
}
