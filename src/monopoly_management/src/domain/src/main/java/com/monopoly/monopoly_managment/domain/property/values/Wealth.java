package com.monopoly.monopoly_managment.domain.property.values;

import com.monopoly.shared.domain.generic.IValueObject;
import com.monopoly.shared.domain.utils.Validator;

import java.util.List;

public class Wealth implements IValueObject {
  private final Double balance;
  private final List<String> propertiesIds;

  private Wealth(final Double balance, List<String> propertiesIds) {
    this.balance = balance;
    this.propertiesIds = propertiesIds;
  }

  public static Wealth of(final Double value, final List<String> propertiesIds) {
    return new Wealth(value, propertiesIds);
  }

  public List<String> getPropertiesIds() {
    return propertiesIds;
  }

  public Double getBalance() {
    return balance;
  }

  @Override
  public void validate() {
    Validator.validateNegative(this.balance, "Wealth");
    Validator.validateMax(this.balance, 1000000.0, "Wealth");
  }
}
