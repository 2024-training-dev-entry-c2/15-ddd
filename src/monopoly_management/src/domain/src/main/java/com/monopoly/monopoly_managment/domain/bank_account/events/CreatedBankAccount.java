package com.monopoly.monopoly_managment.domain.bank_account.events;

import com.monopoly.shared.domain.generic.DomainEvent;

public class CreatedBankAccount extends DomainEvent {
  private String identity;
  private String ownerId;

  public CreatedBankAccount(String identity, String ownerId) {
    super(EventsEnum.CREATED_BANK_ACCOUNT.name());
    this.identity = identity;
    this.ownerId = ownerId;
  }

  public CreatedBankAccount() {
    super(EventsEnum.CREATED_BANK_ACCOUNT.name());
  }

  public String getIdentity() {
    return identity;
  }

  public void setIdentity(String identity) {
    this.identity = identity;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }


}