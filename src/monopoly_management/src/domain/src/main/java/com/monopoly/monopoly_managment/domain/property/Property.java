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
import com.monopoly.monopoly_managment.domain.property.values.Alias;
import com.monopoly.monopoly_managment.domain.property.values.ColorGroup;
import com.monopoly.monopoly_managment.domain.property.values.Cost;
import com.monopoly.monopoly_managment.domain.property.values.CostEnum;
import com.monopoly.monopoly_managment.domain.property.values.DevelopmentLevel;
import com.monopoly.monopoly_managment.domain.property.values.Name;
import com.monopoly.monopoly_managment.domain.property.values.OwnerId;
import com.monopoly.monopoly_managment.domain.property.values.Portfolio;
import com.monopoly.monopoly_managment.domain.property.values.Price;
import com.monopoly.monopoly_managment.domain.property.values.PropertyId;
import com.monopoly.monopoly_managment.domain.property.values.Token;
import com.monopoly.monopoly_managment.domain.property.values.TypeImprovement;
import com.monopoly.monopoly_managment.domain.property.values.TypeImprovementEnum;
import com.monopoly.monopoly_managment.domain.property.values.UpgradeId;
import com.monopoly.shared.domain.generic.AggregateRoot;
import com.monopoly.shared.domain.generic.DomainEvent;

import java.util.List;

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
    subscribe(new PropertyHandler(this));
    this.contract = contract;
    this.mortgage = mortgage;
    this.name = name;
    this.price = price;
    this.colorGroup = colorGroup;
    this.improvements = new Upgrade( new UpgradeId(), TypeImprovement.of(TypeImprovementEnum.HOUSE), DevelopmentLevel.of(0), Cost.of(0.0, CostEnum.BASE), identity);
  }

  public Property(Contract contract, Mortgage mortgage, Name name, Price price, ColorGroup colorGroup) {
    super(new PropertyId());
    subscribe(new PropertyHandler(this));
    this.contract = contract;
    this.mortgage = mortgage;
    this.name = name;
    this.price = price;
    this.colorGroup = colorGroup;
    this.improvements = new Upgrade( new UpgradeId(), TypeImprovement.of(TypeImprovementEnum.HOUSE), DevelopmentLevel.of(0), Cost.of(0.0, CostEnum.BASE), new PropertyId());
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
  public void madeImprovement(String improvementId, String propertyId, TypeImprovementEnum type, Double cost) {
    apply(new MadeImprovement(improvementId, propertyId, type, cost));
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
  public Integer getDevelopmentLevel() {
    return improvements.getDevelopmentLevel().getValue();
  }

  public Double getBalance() {
    return 4.0;
  }

  public void addBalance(Double balance){
  }

  public void subtractBalance(Double balance){
  }

  public void validateSufficientBalance(Double cost) {
    if (getBalance() < cost) {
      throw new RuntimeException("The balance is not enough to make the improvement");
    }
  }

  public void validateMaximumDevelopmentLevel() {
    if (getDevelopmentLevel() == 8) {
      throw new RuntimeException("The property is already at its maximum level");
    }
  }

  public void validateOwnerMonopoly() {
    if (!owner.validateMonopoly(colorGroup)) {
      throw new RuntimeException("The owner does not have a monopoly of the color group");
    }
  }


  public Owner getOwnerById(final String ownerId) {
    return new Owner(Alias.of("alias"), Token.of("example"), Portfolio.of(List.of("1")), null);
  }
  // endregion

  public static Property from(final String identity,final List<DomainEvent> domainEvents, final Contract contract, final Mortgage mortgage, final Name name, final Price price, final ColorGroup colorGroup) {
    Property property = new Property(PropertyId.of(identity), contract, mortgage, name, price, colorGroup);

    domainEvents.forEach(property::apply);
    return property;
  }

}
