package com.monopoly.monopoly_managment.domain.property.events;

import com.monopoly.monopoly_managment.domain.property.values.TypeImprovementEnum;
import com.monopoly.shared.domain.generic.DomainEvent;

public class DemolishedImprovement extends DomainEvent {
  private String improvementId;
  private TypeImprovementEnum type;
  private Double cost;

  public DemolishedImprovement(String improvementId,TypeImprovementEnum type, Double cost) {
    super(EventsEnum.DEMOLISHED_IMPROVEMENT.name() );
    this.improvementId = improvementId;
    this.type = type;
    this.cost = cost;
  }

  public DemolishedImprovement() {
    super(EventsEnum.DEMOLISHED_IMPROVEMENT.name() );
  }

  public String getImprovementId() {
    return improvementId;
  }

  public TypeImprovementEnum getType() {
    return type;
  }

  public Double getCost() {
    return cost;
  }
}
