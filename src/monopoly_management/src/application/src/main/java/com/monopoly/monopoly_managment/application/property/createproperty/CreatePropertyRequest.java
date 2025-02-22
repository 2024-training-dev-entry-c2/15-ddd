package com.monopoly.monopoly_managment.application.property.createproperty;

import com.monopoly.shared.application.Request;

public class CreatePropertyRequest extends Request {
  private final String contract;
  private final String mortgage;
  private final String owner;
  private final String update;
  private final String name;
  private final Double price;
  private final String colorGroup;

  public CreatePropertyRequest(String contract, String mortgage, String owner, String update, String name, Double price, String colorGroup) {
    super(null);
    this.contract = contract;
    this.mortgage = mortgage;
    this.owner = owner;
    this.update = update;
    this.name = name;
    this.price = price;
    this.colorGroup = colorGroup;
  }

  public String getUpdate() {
    return update;
  }

  public String getContract() {
    return contract;
  }

  public String getMortgage() {
    return mortgage;
  }

  public String getOwner() {
    return owner;
  }

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
