package com.monopoly.monopoly_managment.domain.property.events;

import com.monopoly.monopoly_managment.domain.property.values.TypeImprovementEnum;
import com.monopoly.shared.domain.generic.DomainEvent;

public class MadeImprovement extends DomainEvent {
  private final String improvementId;
  private final String propertyId;
  private final TypeImprovementEnum type;
  private final Double cost;

  public MadeImprovement(String improvementId, String propertyId, TypeImprovementEnum type, Double cost) {
    super(EventsEnum.MADE_IMPROVEMENT.name() );
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
