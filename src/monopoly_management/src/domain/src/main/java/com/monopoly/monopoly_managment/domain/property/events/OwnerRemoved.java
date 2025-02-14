package com.monopoly.monopoly_managment.domain.property.events;

import com.monopoly.shared.domain.generic.DomainEvent;

public class OwnerRemoved extends DomainEvent {

  public OwnerRemoved() {
    super(EventsEnum.OWNER_REMOVED.name() );
  }
}
