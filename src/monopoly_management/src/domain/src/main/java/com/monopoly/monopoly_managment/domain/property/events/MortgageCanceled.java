package com.monopoly.monopoly_managment.domain.property.events;

import com.monopoly.shared.domain.generic.DomainEvent;

public class MortgageCanceled extends DomainEvent {

  public MortgageCanceled() {
    super(EventsEnum.MORTGAGE_CANCELLED.name() );
  }
}
