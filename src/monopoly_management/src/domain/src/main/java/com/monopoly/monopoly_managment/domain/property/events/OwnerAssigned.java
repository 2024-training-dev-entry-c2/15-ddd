package com.monopoly.monopoly_managment.domain.property.events;

import com.monopoly.shared.domain.generic.DomainEvent;

public class OwnerAssigned extends DomainEvent {

  public OwnerAssigned() {
    super(EventsEnum.OWNER_ASSIGNED.name() );
  }
}
