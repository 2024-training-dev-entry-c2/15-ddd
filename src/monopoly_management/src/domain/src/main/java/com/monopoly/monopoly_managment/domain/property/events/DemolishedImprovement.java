package com.monopoly.monopoly_managment.domain.property.events;

import com.monopoly.shared.domain.generic.DomainEvent;

public class DemolishedImprovement extends DomainEvent {

  public DemolishedImprovement() {
    super(EventsEnum.DEMOLISHED_IMPROVEMENT.name() );
  }
}
