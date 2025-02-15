package com.monopoly.monopoly_managment.domain.property.entities;

import com.monopoly.monopoly_managment.domain.property.values.CancellationCost;
import com.monopoly.monopoly_managment.domain.property.values.IsMortgaged;
import com.monopoly.monopoly_managment.domain.property.values.MortgageId;
import com.monopoly.monopoly_managment.domain.property.values.Value;
import com.monopoly.shared.domain.generic.Entity;

public class Mortgage extends Entity<MortgageId> {
  private Value value;
  private IsMortgaged isMortgaged;
  private CancellationCost cancellationCost;

  public Mortgage(MortgageId identity, Value value, IsMortgaged isMortgaged, CancellationCost cancellationCost) {
    super(identity);
    this.value = value;
    this.isMortgaged = isMortgaged;
    this.cancellationCost = cancellationCost;
  }
  public Mortgage( Value value, IsMortgaged isMortgaged, CancellationCost cancellationCost) {
    super(new MortgageId());
    this.value = value;
    this.isMortgaged = isMortgaged;
    this.cancellationCost = cancellationCost;
  }

  public Value getValue() {
    return value;
  }

  public void setValue(Value value) {
    this.value = value;
  }

  public IsMortgaged getIsMortgaged() {
    return isMortgaged;
  }

  public void setIsMortgaged(IsMortgaged isMortgaged) {
    this.isMortgaged = isMortgaged;
  }

  public CancellationCost getCancellationCost() {
    return cancellationCost;
  }

  public void setCancellationCost(CancellationCost cancellationCost) {
    this.cancellationCost = cancellationCost;
  }

  public Double calculateValue(){

  }



}
