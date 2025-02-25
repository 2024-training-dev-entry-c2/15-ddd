package com.monopoly.monopoly_managment.domain.property.events;

import com.monopoly.shared.domain.generic.DomainEvent;

public class OwnerRemoved extends DomainEvent {

  private String ownerId;
  private String propertyId;

  public OwnerRemoved(String ownerId, String propertyId) {
    super(EventsEnum.OWNER_REMOVED.name() );
    this.ownerId = ownerId;
    this.propertyId = propertyId;
  }

  public OwnerRemoved() {
    super(EventsEnum.OWNER_REMOVED.name() );
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }

  public void setPropertyId(String propertyId) {
    this.propertyId = propertyId;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public String getPropertyId() {
    return propertyId;
  }
}
