package com.monopoly.monopoly_managment.domain.property.events;

import com.monopoly.shared.domain.generic.DomainEvent;

public class OwnerAssigned extends DomainEvent {
  private String ownerId;

  public OwnerAssigned(String ownerId) {
    super(EventsEnum.OWNER_ASSIGNED.name() );
    this.ownerId = ownerId;

  }

  public OwnerAssigned() {
    super(EventsEnum.OWNER_ASSIGNED.name() );
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }


  public String getOwnerId() {
    return ownerId;
  }

}
