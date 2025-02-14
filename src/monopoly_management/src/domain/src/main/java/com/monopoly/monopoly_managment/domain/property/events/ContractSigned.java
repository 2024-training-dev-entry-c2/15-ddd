package com.monopoly.monopoly_managment.domain.property.events;

import com.monopoly.shared.domain.generic.DomainEvent;

public class ContractSigned extends DomainEvent {

  public ContractSigned() {
    super(EventsEnum.CONTRACT_SIGNED.name() );
  }
}
