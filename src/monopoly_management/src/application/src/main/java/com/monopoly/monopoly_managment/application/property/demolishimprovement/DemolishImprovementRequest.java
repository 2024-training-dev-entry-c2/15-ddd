package com.monopoly.monopoly_managment.application.property.demolishimprovement;

import com.monopoly.monopoly_managment.domain.property.values.TypeImprovementEnum;
import com.monopoly.shared.application.Request;

public class DemolishImprovementRequest extends Request {
  private final String improvementId;
  private final String propertyId;
  private final TypeImprovementEnum type;
  private final Double cost;

  public DemolishImprovementRequest(String aggregateId, String improvementId, String propertyId, TypeImprovementEnum type, Double cost) {
    super(aggregateId);
    this.improvementId = improvementId;
    this.propertyId = propertyId;
    this.type = type;
    this.cost = cost;
  }

  public String getImprovementId() {
    return improvementId;
  }

  public String getPropertyId() {
    return propertyId;
  }

  public TypeImprovementEnum getType() {
    return type;
  }

  public Double getCost() {
    return cost;
  }
}
