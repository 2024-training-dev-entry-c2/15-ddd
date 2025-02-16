package com.monopoly.monopoly_managment.domain.property.entities;

import com.monopoly.monopoly_managment.domain.property.values.Cost;
import com.monopoly.monopoly_managment.domain.property.values.DevelopmentLevel;
import com.monopoly.monopoly_managment.domain.property.values.PropertyId;
import com.monopoly.monopoly_managment.domain.property.values.TypeImprovement;
import com.monopoly.monopoly_managment.domain.property.values.TypeImprovementEnum;
import com.monopoly.monopoly_managment.domain.property.values.UpgradeId;
import com.monopoly.shared.domain.generic.Entity;

public class Upgrade extends Entity<UpgradeId> {
  private TypeImprovement typeImprovement;
  private DevelopmentLevel developmentLevel;
  private PropertyId propertyId;
  private Cost cost;

  protected Upgrade(UpgradeId identity, TypeImprovement typeImprovement, DevelopmentLevel developmentLevel, Cost cost, PropertyId propertyId) {
    super(identity);
    this.typeImprovement = typeImprovement;
    this.developmentLevel = developmentLevel;
    this.propertyId = propertyId;
    this.cost = cost;
  }

  public Upgrade( TypeImprovement typeImprovement, DevelopmentLevel developmentLevel, PropertyId propertyId, Cost cost) {
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

  public PropertyId getPropertyId() {
    return propertyId;
  }

  public void setPropertyId(PropertyId propertyId) {
    this.propertyId = propertyId;
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

  public void build(){
    if (this.developmentLevel.getValue() == 8) {
      throw new RuntimeException("The property is already at its maximum level");
    }
    validateLevel();
    this.developmentLevel = DevelopmentLevel.of(this.developmentLevel.getValue() + 1);
  }

  public void downgrade(){
    if (this.developmentLevel.getValue() == 0) {
      throw new RuntimeException("The property is already at its minimum level");
    }
    validateLevel();
    this.developmentLevel = DevelopmentLevel.of(this.developmentLevel.getValue() - 1);
  }

  public void updateTye(TypeImprovementEnum type){
    this.typeImprovement = TypeImprovement.of(type);
  }

  private void validateLevel(){
    if (this.developmentLevel.getValue() > 4){
      updateTye(TypeImprovementEnum.HOTEL);
    }
    else {
      updateTye(TypeImprovementEnum.HOUSE);
    }
  }
}
