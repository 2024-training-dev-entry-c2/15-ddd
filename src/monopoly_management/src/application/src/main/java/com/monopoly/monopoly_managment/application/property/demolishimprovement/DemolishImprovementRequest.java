package com.monopoly.monopoly_managment.application.property.demolishimprovement;

import com.monopoly.monopoly_managment.domain.property.values.TypeImprovementEnum;
import com.monopoly.shared.application.Request;

public class DemolishImprovementRequest extends Request {
  private String improvementId;
  private TypeImprovementEnum type;
  private Double cost;

  public DemolishImprovementRequest(String aggregateId, String improvementId, TypeImprovementEnum type, Double cost) {
    super(aggregateId);
    this.improvementId = improvementId;
    this.type = type;
    this.cost = cost;
  }

  public DemolishImprovementRequest() {
    super(null);
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

  public void setImprovementId(String improvementId) {
    this.improvementId = improvementId;
  }

  public void setType(TypeImprovementEnum type) {
    this.type = type;
  }

  public void setCost(Double cost) {
    this.cost = cost;
  }
}
