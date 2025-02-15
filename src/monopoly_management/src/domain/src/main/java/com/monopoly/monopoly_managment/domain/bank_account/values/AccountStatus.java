package com.monopoly.monopoly_managment.domain.bank_account.values;

import com.monopoly.shared.domain.generic.IValueObject;
import com.monopoly.shared.domain.utils.Validator;

public class AccountStatus implements IValueObject {
  private final AccountStatusEnum value;

  private AccountStatus(final AccountStatusEnum value) {
    this.value = value;
    validate();
  }

  public static AccountStatus of(final AccountStatusEnum value) {
    return new AccountStatus(value);
  }

  public AccountStatusEnum getValue() {
    return value;
  }

  @Override
  public void validate() {
    Validator.validateEnum(AccountStatusEnum.class, this.value, "AccountStatus value");
  }
}
