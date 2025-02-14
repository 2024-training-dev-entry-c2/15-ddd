package com.monopoly.monopoly_managment.domain.property.events;

import com.monopoly.shared.domain.generic.DomainEvent;

public class MortgageConstituted extends DomainEvent {

  public MortgageConstituted() {
    super(EventsEnum.MORTGAGE_CONSTITUTED.name() );
  }
}
