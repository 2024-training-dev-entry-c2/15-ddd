package com.monopoly.monopoly_managment.domain.bank_account.events;

import com.monopoly.shared.domain.generic.DomainEvent;

public class ObtainedBalance extends DomainEvent {

  private final String accountId;
  private final Double amount;

  public ObtainedBalance(String accountId, Double amount) {
    super(EventsEnum.OBTAINED_BALANCE.name() );
    this.accountId = accountId;
    this.amount = amount;
  }

  public String getAccountId() {
    return accountId;
  }

  public Double getAmount() {
    return amount;
  }
}
