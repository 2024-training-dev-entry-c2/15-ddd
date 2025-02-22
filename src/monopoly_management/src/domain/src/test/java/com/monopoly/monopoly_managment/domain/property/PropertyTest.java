package com.monopoly.monopoly_managment.domain.property;

import com.monopoly.monopoly_managment.domain.bank_account.values.Amount;
import com.monopoly.monopoly_managment.domain.bank_account.values.BankAccountId;
import com.monopoly.monopoly_managment.domain.property.entities.Contract;
import com.monopoly.monopoly_managment.domain.property.entities.Mortgage;
import com.monopoly.monopoly_managment.domain.property.entities.Owner;
import com.monopoly.monopoly_managment.domain.property.entities.Upgrade;
import com.monopoly.monopoly_managment.domain.property.events.*;
import com.monopoly.monopoly_managment.domain.property.values.*;
import com.monopoly.shared.domain.generic.DomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Constructor;


class PropertyTest {
  private Property property;
  private PropertyId propertyId;
  private OwnerId ownerId;
  private UpgradeId upgradeId;
  private Cost cost;
  private TypeImprovementEnum type;

  @BeforeEach
  void setUp() {
    propertyId = PropertyId.of("property-123");
    ownerId = OwnerId.of("owner-123");
    upgradeId = UpgradeId.of("upgrade-123");
    cost = Cost.of(0.0, CostEnum.BASE);
    type = TypeImprovementEnum.HOUSE;
    property = new Property();
    property.setImprovements(new Upgrade(upgradeId, TypeImprovement.of(type), DevelopmentLevel.of(0), cost, propertyId).getIdentity());
    property.setOwner(new Owner(Alias.of("alias"), Token.of(UUID.randomUUID().toString()), Portfolio.of(List.of("1")), null).getIdentity().getValue());
  }

  @Test
  void madeImprovement() {
    property.madeImprovement(upgradeId.getValue(), propertyId.getValue(), type, cost.getValue());
    assertEquals(3, property.getDevelopmentLevel());
  }

  @Test
  void demolishedImprovement() {
    property.setDevelopmentLevel(DevelopmentLevel.of(1));
    property.demolishedImprovement(upgradeId.getValue(), propertyId.getValue(), type, cost.getValue());
    assertEquals(0, property.getDevelopmentLevel());
  }

  @Test
  void mortgaged() {
    property.mortgaged(ownerId.getValue(), propertyId.getValue(), 500.0);
    assertTrue(property.getIsMortgaged().getIsMortgaged().getValue());
  }

  @Test
  void mortgageFailed() {
    property.addBalance(1000.0);
    property.mortgaged(ownerId.getValue(), propertyId.getValue(), 500.0);
    assertThrows(RuntimeException.class, () -> property.mortgaged(ownerId.getValue(), propertyId.getValue(), 500.0));
  }

  @Test
  void validateSufficientBalance() {
    property.addBalance(1000.0);
    assertDoesNotThrow(() -> property.validateSufficientBalance(1.0));
    assertThrows(RuntimeException.class, () -> property.validateSufficientBalance(1500.0));
  }

  @Test
  void validateOwnerMonopoly() {
    assertDoesNotThrow(() -> property.validateOwnerMonopoly());
  }

  @Test
  void testPrivateConstructor() throws Exception {
    PropertyId propertyId = PropertyId.of("property-123");
    Contract contract = new Contract(new ContractId(), TypeContrat.of(TypeContratEnum.SALE), Rate.of(1.0, 4.0), Parties.of("owner-123"), IsActive.of(true));
    Mortgage mortgage = new Mortgage(MortgageId.of("mortgage-123"), Value.of(1000.0), IsMortgaged.of(false), CancellationCost.of(500.0));
    Name name = Name.of("property-name");
    Price price = Price.of(1000.0);
    ColorGroup colorGroup = ColorGroup.of("BROWN");

    Constructor<Property> constructor = Property.class.getDeclaredConstructor(PropertyId.class, Contract.class, Mortgage.class, Name.class, Price.class, ColorGroup.class);
    constructor.setAccessible(true);
    Property property = constructor.newInstance(propertyId, contract, mortgage, name, price, colorGroup);

    assertNotNull(property);
    assertEquals(propertyId, property.getIdentity());
    assertEquals(contract.getIdentity(), property.getContract());
    assertEquals(mortgage.getIdentity(), property.getMortgage());
    assertEquals(name, property.getName());
    assertEquals(price, property.getPrice());
    assertEquals(colorGroup, property.getColorGroup());
    assertNotNull(property.getImprovements());
  }

  @Test
  void testGetBankAccountId() {
    BankAccountId bankAccountId = BankAccountId.of("bank-account-123");
    property.setBankAccountId(bankAccountId);
    assertEquals(bankAccountId, property.getBankAccountId());
  }

  @Test
  void testSetBankAccountId() {
    BankAccountId bankAccountId = BankAccountId.of("bank-account-123");
    property.setBankAccountId(bankAccountId);
    assertEquals(bankAccountId, property.getBankAccountId());
  }

  @Test
  void testSetContract (){
    Contract contract = new Contract(new ContractId(), TypeContrat.of(TypeContratEnum.SALE), Rate.of(1.0, 4.0), Parties.of("owner-123"), IsActive.of(true));
    property.setContract(contract.getIdentity());
    assertEquals(contract.getIdentity(), property.getContract());
  }

  @Test
  void testSetMortgage(){
    Mortgage mortgage = new Mortgage(MortgageId.of("mortgage-123"), Value.of(1000.0), IsMortgaged.of(false), CancellationCost.of(500.0));
    property.setMortgage(mortgage.getIdentity());
    assertEquals(mortgage.getIdentity(), property.getMortgage());
  }

  @Test
  void testGetOwner(){
    Owner owner = new Owner(Alias.of("alias"), Token.of(UUID.randomUUID().toString()), Portfolio.of(List.of("1")), null);
    property.setOwner(owner.getIdentity().getValue());
    assertEquals(owner.getIdentity(), property.getOwner());
  }

  @Test
  void testSetPrice(){
    Price price = Price.of(1000.0);
    property.setPrice(price);
    assertEquals(price, property.getPrice());
  }

  @Test
  void testSetName(){
    Name name = Name.of("property-name");
    property.setName(name);
    assertEquals(name, property.getName());
  }

  @Test
  void setColorGroup(){
    ColorGroup colorGroup = ColorGroup.of("BROWN");
    property.setColorGroup(colorGroup);
    assertEquals(colorGroup, property.getColorGroup());
  }

  @Test
  void testGetOwnerById() {
    Owner owner = property.getOwnerById("owner-123");
    assertNotNull(owner);
    assertEquals("alias", owner.getAlias().getValue());
    assertEquals("example", owner.getToken().getValue());
    assertEquals(List.of("1"), owner.getPortfolio().getPropertiesIds());
  }

  @Test
  void testFrom() {
    Contract contract = new Contract(new ContractId(), TypeContrat.of(TypeContratEnum.SALE), Rate.of(1.0, 4.0), Parties.of("owner-123"), IsActive.of(true));
    Mortgage mortgage = new Mortgage(MortgageId.of("mortgage-123"), Value.of(1000.0), IsMortgaged.of(false), CancellationCost.of(500.0));
    Name name = Name.of("property-name");
    Price price = Price.of(1000000.0);
    ColorGroup colorGroup = ColorGroup.of("BROWN");
    Owner owner = new Owner(Alias.of("alias"), Token.of(UUID.randomUUID().toString()), Portfolio.of(List.of("1")), null);
    List<DomainEvent> domainEvents = List.of(new MortgageConstituted(owner.getIdentity().getValue(), propertyId.getValue(), 1000.0));

    Property property = Property.from("property-123", domainEvents);
    property.setOwner(owner.getIdentity().getValue());
    property.addBalance(1000.0);

    assertNotNull(property);
    assertEquals(contract.getIdentity(), property.getContract());
    assertEquals(mortgage.getIdentity(), property.getMortgage());
    assertEquals(name, property.getName());
    assertEquals(price, property.getPrice());
    assertEquals(colorGroup, property.getColorGroup());
    assertNotNull(property.getImprovements());
  }

  @Test
  void testCanceledMortgage() {
    OwnerId ownerId = OwnerId.of("owner-123");
    PropertyId propertyId = PropertyId.of("property-123");
    Amount amount = Amount.of(50000.0);
    property.addBalance(10000.0);
    property.mortgaged(ownerId.getValue(), propertyId.getValue(), amount.getValue());
    property.canceledMortgage(ownerId.getValue(), propertyId.getValue(), amount.getValue());
    assertFalse(property.getIsMortgaged().getIsMortgaged().getValue());
  }

  @Test
  void testValidateOwnerMonopoly() {
    Owner ownerWithMonopoly = new Owner(Alias.of("alias"), Token.of(UUID.randomUUID().toString()), Portfolio.of(List.of("property-123", "property-124")), null);
    property.setOwner(ownerWithMonopoly.getIdentity().getValue());
    property.setColorGroup(ColorGroup.of("BROWN"));
    assertDoesNotThrow(() -> property.validateOwnerMonopoly());
  }

  @Test
  void testValidateOwnerMonopolyFailed() {
    Owner ownerWithoutMonopoly = new Owner(Alias.of("alias"), Token.of(UUID.randomUUID().toString()), Portfolio.of(List.of("property-123")), null);
    property.setOwner(ownerWithoutMonopoly.getIdentity().getValue());
    property.setColorGroup(ColorGroup.of("PINK"));
    assertThrows(RuntimeException.class, () -> property.validateOwnerMonopoly());
  }

  @Test
  void testAssignedOwner() {
    OwnerId newOwnerId = OwnerId.of("new-owner-123");
    PropertyId propertyId = PropertyId.of("property-123");
    property.setOwner(new Owner(Alias.of("alias"), Token.of(UUID.randomUUID().toString()), Portfolio.of(List.of()), null).getIdentity().getValue());
    assertDoesNotThrow(() -> property.assignedOwner(newOwnerId.getValue(), propertyId.getValue()));
  }

  @Test
  void testRemovedOwner() {
    OwnerId ownerId = OwnerId.of("owner-123");
    PropertyId propertyId = PropertyId.of("property-123");
    property.setOwner(new Owner(ownerId, Alias.of("alias"), Token.of(UUID.randomUUID().toString()), Portfolio.of(List.of("property-123")), null).getIdentity().getValue());
    assertDoesNotThrow(() -> property.removedOwner(ownerId.getValue(), propertyId.getValue()));
    assertNull(property.getOwner());

    property.setOwner(null);

  }

  @Test
  void testModifiedOwner() {
    OwnerId newOwnerId = OwnerId.of("new-owner-123");
    PropertyId propertyId = PropertyId.of("property-123");
    OwnerId previousOwnerId = OwnerId.of("previous-owner-123");

    // Set the initial owner
    property.setOwner(new Owner(previousOwnerId, Alias.of("alias"), Token.of(UUID.randomUUID().toString()), Portfolio.of(List.of("property-123")), null).getIdentity().getValue());

    // Modify the owner
    assertDoesNotThrow(() -> property.modifiedOwner(newOwnerId.getValue(), propertyId.getValue(), previousOwnerId.getValue()));

  }

}