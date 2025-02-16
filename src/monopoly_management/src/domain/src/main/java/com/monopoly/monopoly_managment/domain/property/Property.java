package com.monopoly.monopoly_managment.domain.property;

import com.monopoly.monopoly_managment.domain.bank_account.values.Amount;
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
import com.monopoly.monopoly_managment.domain.property.values.Cost;
import com.monopoly.monopoly_managment.domain.property.values.Name;
import com.monopoly.monopoly_managment.domain.property.values.OwnerId;
import com.monopoly.monopoly_managment.domain.property.values.Price;
import com.monopoly.monopoly_managment.domain.property.values.PropertyId;
import com.monopoly.monopoly_managment.domain.property.values.TypeImprovementEnum;
import com.monopoly.monopoly_managment.domain.property.values.UpgradeId;
import com.monopoly.shared.domain.generic.AggregateRoot;

public class Property extends AggregateRoot<PropertyId> {
  private BankAccountId bankAccountId;
  private Upgrade improvements;
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

  public Upgrade getImprovements() {
    return improvements;
  }

  public void setImprovements(Upgrade improvements) {
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
  public void madeImprovement(UpgradeId improvementId, PropertyId propertyId, TypeImprovementEnum type, Cost cost) {
    apply(new MadeImprovement(improvementId.getValue(), propertyId.getValue(), type, cost.getValue()));
  }

  public void demolishedImprovement(UpgradeId improvementId, PropertyId propertyId, TypeImprovementEnum type, Cost cost) {
    apply(new DemolishedImprovement(improvementId.getValue(), propertyId.getValue(), type, cost.getValue()));
  }

  public void mortgaged(OwnerId ownerId, PropertyId propertyId, Amount amount) {
    apply(new MortgageConstituted(ownerId.getValue(), propertyId.getValue(), amount.getValue()));
  }

  public void canceledMortgage(OwnerId ownerId, PropertyId propertyId, Amount amount) {
    apply(new MortgageCanceled(ownerId.getValue(), propertyId.getValue(), amount.getValue()));
  }

  public void assignedOwner(OwnerId ownerId, PropertyId propertyId) {
    apply(new OwnerAssigned(ownerId.getValue(), propertyId.getValue()));
  }

  public void removedOwner(OwnerId ownerId, PropertyId propertyId) {
    apply(new OwnerRemoved(ownerId.getValue(), propertyId.getValue()));
  }

  public void modifiedOwner(OwnerId ownerId, PropertyId propertyId, OwnerId previousOwnerId) {
    apply(new OwnerModified(ownerId.getValue(), propertyId.getValue(), previousOwnerId.getValue()));
  }
  // endregion

  // region Public Methods
  public void makeImprovement(UpgradeId improvementId, PropertyId propertyId, TypeImprovementEnum type, Cost cost) {
    validateSufficientBalance(cost);
    validateMaximumDevelopmentLevel();
    validateOwnerMonopoly();
    improvements.build();
    subtractBalance(cost.getValue());
    madeImprovement(improvementId, propertyId, type, cost);
  }

  public void demolishImprovement(UpgradeId improvementId, PropertyId propertyId, TypeImprovementEnum type, Cost cost) {
    if (getDevelopmentLevel() == 0) {
      throw new RuntimeException("The property is already at its minimum level");
    }
    improvements.downgrade();
    addBalance(cost.getValue());
    demolishedImprovement(improvementId, propertyId, type, cost);
  }

  public void mortgage(OwnerId ownerId, Amount amount) {
    if (mortgage.getIsMortgaged().getValue()) {
      throw new RuntimeException("The property is already mortgaged");
    }
    mortgage.activate();
    mortgaged(ownerId, this.getIdentity(), amount);
  }

  public void cancelMortgage(OwnerId ownerId, Amount amount) {
    if (!mortgage.getIsMortgaged().getValue()) {
      throw new RuntimeException("The property is not mortgaged");
    }
    validateCancelMortgage();
    mortgage.cancel();
    canceledMortgage(ownerId, this.getIdentity(), amount);
  }

  public void assignOwner(OwnerId ownerId) {
    if ( owner.getIdentity().getValue() != null) {
      throw new RuntimeException("The property already has an owner");
    }
    owner.acquireProperty(getIdentity());
    contract.sign(ownerId);
    assignedOwner(ownerId, this.getIdentity()));
  }

  public void removeOwner(OwnerId ownerId) {
    if ( owner.getIdentity().getValue() == null) {
      throw new RuntimeException("The property does not have an owner");
    }
    contract.cancel();
    owner.sellProperty(getIdentity());
    removedOwner(ownerId, this.getIdentity());
  }

  public void modifyOwner(OwnerId ownerId, Owner previousOwner) {
    if ( owner.getIdentity().getValue() == null) {
      throw new RuntimeException("The property does not have an owner");
    }
    owner.transferProperty(getIdentity(), previousOwner);
    contract.cancel();
    previousOwner.sellProperty(getIdentity());
    modifiedOwner(ownerId, this.getIdentity(), previousOwner.getIdentity());
  }
    // endregion
    // region Private Methods
  private Integer getDevelopmentLevel() {
    return improvements.getDevelopmentLevel().getValue();
  }

  private Double getBalance() {
    return 4.0;
  }

  private void addBalance(Double balance){
  }

  private void subtractBalance(Double balance){
  }

  private void validateSufficientBalance(Cost cost) {
    if (getBalance() < cost.getValue()) {
      throw new RuntimeException("The balance is not enough to make the improvement");
    }
  }

  private void validateCancelMortgage(){
    if (getBalance() < mortgage.calculateCancellationCost()){
      throw new RuntimeException(" Not enough balance ");
    }
  }

  private void validateMaximumDevelopmentLevel() {
    if (getDevelopmentLevel() == 8) {
      throw new RuntimeException("The property is already at its maximum level");
    }
  }

  private void validateOwnerMonopoly() {
    if (!owner.validateMonopoly(colorGroup)) {
      throw new RuntimeException("The owner does not have a monopoly of the color group");
    }
  }
  // endregion
}
