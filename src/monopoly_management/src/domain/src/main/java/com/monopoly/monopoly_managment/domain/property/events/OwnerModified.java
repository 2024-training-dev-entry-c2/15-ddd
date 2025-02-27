package com.monopoly.monopoly_managment.domain.property.events;

import com.monopoly.shared.domain.generic.DomainEvent;

public class OwnerModified extends DomainEvent {
  private String ownerId;
  private String previousOwnerId;

  public OwnerModified(String ownerId, String previousOwnerId) {
    super(EventsEnum.OWNER_MODIFIED.name() );
    this.ownerId = ownerId;
    this.previousOwnerId = previousOwnerId;
  }

  public OwnerModified() {
    super(EventsEnum.OWNER_MODIFIED.name() );
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }

  public void setPreviousOwnerId(String previousOwnerId) {
    this.previousOwnerId = previousOwnerId;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public String getPreviousOwnerId() {
    return previousOwnerId;
  }
}
