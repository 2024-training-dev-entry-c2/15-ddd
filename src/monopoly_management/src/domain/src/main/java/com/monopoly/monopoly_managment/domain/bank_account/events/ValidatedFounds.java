package com.monopoly.monopoly_managment.domain.bank_account.events;

import com.monopoly.monopoly_managment.domain.bank_account.values.TypeEnum;
import com.monopoly.shared.domain.generic.DomainEvent;

public class ValidatedFounds extends DomainEvent {
  private final String accountId;
  private final Double amount;
  private final TypeEnum type;

  public ValidatedFounds(String accountId, Double amount, TypeEnum type) {
    super(EventsEnum.VALIDATED_FOUNDS.name());
    this.accountId = accountId;
    this.amount = amount;
    this.type = type;
  }

  public String getAccountId() {
    return accountId;
  }

  public Double getAmount() {
    return amount;
  }

  public TypeEnum getType() {
    return type;
  }
}
