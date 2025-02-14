package com.monopoly.monopoly_managment.domain.property.events;

import com.monopoly.shared.domain.generic.DomainEvent;

public class OwnerModified extends DomainEvent {

  public OwnerModified() {
    super(EventsEnum.OWNER_MODIFIED.name() );
  }
}
