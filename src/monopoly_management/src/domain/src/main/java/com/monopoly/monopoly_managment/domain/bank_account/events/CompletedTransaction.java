package com.monopoly.monopoly_managment.domain.bank_account.events;

import com.monopoly.shared.domain.generic.DomainEvent;

public class CompletedTransaction extends DomainEvent {

  public CompletedTransaction(String name) {
    super(name);
  }
}
