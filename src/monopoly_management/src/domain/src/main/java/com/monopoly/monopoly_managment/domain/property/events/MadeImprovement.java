package com.monopoly.monopoly_managment.domain.property.events;

import com.monopoly.monopoly_managment.domain.property.values.TypeImprovementEnum;
import com.monopoly.shared.domain.generic.DomainEvent;

public class MadeImprovement extends DomainEvent {
  private String improvementId;
  private String propertyId;
  private TypeImprovementEnum type;
  private Double cost;

  public MadeImprovement(String improvementId, String propertyId, TypeImprovementEnum type, Double cost) {
    super(EventsEnum.MADE_IMPROVEMENT.name() );
    this.improvementId = improvementId;
    this.propertyId = propertyId;
    this.type = type;
    this.cost = cost;
  }

  public MadeImprovement(String name) {
    super(name);
  }

  public void setImprovementId(String improvementId) {
    this.improvementId = improvementId;
  }

  public void setPropertyId(String propertyId) {
    this.propertyId = propertyId;
  }

  public void setType(TypeImprovementEnum type) {
    this.type = type;
  }

  public void setCost(Double cost) {
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
