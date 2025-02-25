package com.monopoly.monopoly_managment.domain.property.events;

import com.monopoly.shared.domain.generic.DomainEvent;

public class OwnerAssigned extends DomainEvent {
  private String ownerId;
  private String propertyId;

  public OwnerAssigned(String ownerId, String propertyId) {
    super(EventsEnum.OWNER_ASSIGNED.name() );
    this.ownerId = ownerId;
    this.propertyId = propertyId;
  }

  public OwnerAssigned() {
    super(EventsEnum.OWNER_ASSIGNED.name() );
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
