package com.monopoly.monopoly_managment.application.property.createproperty;

import com.monopoly.shared.application.Request;

public class CreatePropertyRequest extends Request {
  private String contract;
  private String mortgage;
  private String owner;
  private String update;
  private String title;
  private Double price;
  private String colorGroup;

  public CreatePropertyRequest(String contract, String mortgage, String owner, String update, String name, Double price, String colorGroup) {
    super(null);
    this.contract = contract;
    this.mortgage = mortgage;
    this.owner = owner;
    this.update = update;
    this.title = name;
    this.price = price;
    this.colorGroup = colorGroup;
  }

  public CreatePropertyRequest() {
    super(null);
  }

  public void setContract(String contract) {
    this.contract = contract;
  }

  public void setMortgage(String mortgage) {
    this.mortgage = mortgage;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public void setUpdate(String update) {
    this.update = update;
  }

  public void setName(String title) {
    this.title = title;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public void setColorGroup(String colorGroup) {
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

  public String getTitle() {
    return title;
  }

  public Double getPrice() {
    return price;
  }

  public String getColorGroup() {
    return colorGroup;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
