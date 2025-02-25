package com.monopoly.monopoly_managment.domain.property;

import com.monopoly.monopoly_managment.domain.bank_account.values.BankAccountId;
import com.monopoly.monopoly_managment.domain.property.entities.Contract;
import com.monopoly.monopoly_managment.domain.property.entities.Mortgage;
import com.monopoly.monopoly_managment.domain.property.entities.Owner;
import com.monopoly.monopoly_managment.domain.property.entities.Upgrade;
import com.monopoly.monopoly_managment.domain.property.events.CreatedProperty;
import com.monopoly.monopoly_managment.domain.property.events.DemolishedImprovement;
import com.monopoly.monopoly_managment.domain.property.events.MadeImprovement;
import com.monopoly.monopoly_managment.domain.property.events.MortgageCanceled;
import com.monopoly.monopoly_managment.domain.property.events.MortgageConstituted;
import com.monopoly.monopoly_managment.domain.property.events.OwnerAssigned;
import com.monopoly.monopoly_managment.domain.property.events.OwnerModified;
import com.monopoly.monopoly_managment.domain.property.events.OwnerRemoved;
import com.monopoly.monopoly_managment.domain.property.values.*;
import com.monopoly.shared.domain.generic.AggregateRoot;
import com.monopoly.shared.domain.generic.DomainEvent;

import java.util.List;

public class Property extends AggregateRoot<PropertyId> {
  private BankAccountId bankAccountId;
  private UpgradeId improvements;
  private ContractId contract;
  private MortgageId mortgage;
  private OwnerId owner;
  private Name name;
  private Price price;
  private ColorGroup colorGroup;

  // region Constructors
  private Property(PropertyId identity) {
    super(identity);
    subscribe(new PropertyHandler(this));
  }

  public Property() {
    super(new PropertyId());
    subscribe(new PropertyHandler(this));
  }


  // endregion

  // region Getters and Setters
  public BankAccountId getBankAccountId() {
    return bankAccountId;
  }

  public void setBankAccountId(BankAccountId bankAccountId) {
    this.bankAccountId = bankAccountId;
  }

  public UpgradeId getImprovements() {
    return improvements;
  }

  public void setImprovements(UpgradeId improvements) {
    this.improvements = improvements;
  }

  public ContractId getContract() {
    return contract;
  }

  public void setContract(ContractId contract) {
    this.contract = contract;
  }

  public MortgageId getMortgage() {
    return mortgage;
  }

  public void setMortgage(MortgageId mortgage) {
    this.mortgage = mortgage;
  }

  public OwnerId getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = OwnerId.of(owner);
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
  public void createdProperty(String contractId, String mortgageId, String ownerId,String updateId ,String title, Double price, String colorGroup) {
    apply(new CreatedProperty(contractId, mortgageId, ownerId, updateId,title, price, colorGroup));
  }

  public void madeImprovement(String improvementId, String propertyId, TypeImprovementEnum type, Double cost) {
    apply(new MadeImprovement(improvementId, propertyId, type, cost));
  }

  public void demolishedImprovement(String improvementId, String propertyId, TypeImprovementEnum type, Double cost) {
    apply(new DemolishedImprovement(improvementId, propertyId, type, cost));
  }

  public void mortgaged(String ownerId, String propertyId , Double amount) {
    apply(new MortgageConstituted(ownerId, propertyId, amount));
  }

  public void canceledMortgage(String ownerId, String propertyId, Double amount) {
    apply(new MortgageCanceled(ownerId, propertyId, amount));
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
  public Integer getDevelopmentLevel() {
    Upgrade upgrade = getUpgradeById(this.improvements.getValue());
    return upgrade.getDevelopmentLevel().getValue();
  }

  public Upgrade getUpgradeById(String improvementId) {
    return new Upgrade(
      UpgradeId.of(improvementId),
      TypeImprovement.of(TypeImprovementEnum.HOUSE),
      DevelopmentLevel.of(3),
      Cost.of(100.0, CostEnum.BASE),
      PropertyId.of("propertyId")
    );
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

  public Mortgage getIsMortgaged(){
    return new Mortgage(MortgageId.of("mortgage-123"), Value.of(1000.0), IsMortgaged.of(false), CancellationCost.of(500.0));
  }

  public void setDevelopmentLevel(DevelopmentLevel developmentLevel) {
    Mortgage mortgage = new Mortgage(MortgageId.of("mortgage-123"), Value.of(1000.0), IsMortgaged.of(false), CancellationCost.of(500.0));
    setMortgage(mortgage.getIdentity());
  }

  public void validateMaximumDevelopmentLevel() {
    if (getDevelopmentLevel() == 8) {
      throw new RuntimeException("The property is already at its maximum level");
    }
  }

  public void validateOwnerMonopoly() {
    if (!getOwnerById(this.owner.getValue()).validateMonopoly(this.colorGroup)){
      throw new RuntimeException("The property does not belong to the owner");
    }
  }

  public Owner getOwnerById(final String ownerId) {
   Owner owner = new Owner(Alias.of("alias"), Token.of("example"), Portfolio.of(List.of("1")), Wealth.of(0.0, List.of()));
  this.owner = owner.getIdentity();
    return owner;
  }

  public OwnerId getOwnerIdById(final String ownerId) {
    return this.owner;
  }

  public void buildImprovement(String improvementId, String propertyId, TypeImprovementEnum type, Double cost) {
    Upgrade upgrade = new Upgrade(UpgradeId.of(improvementId), TypeImprovement.of(type), DevelopmentLevel.of(3), Cost.of(cost, CostEnum.BASE), PropertyId.of(propertyId));
    setImprovements(upgrade.getIdentity());
  }

  public void downgradeImprovement(String improvementId, String propertyId, TypeImprovementEnum type, Double cost) {
    Upgrade upgrade = new Upgrade(UpgradeId.of(improvementId), TypeImprovement.of(type), DevelopmentLevel.of(3), Cost.of(cost, CostEnum.BASE), PropertyId.of(propertyId));
    setImprovements(upgrade.getIdentity());
  }

  public Mortgage getMortgageById(String mortgageId, Boolean status) {
    Mortgage mortgage = new Mortgage(MortgageId.of(mortgageId), Value.of(0.0), IsMortgaged.of(status), CancellationCost.of(0.0));
    this.mortgage = mortgage.getIdentity();
    return mortgage;
  }

  public Contract getContractById(String contractId) {
    Contract contract = new Contract(ContractId.of(contractId), TypeContrat.of(TypeContratEnum.RENT), Rate.of(0.0, 0.0), Parties.of("ownerId"), IsActive.of(true));
    this.contract = contract.getIdentity();
    return contract;
  }
  // endregion

  public static Property from(final String identity,final List<DomainEvent> domainEvents) {
    Property property = new Property(PropertyId.of(identity));
    domainEvents.forEach(property::apply);
    return property;
  }
}