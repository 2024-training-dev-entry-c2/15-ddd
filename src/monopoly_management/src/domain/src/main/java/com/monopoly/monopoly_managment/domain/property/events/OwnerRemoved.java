package com.monopoly.monopoly_managment.domain.property.events;

import com.monopoly.shared.domain.generic.DomainEvent;

public class OwnerRemoved extends DomainEvent {

  private final String ownerId;
  private final String propertyId;

  public OwnerRemoved(String ownerId, String propertyId) {
    super(EventsEnum.OWNER_REMOVED.name() );
    this.ownerId = ownerId;
    this.propertyId = propertyId;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public String getPropertyId() {
    return propertyId;
  }
}
