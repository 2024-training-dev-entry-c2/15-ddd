package com.monopoly.monopoly_managment.domain.property.values;

import com.monopoly.shared.domain.generic.IValueObject;
import com.monopoly.shared.domain.utils.Validator;

public class Cost implements IValueObject {
  private final Double cost;
  private final CostEnum costType;

  private Cost(final Double cost, CostEnum costType) {
    this.cost = cost;
    this.costType = costType;
    validate();
  }

  public static Cost of(final Double cost, final CostEnum costType) {
    return new Cost(cost, costType);
  }

  public Double getValue() {
    return cost;
  }

  public CostEnum getCostType() {
    return costType;
  }

  @Override
  public void validate() {
    Validator.validateNegative(this.cost, "Cost value");
    Validator.validateMax(this.cost, 1000000.0, "Cost value");
  }

}
