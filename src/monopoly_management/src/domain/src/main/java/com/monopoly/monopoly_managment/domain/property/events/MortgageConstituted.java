package com.monopoly.monopoly_managment.domain.property.events;

import com.monopoly.shared.domain.generic.DomainEvent;

public class MortgageConstituted extends DomainEvent {
  private String mortgageId;
  private String ownerId;
  private Double amount;
  private Boolean mortgaged;

  public MortgageConstituted(String mortgageId ,String ownerId,Double amount, Boolean mortgaged) {
    super(EventsEnum.MORTGAGE_CONSTITUTED.name() );
    this.mortgageId = mortgageId;
    this.ownerId = ownerId;
    this.amount = amount;
    this.mortgaged = mortgaged;
  }

  public Boolean getMortgaged() {
    return mortgaged;
  }

  public void setMortgaged(Boolean mortgaged) {
    this.mortgaged = mortgaged;
  }

  public String getMortgageId() {
    return mortgageId;
  }

  public void setMortgageId(String mortgageId) {
    this.mortgageId = mortgageId;
  }

  public MortgageConstituted() {
    super(EventsEnum.MORTGAGE_CONSTITUTED.name() );
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public String getOwnerId() {
    return ownerId;
  }


  public Double getAmount() {
    return amount;
  }
}
