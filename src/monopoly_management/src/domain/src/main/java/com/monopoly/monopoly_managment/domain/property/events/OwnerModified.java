package com.monopoly.monopoly_managment.domain.property.events;

import com.monopoly.shared.domain.generic.DomainEvent;

public class OwnerModified extends DomainEvent {
  private String ownerId;
  private String propertyId;
  private String previousOwnerId;

  public OwnerModified(String ownerId, String propertyId, String previousOwnerId) {
    super(EventsEnum.OWNER_MODIFIED.name() );
    this.ownerId = ownerId;
    this.propertyId = propertyId;
    this.previousOwnerId = previousOwnerId;
  }

  public OwnerModified(String name) {
    super(name);
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }

  public void setPropertyId(String propertyId) {
    this.propertyId = propertyId;
  }

  public void setPreviousOwnerId(String previousOwnerId) {
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
