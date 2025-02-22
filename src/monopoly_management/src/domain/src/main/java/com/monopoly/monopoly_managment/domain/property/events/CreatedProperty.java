package com.monopoly.monopoly_managment.domain.property.events;

import com.monopoly.shared.domain.generic.DomainEvent;

public class CreatedProperty extends DomainEvent {
  private final String contractId;
  private final String mortgageId;
  private final String ownerId;
  private final String improvementsId;
  private final String name;
  private final Double price;
  private final String colorGroup;

  public CreatedProperty(String contractId, String mortgageId, String ownerId, String improvementsId, String name, Double price, String colorGroup) {
    super(EventsEnum.CREATED_PROPERTY.name());
    this.contractId = contractId;
    this.mortgageId = mortgageId;
    this.ownerId = ownerId;
    this.improvementsId = improvementsId;
    this.name = name;
    this.price = price;
    this.colorGroup = colorGroup;
  }

  public String getImprovementsId() {
    return improvementsId;
  }

  public String getContractId() {
    return contractId;
  }

  public String getMortgageId() {
    return mortgageId;
  }

  public String getOwnerId() {
    return ownerId;
  }

  @Override
  public String getName() {
    return name;
  }

  public Double getPrice() {
    return price;
  }

  public String getColorGroup() {
    return colorGroup;
  }
}
