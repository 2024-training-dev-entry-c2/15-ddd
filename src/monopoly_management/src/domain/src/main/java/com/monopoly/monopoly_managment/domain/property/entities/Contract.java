package com.monopoly.monopoly_managment.domain.property.entities;

import com.monopoly.monopoly_managment.domain.property.values.ContractId;
import com.monopoly.monopoly_managment.domain.property.values.IsActive;
import com.monopoly.monopoly_managment.domain.property.values.OwnerId;
import com.monopoly.monopoly_managment.domain.property.values.Parties;
import com.monopoly.monopoly_managment.domain.property.values.Rate;
import com.monopoly.monopoly_managment.domain.property.values.TypeContrat;
import com.monopoly.shared.domain.generic.Entity;

public class Contract extends Entity<ContractId> {
  private TypeContrat type;
  private Rate rate;
  private Parties parties;
  private IsActive isActive;

  public Contract(ContractId identity, TypeContrat type, Rate rate, Parties parties, IsActive isActive) {
    super(identity);
    this.type = type;
    this.rate = rate;
    this.parties = parties;
    this.isActive = isActive;
  }

  public Contract(TypeContrat type, Rate rate, Parties parties, IsActive isActive) {
    super(new ContractId());
    this.type = type;
    this.rate = rate;
    this.parties = parties;
    this.isActive = isActive;
  }

  public TypeContrat getType() {
    return type;
  }

  public IsActive getIsActive() {
    return isActive;
  }

  public void setIsActive(IsActive isActive) {
    this.isActive = isActive;
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

  public void sign(String ownerId){
    if (getIsActive().getValue()){
      throw new IllegalStateException("Contract is already signed");
    }
    else {
      this.isActive = IsActive.of(true);
      this.parties = Parties.of(ownerId);
    }
  }

  public void cancel(){
    if (!getIsActive().getValue()){
      throw new IllegalStateException("Contract is already canceled");
    }
    else {
      this.isActive = IsActive.of(false);
      this.parties = Parties.of(null);
    }
  }

  public Double calculateRent(){
    Integer improvementsLevel = getImprovementsLevel();
    if (improvementsLevel > 3){
      return calculateRentWithImprovements(3, rate.getBase());
    }
    else {
      return calculateRentWithImprovements(improvementsLevel, rate.getBase());
    }
  }

  public void removeTenant(OwnerId tenantId){
    if ( !getParties().getTenantId().contains(tenantId)){
      throw new IllegalStateException("Tenant is not vinculated");
    }
    else {
      this.parties.getTenantId().remove(tenantId);
    }
  }

  private void addTenant(OwnerId tenantId){
    if ( getParties().getTenantId().contains(tenantId)){
      throw new IllegalStateException("Tenant is already vinculated");
    }
    else {
      this.parties.getTenantId().add(tenantId);
    }
  }

  private Integer getImprovementsLevel(){
    return 1;
  }

  private Double calculateRentWithImprovements(Integer improvementsLevel, Double rate){
    return improvementsLevel * rate;
  }
}
