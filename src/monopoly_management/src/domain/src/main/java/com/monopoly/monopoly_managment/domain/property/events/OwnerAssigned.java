package com.monopoly.monopoly_managment.domain.property.events;

import com.monopoly.shared.domain.generic.DomainEvent;

public class OwnerAssigned extends DomainEvent {
  private final String ownerId;
  private final String propertyId;

  public OwnerAssigned(String ownerId, String propertyId) {
    super(EventsEnum.OWNER_ASSIGNED.name() );
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
