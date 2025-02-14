package com.monopoly.monopoly_managment.domain.bank_account.events;

import com.monopoly.shared.domain.generic.DomainEvent;

public class RejectedTransaction extends DomainEvent {

  public RejectedTransaction() {
    super(EventsEnum.REJECTED_TRANSACTION.name());
  }
}
