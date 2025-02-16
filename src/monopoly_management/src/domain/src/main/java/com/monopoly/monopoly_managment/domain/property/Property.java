package com.monopoly.monopoly_managment.domain.property;

import com.monopoly.monopoly_managment.domain.bank_account.values.BankAccountId;
import com.monopoly.monopoly_managment.domain.property.entities.Contract;
import com.monopoly.monopoly_managment.domain.property.entities.Mortgage;
import com.monopoly.monopoly_managment.domain.property.entities.Owner;
import com.monopoly.monopoly_managment.domain.property.entities.Upgrade;
import com.monopoly.monopoly_managment.domain.property.events.DemolishedImprovement;
import com.monopoly.monopoly_managment.domain.property.events.MadeImprovement;
import com.monopoly.monopoly_managment.domain.property.events.MortgageCanceled;
import com.monopoly.monopoly_managment.domain.property.events.MortgageConstituted;
import com.monopoly.monopoly_managment.domain.property.events.OwnerAssigned;
import com.monopoly.monopoly_managment.domain.property.events.OwnerModified;
import com.monopoly.monopoly_managment.domain.property.events.OwnerRemoved;
import com.monopoly.monopoly_managment.domain.property.values.ColorGroup;
import com.monopoly.monopoly_managment.domain.property.values.IsMortgaged;
import com.monopoly.monopoly_managment.domain.property.values.MortgageId;
import com.monopoly.monopoly_managment.domain.property.values.Name;
import com.monopoly.monopoly_managment.domain.property.values.OwnerId;
import com.monopoly.monopoly_managment.domain.property.values.Price;
import com.monopoly.monopoly_managment.domain.property.values.PropertyId;
import com.monopoly.monopoly_managment.domain.property.values.TypeContrat;
import com.monopoly.monopoly_managment.domain.property.values.TypeContratEnum;
import com.monopoly.monopoly_managment.domain.property.values.TypeImprovementEnum;
import com.monopoly.shared.domain.generic.AggregateRoot;

import java.time.LocalDate;
import java.util.List;

public class Property extends AggregateRoot<PropertyId> {
  private BankAccountId bankAccountId;
  private List<Upgrade> improvements;
  private Contract contract;
  private Mortgage mortgage;
  private Owner owner;
  private Name name;
  private Price price;
  private ColorGroup colorGroup;

  // region Constructors
  private Property(PropertyId identity, Contract contract, Mortgage mortgage, Name name, Price price, ColorGroup colorGroup) {
    super(identity);
    this.contract = contract;
    this.mortgage = mortgage;
    this.name = name;
    this.price = price;
    this.colorGroup = colorGroup;
  }

  public Property(Contract contract, Mortgage mortgage, Name name, Price price, ColorGroup colorGroup) {
    super(new PropertyId());
    this.contract = contract;
    this.mortgage = mortgage;
    this.name = name;
    this.price = price;
    this.colorGroup = colorGroup;
  }

  // endregion

  // region Getters and Setters

  public BankAccountId getBankAccountId() {
    return bankAccountId;
  }

  public void setBankAccountId(BankAccountId bankAccountId) {
    this.bankAccountId = bankAccountId;
  }

  public List<Upgrade> getImprovements() {
    return improvements;
  }

  public void setImprovements(List<Upgrade> improvements) {
    this.improvements = improvements;
  }

  public Contract getContract() {
    return contract;
  }

  public void setContract(Contract contract) {
    this.contract = contract;
  }

  public Mortgage getMortgage() {
    return mortgage;
  }

  public void setMortgage(Mortgage mortgage) {
    this.mortgage = mortgage;
  }

  public Owner getOwner() {
    return owner;
  }

  public void setOwner(Owner owner) {
    this.owner = owner;
  }

  public Name getName() {
    return name;
  }

  public void setName(Name name) {
    this.name = name;
  }

  public Price getPrice() {
    return price;
  }

  public void setPrice(Price price) {
    this.price = price;
  }

  public ColorGroup getColorGroup() {
    return colorGroup;
  }

  public void setColorGroup(ColorGroup colorGroup) {
    this.colorGroup = colorGroup;
  }

  // endregion

  // region Domain Actions
  public void makeImprovement(String improvementId, String propertyId, TypeImprovementEnum type, Double cost) {
    apply(new MadeImprovement(improvementId, propertyId, type, cost));
  }

  public void demolishImprovement(String improvementId, String propertyId, TypeImprovementEnum type, Double cost) {
    apply(new DemolishedImprovement(improvementId, propertyId, type, cost));
  }

  public void mortgage(String ownerId, String propertyId, Double amount) {
    apply(new MortgageConstituted(ownerId, propertyId, amount));
  }

  public void cancelMortgage(String ownerId, String propertyId, Double amount) {
    apply(new MortgageCanceled(ownerId, propertyId, amount));
  }

  public void assignOwnerEvent(String ownerId, String propertyId) {
    apply(new OwnerAssigned(ownerId, propertyId));
  }

  public void removeOwnerEvent(String ownerId, String propertyId) {
    apply(new OwnerRemoved(ownerId, propertyId));
  }

  public void modifyOwnerEvent(String ownerId, String propertyId, String previousOwnerId) {
    apply(new OwnerModified(ownerId, propertyId, previousOwnerId));
  }

  // endregion

  // region Public Methods
  public void assignOwner(Owner owner, Contract contract) {
    if (!contract.getType().getValue().toString().equals(TypeContratEnum.SALE.toString())) {
      throw new IllegalArgumentException("The contract must be of type SALE");
    }
    contract.sign(this.owner.getIdentity());
    this.owner = owner;
    assignOwnerEvent(owner.getIdentity().getValue(), this.getIdentity().getValue());
  }

  public void removeOwner( Contract contract){
    if (this.owner != null){
      this.owner = null;
      contract.cancel();
      removeOwnerEvent(this.owner.getIdentity().getValue(), this.getIdentity().getValue());
    }
    else {
      throw new IllegalArgumentException("The property does not have an owner");
    }
  }

    public void modifyOwner( Owner owner, Contract contract) {
      if (this.owner != null) {
        String previousOwnerId = this.owner.getIdentity().getValue();
        this.owner = owner;
        contract.sign(this.owner.getIdentity());
        modifyOwnerEvent(this.owner.getIdentity().getValue(), this.getIdentity().getValue(), previousOwnerId);
      } else {
        throw new IllegalArgumentException("The property does not have an owner");
      }
    }
      public void makeImprovement(){
        if (this.owner != null){
          if (this.owner.getIdentity().getValue().equals(this.contract.getParties().getOwnerId().getValue())){
            if (this.improvements.size() < 5){
              if (this.improvements.size() == 0){
                if (this.owner.getBankAccountId().getBalance() >= 50){
                  this.owner.getBankAccountId().withdraw(50);
                  this.improvements.add(new Upgrade(TypeImprovementEnum.HOUSE, 50));
                  makeImprovement(this.getIdentity().getValue(), TypeImprovementEnum.HOUSE, 50);
                }
                else {
                  throw new IllegalArgumentException("The owner does not have enough money to make an improvement");
                }
              }
              else {
                if (this.owner.getBankAccountId().getBalance() >= 50){
                  this.owner.getBankAccountId().withdraw(50);
                  this.improvements.add(new Upgrade(TypeImprovementEnum.HOUSE, 50));
                  makeImprovement(this.getIdentity().getValue(), TypeImprovementEnum.HOUSE, 50);
                }
                else {
                  throw new IllegalArgumentException("The owner does not have enough money to make an improvement");
                }
              }
            }
            else {
              throw new IllegalArgumentException("The property already has 5 improvements");
            }
          }
          else {
            throw new IllegalArgumentException("The owner is not the owner of the property");
          }
        }
        else {
          throw new IllegalArgumentException("The property does not have an owner");
        }
      }

      public void demolishImprovement(){
        if (this.owner != null){
          if (this.owner.getIdentity().getValue().equals(this.contract.getParties().getOwnerId().getValue())){
            if (this.improvements.size() > 0){
              if (this.improvements.size() == 5){
                this.owner.getBankAccountId().deposit(50);
                this.improvements.remove(0);
                demolishImprovement(this.getIdentity().getValue(), TypeImprovementEnum.HOUSE, 50);
              }
              else {
                this.owner.getBankAccountId().deposit(50);
                this.improvements.remove(0);
                demolishImprovement(this.getIdentity().getValue(), TypeImprovementEnum.HOUSE, 50);
              }
            }
            else {
              throw new IllegalArgumentException("The property does not have any improvements");
            }
          }
          else {
            throw new IllegalArgumentException("The owner is not the owner of the property");
          }
        }
        else {
          throw new IllegalArgumentException("The property does not have an owner");
        }
      }
  public void mortgage(double amount) {
    validateOwnership();
    validateSufficientFunds(amount * 0.5); // Requiere 50% del valor como garantía

    this.mortgage = new Mortgage(
      new MortgageId(),
      amount,
      LocalDate.now().plusYears(1),
      IsMortgaged.of(true)
    );

    this.owner.getBankAccount().deposit(amount);
    apply(new MortgageConstituted(
      this.owner.getIdentity().value(),
      this.getIdentity().value(),
      amount
    ));
  }
  // endregion

  // region Private Methods
  private void validateOwnership() {
    if (this.owner == null) {
      throw new IllegalStateException("Property has no owner");
    }
    if (!this.owner.getIdentity().equals(this.contract.getParties().getOwnerId())) {
      throw new IllegalArgumentException("Action not allowed for non-owners");
    }
  }

  private void validateSufficientFunds(double amount) {
    if (this.owner.getBankAccount().getBalance() < amount) {
      throw new IllegalArgumentException("Insufficient funds");
    }
  }

  private void validateMaxImprovements() {
    if (this.improvements.size() >= 5) {
      throw new IllegalStateException("Maximum improvements reached");
    }
  }

  private void validateExistingImprovements() {
    if (this.improvements.isEmpty()) {
      throw new IllegalStateException("No improvements to demolish");
    }
    // endregion
  }
}
