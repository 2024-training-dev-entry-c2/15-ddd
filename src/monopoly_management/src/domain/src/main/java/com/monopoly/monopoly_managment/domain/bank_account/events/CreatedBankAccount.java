package com.monopoly.monopoly_managment.domain.bank_account.events;

import com.monopoly.shared.domain.generic.DomainEvent;

public class CreatedBankAccount extends DomainEvent {
  private final String bankAccountId;
  private final String ownerId;

  public CreatedBankAccount(String bankAccountId, String ownerId) {
    super(EventsEnum.CREATED_BANK_ACCOUNT.name());
    this.bankAccountId = bankAccountId;
    this.ownerId = ownerId;
  }

  public String getBankAccountId() {
    return bankAccountId;
  }

  public String getOwnerId() {
    return ownerId;
  }
}
