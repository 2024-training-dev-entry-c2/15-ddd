package com.monopoly.monopoly_managment.domain.property.entities;

import com.monopoly.monopoly_managment.domain.property.Property;
import com.monopoly.monopoly_managment.domain.property.values.ContractId;
import com.monopoly.monopoly_managment.domain.property.values.IsActive;
import com.monopoly.monopoly_managment.domain.property.values.Parties;
import com.monopoly.monopoly_managment.domain.property.values.Rate;
import com.monopoly.monopoly_managment.domain.property.values.TypeContrat;
import com.monopoly.shared.domain.generic.Entity;
import com.monopoly.shared.domain.utils.Validator;

import java.util.Objects;

public class Contract extends Entity<ContractId> {
  private TypeContrat type;
  private Rate rate;
  private Parties parties;
  private IsActive isActive;

  public Contract(ContractId identity, TypeContrat type, Rate rate, Parties parties, IsActive isActive) {
    super(identity);
    this.type = Objects.requireNonNull(type, "Type is required");
    this.rate = Objects.requireNonNull(rate, "Rate is required");
    this.parties = Objects.requireNonNull(parties, "Parties is required");
    this.isActive = Objects.requireNonNull(isActive, "Is active is required");
    validate();
  }

  public Contract(TypeContrat type, Rate rate, Parties parties, IsActive isActive) {
    super(new ContractId());
    this.type = Objects.requireNonNull(type, "Type is required");
    this.rate = Objects.requireNonNull(rate, "Rate is required");
    this.parties = Objects.requireNonNull(parties, "Parties is required");
    this.isActive = Objects.requireNonNull(isActive, "Is active is required");
    validate();
  }

  public TypeContrat getType() {
    return type;
  }

  public void setType(TypeContrat typeContrat) {
    this.type = typeContrat;
  }

  public Rate getRate() {
    return rate;
  }

  public void setRate(Rate rate) {
    this.rate = rate;
  }

  public Parties getParties() {
    return parties;
  }

  public void setParties(Parties parties) {
    this.parties = parties;
  }

  public void sign(){
  if (this.isActive.getValue()){
    throw new IllegalStateException("Contract is already signed");
  }
  else {
    this.isActive = IsActive.of(true);
  }
  }

  public void cancel(){
    if (!this.isActive.getValue()){
      throw new IllegalStateException("Contract is already canceled");
    }
    else {
      this.isActive = IsActive.of(false);
    }
  }

//  public Double calculateRentalRate(Property property){
//   return rate.getValue().ad
//  }

  private void validate(){
    Validator.validateNegative(rate.getValue(), "Rate");
  }
}
