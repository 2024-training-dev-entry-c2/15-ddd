package com.monopoly.monopoly_managment.domain.property.events;

import com.monopoly.shared.domain.generic.DomainEvent;

public class MadeImprovement extends DomainEvent {

  public MadeImprovement() {
    super(EventsEnum.MADE_IMPROVEMENT.name() );
  }
}
