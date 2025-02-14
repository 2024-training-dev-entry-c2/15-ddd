package com.monopoly.monopoly_managment.domain.property.events;

import com.monopoly.shared.domain.generic.DomainEvent;

public class ContractFinalized extends DomainEvent {

  public ContractFinalized() {
    super(EventsEnum.CONTRACT_FINALIZED.name() );
  }
}
