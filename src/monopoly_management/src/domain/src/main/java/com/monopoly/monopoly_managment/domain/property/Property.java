package com.monopoly.monopoly_managment.domain.property;

import com.monopoly.monopoly_managment.domain.bank_account.events.CompletedTransaction;
import com.monopoly.monopoly_managment.domain.bank_account.values.Amount;
import com.monopoly.monopoly_managment.domain.bank_account.values.BankAccountId;
import com.monopoly.monopoly_managment.domain.bank_account.values.TransactionId;
import com.monopoly.monopoly_managment.domain.bank_account.values.TypeEnum;
import com.monopoly.monopoly_managment.domain.property.entities.Contract;
import com.monopoly.monopoly_managment.domain.property.entities.Mortgage;
import com.monopoly.monopoly_managment.domain.property.entities.Owner;
import com.monopoly.monopoly_managment.domain.property.entities.Upgrade;
import com.monopoly.monopoly_managment.domain.property.events.ContractSigned;
import com.monopoly.monopoly_managment.domain.property.events.DemolishedImprovement;
import com.monopoly.monopoly_managment.domain.property.events.MadeImprovement;
import com.monopoly.monopoly_managment.domain.property.events.MortgageCanceled;
import com.monopoly.monopoly_managment.domain.property.events.MortgageConstituted;
import com.monopoly.monopoly_managment.domain.property.events.OwnerAssigned;
import com.monopoly.monopoly_managment.domain.property.events.OwnerModified;
import com.monopoly.monopoly_managment.domain.property.events.OwnerRemoved;
import com.monopoly.monopoly_managment.domain.property.values.ColorGroup;
import com.monopoly.monopoly_managment.domain.property.values.ContractId;
import com.monopoly.monopoly_managment.domain.property.values.Cost;
import com.monopoly.monopoly_managment.domain.property.values.DevelopmentLevel;
import com.monopoly.monopoly_managment.domain.property.values.Name;
import com.monopoly.monopoly_managment.domain.property.values.OwnerId;
import com.monopoly.monopoly_managment.domain.property.values.Price;
import com.monopoly.monopoly_managment.domain.property.values.PropertyId;
import com.monopoly.monopoly_managment.domain.property.values.Rate;
import com.monopoly.monopoly_managment.domain.property.values.TypeContratEnum;
import com.monopoly.monopoly_managment.domain.property.values.TypeImprovement;
import com.monopoly.monopoly_managment.domain.property.values.TypeImprovementEnum;
import com.monopoly.shared.domain.generic.AggregateRoot;

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
  public void madeImprovement(String improvementId, String propertyId, TypeImprovementEnum type, Double cost) {
    apply(new MadeImprovement(improvementId, propertyId, type, cost));
  }

  public void demolishedImprovement(String improvementId, String propertyId, TypeImprovementEnum type, Double cost) {
    apply(new DemolishedImprovement(improvementId, propertyId, type, cost));
  }

  public void mortgaged(String ownerId, String propertyId, Double amount) {
    apply(new MortgageConstituted(ownerId, propertyId, amount));
  }

  public void canceledMortgage(String ownerId, String propertyId, Double amount) {
    apply(new MortgageCanceled(ownerId, propertyId, amount));
  }

  public void signedContract(String contractId, String propertyId, String ownerId, TypeContratEnum contractType, Double rate, String tenantId) {
    apply(new ContractSigned(contractId, propertyId, ownerId, contractType, rate, tenantId));
  }

  public void finalizedContract(String accountId, String ownerId, String transactionId, TypeEnum type, Double amount){
    apply(new CompletedTransaction(accountId, ownerId, transactionId, type, amount));
  }

  public void assignedOwner(String ownerId, String propertyId) {
    apply(new OwnerAssigned(ownerId, propertyId));
  }

  public void removedOwner(String ownerId, String propertyId) {
    apply(new OwnerRemoved(ownerId, propertyId));
  }

  public void modifiedOwner(String ownerId, String propertyId, String previousOwnerId) {
    apply(new OwnerModified(ownerId, propertyId, previousOwnerId));
  }

  // endregion

  // region Public Methods
  public void makeImprovement(TypeImprovement improvement, DevelopmentLevel developmentLevel, Cost cost, PropertyId propertyId) {
    if (getBalance() < cost.getValue()) {
      throw new RuntimeException("The balance is not enough to make the improvement");
    }
    Upgrade upgrade = new Upgrade(improvement, developmentLevel,propertyId ,cost);
    this.improvements.add(upgrade);
    subtractBalance(cost.getValue());
    madeImprovement(upgrade.getIdentity().getValue(), propertyId.getValue(), improvement.getValue(), cost.getValue());
  }

  public void demolishImprovement(TypeImprovement improvement, DevelopmentLevel developmentLevel, Cost cost, PropertyId propertyId) {
    if (developmentLevel.getValue() == 0) {
      throw new RuntimeException("The property is already at its minimum level");
    }
    Upgrade upgrade = new Upgrade(improvement, developmentLevel,propertyId ,cost);
    this.improvements.remove(upgrade);
    addBalance(cost.getValue());
    demolishedImprovement(upgrade.getIdentity().getValue(), propertyId.getValue(), improvement.getValue(), cost.getValue());
  }

  public void mortgage(OwnerId ownerId, Amount amount) {
    if (mortgage.getIsMortgaged().getValue()) {
      throw new RuntimeException("The property is already mortgaged");
    }
    mortgage.activate();
    mortgaged(ownerId.getValue(), this.getIdentity().getValue(), amount.getValue());
  }

  public void cancelMortgage(OwnerId ownerId, Amount amount) {
    if (!mortgage.getIsMortgaged().getValue()) {
      throw new RuntimeException("The property is not mortgaged");
    }
    mortgage.cancel();
    canceledMortgage(ownerId.getValue(), this.getIdentity().getValue(), amount.getValue());
  }

  public void signContract(ContractId contractId, OwnerId ownerId, TypeContratEnum contractType, Rate rate, OwnerId tenantId) {
    if (contract.getIsActive().getValue()) {
      throw new RuntimeException("The contract is already signed");
    }
    contract.sign(ownerId);
    signedContract(contractId.getValue(), this.getIdentity().getValue(), ownerId.getValue(), contractType, rate.getBase(), tenantId.getValue());
  }

  public void finalizeContract(BankAccountId accountId, OwnerId ownerId, TransactionId transactionId, TypeEnum type, Amount amount){
    if (!contract.getIsActive().getValue()) {
      throw new RuntimeException("The contract is already canceled");
    }
    contract.cancel();
    finalizedContract(accountId.getValue(), ownerId.getValue(), transactionId.getValue(), type, amount.getValue());
  }

  public void assignOwner(OwnerId ownerId) {
    if ( owner.getIdentity().getValue() != null) {
      throw new RuntimeException("The property already has an owner");
    }
    owner.acquireProperty(getIdentity());
    assignedOwner(ownerId.getValue(), this.getIdentity().getValue());
  }

  public void removeOwner(OwnerId ownerId) {
    if ( owner.getIdentity().getValue() == null) {
      throw new RuntimeException("The property does not have an owner");
    }
    owner.sellProperty(getIdentity());
    removedOwner(ownerId.getValue(), this.getIdentity().getValue());
  }

  public void modifyOwner(OwnerId ownerId, Owner previousOwner) {
    if ( owner.getIdentity().getValue() == null) {
      throw new RuntimeException("The property does not have an owner");
    }
    owner.transferProperty(getIdentity(), previousOwner);
    modifiedOwner(ownerId.getValue(), this.getIdentity().getValue(), previousOwner.getIdentity().getValue());
  }
    // endregion
    // region Private Methods
  private Double getBalance() {
    return 4.0;
  }

  private void addBalance(Double balance){
  }

  private void subtractBalance(Double balance){
  }
  // endregion
}
