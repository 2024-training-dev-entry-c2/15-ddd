package com.monopoly.monopoly_managment.domain.property.events;

import com.monopoly.shared.domain.generic.DomainEvent;

public class OwnerModified extends DomainEvent {
  private final String ownerId;
  private final String propertyId;
  private final String previousOwnerId;

  public OwnerModified(String ownerId, String propertyId, String previousOwnerId) {
    super(EventsEnum.OWNER_MODIFIED.name() );
    this.ownerId = ownerId;
    this.propertyId = propertyId;
    this.previousOwnerId = previousOwnerId;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public String getPropertyId() {
    return propertyId;
  }

  public String getPreviousOwnerId() {
    return previousOwnerId;
  }
}
