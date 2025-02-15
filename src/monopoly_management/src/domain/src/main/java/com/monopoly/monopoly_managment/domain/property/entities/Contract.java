package com.monopoly.monopoly_managment.domain.property.entities;

import com.monopoly.monopoly_managment.domain.property.values.ContractId;
import com.monopoly.monopoly_managment.domain.property.values.Parties;
import com.monopoly.monopoly_managment.domain.property.values.Rate;
import com.monopoly.monopoly_managment.domain.property.values.TypeContrat;
import com.monopoly.shared.domain.generic.Entity;

public class Contract extends Entity<ContractId> {
  private TypeContrat typeContrat;
  private Rate rate;
  private Parties parties;

  public Contract(ContractId identity, TypeContrat typeContrat, Rate rate, Parties parties) {
    super(identity);
    this.typeContrat = typeContrat;
    this.rate = rate;
    this.parties = parties;
  }

  public Contract(TypeContrat typeContrat, Rate rate, Parties parties) {
    super(new ContractId());
    this.typeContrat = typeContrat;
    this.rate = rate;
    this.parties = parties;
  }

  public TypeContrat getTypeContrat() {
    return typeContrat;
  }

  public void setTypeContrat(TypeContrat typeContrat) {
    this.typeContrat = typeContrat;
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

  }

  public void cancel(){

  }

  public Double calculateRentalRate(){

  }


}
