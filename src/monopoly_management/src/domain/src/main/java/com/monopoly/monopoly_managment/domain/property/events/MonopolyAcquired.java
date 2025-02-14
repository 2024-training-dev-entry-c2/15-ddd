package com.monopoly.monopoly_managment.domain.property.events;

import com.monopoly.shared.domain.generic.DomainEvent;

public class MonopolyAcquired extends DomainEvent {

  public MonopolyAcquired() {
    super(EventsEnum.MONOPOLY_ACQUIRED.name() );
  }
}
