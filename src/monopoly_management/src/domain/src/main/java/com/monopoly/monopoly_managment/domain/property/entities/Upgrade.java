package com.monopoly.monopoly_managment.domain.property.entities;

import com.monopoly.monopoly_managment.domain.property.values.Cost;
import com.monopoly.monopoly_managment.domain.property.values.DevelopmentLevel;
import com.monopoly.monopoly_managment.domain.property.values.TypeImprovement;
import com.monopoly.monopoly_managment.domain.property.values.UpgradeId;
import com.monopoly.shared.domain.generic.Entity;

public class Upgrade extends Entity<UpgradeId> {
  private TypeImprovement typeImprovement;
  private DevelopmentLevel developmentLevel;
  private Cost cost;

  protected Upgrade(UpgradeId identity, TypeImprovement typeImprovement, DevelopmentLevel developmentLevel, Cost cost) {
    super(identity);
    this.typeImprovement = typeImprovement;
    this.developmentLevel = developmentLevel;
    this.cost = cost;
  }

  public Upgrade( TypeImprovement typeImprovement, DevelopmentLevel developmentLevel, Cost cost) {
    super(new UpgradeId());
    this.typeImprovement = typeImprovement;
    this.developmentLevel = developmentLevel;
    this.cost = cost;
  }

  public TypeImprovement getTypeImprovement() {
    return typeImprovement;
  }

  public void setTypeImprovement(TypeImprovement typeImprovement) {
    this.typeImprovement = typeImprovement;
  }

  public DevelopmentLevel getDevelopmentLevel() {
    return developmentLevel;
  }

  public void setDevelopmentLevel(DevelopmentLevel developmentLevel) {
    this.developmentLevel = developmentLevel;
  }

  public Cost getCost() {
    return cost;
  }

  public void setCost(Cost cost) {
    this.cost = cost;
  }
}
