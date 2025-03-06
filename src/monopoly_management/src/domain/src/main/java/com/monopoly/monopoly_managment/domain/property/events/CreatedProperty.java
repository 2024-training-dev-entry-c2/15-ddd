package com.monopoly.monopoly_managment.domain.property.events;

import com.monopoly.shared.domain.generic.DomainEvent;

public class CreatedProperty extends DomainEvent {
  private String contractId;
  private String mortgageId;
  private String ownerId;
  private String improvementsId;
  private String title;
  private Double price;
  private String colorGroup;

  public CreatedProperty(String contractId, String mortgageId, String ownerId, String improvementsId, String title, Double price, String colorGroup) {
    super(EventsEnum.CREATED_PROPERTY.name());
    this.contractId = contractId;
    this.mortgageId = mortgageId;
    this.ownerId = ownerId;
    this.improvementsId = improvementsId;
    this.title = title;
    this.price = price;
    this.colorGroup = colorGroup;
  }

  public CreatedProperty() {
    super( EventsEnum.CREATED_PROPERTY.name() );
  }

  public CreatedProperty(String name) {
    super(name);
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public void setMortgageId(String mortgageId) {
    this.mortgageId = mortgageId;
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }

  public void setImprovementsId(String improvementsId) {
    this.improvementsId = improvementsId;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public void setColorGroup(String colorGroup) {
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
  public String getTitle() {
    return title;
  }

  public Double getPrice() {
    return price;
  }

  public String getColorGroup() {
    return colorGroup;
  }
}
