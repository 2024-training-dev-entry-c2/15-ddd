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

  public void activate() {
    if (getIsMortgaged().getValue()) {
      throw new IllegalStateException("Mortgage is already active");
    }
    this.isMortgaged = IsMortgaged.of(true);
  }

  public void cancel() {
    setIsMortgaged(IsMortgaged.of(false));
  }

  public Double calculateCancellationCost() {
    if (!getIsMortgaged().getValue()) {
      throw new IllegalStateException("Mortgage is not active");
    }
    return getValue().getValue() * 2.5;
  }
}
