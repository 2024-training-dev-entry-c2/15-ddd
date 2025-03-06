package com.monopoly.monopoly_managment.application.property.shared;

import com.monopoly.monopoly_managment.domain.property.values.TypeContrat;
import com.monopoly.monopoly_managment.domain.property.values.TypeContratEnum;
import com.monopoly.monopoly_managment.domain.property.values.TypeImprovement;
import com.monopoly.monopoly_managment.domain.property.values.TypeImprovementEnum;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PropertyResponseTest {

  @Test
  void testPropertyResponse() {
    PropertyResponse.Upgrade upgrade = new PropertyResponse.Upgrade(
      TypeImprovement.of(TypeImprovementEnum.HOUSE), 3, "property-123", 100.0);
    PropertyResponse.Contract contract = new PropertyResponse.Contract(
      TypeContrat.of(TypeContratEnum.RENT), 1.0, new PropertyResponse.Parties("owner-123", List.of("tenant-123")), true);
    PropertyResponse.Mortgage mortgage = new PropertyResponse.Mortgage(1000.0, false, 500.0);
    PropertyResponse.Owner owner = new PropertyResponse.Owner("alias", "token", List.of("property-123"), 1000.0);

    PropertyResponse propertyResponse = new PropertyResponse(
      "property-123", upgrade, contract, mortgage, owner, "property-name", 1000.0, "BROWN");

    assertEquals("property-123", propertyResponse.getPropertyID());
    assertEquals(upgrade, propertyResponse.getUpgrade());
    assertEquals(contract, propertyResponse.getContract());
    assertEquals(mortgage, propertyResponse.getMortgage());
    assertEquals(owner, propertyResponse.getOwner());
    assertEquals("property-name", propertyResponse.getName());
    assertEquals(1000.0, propertyResponse.getPrice());
    assertEquals("BROWN", propertyResponse.getColorGroup());
  }

  @Test
  void testUpgrade() {
    PropertyResponse.Upgrade upgrade = new PropertyResponse.Upgrade(
      TypeImprovement.of(TypeImprovementEnum.HOUSE), 3, "property-123", 100.0);


    assertEquals(3, upgrade.getDevelopmentLevel());
    assertEquals("property-123", upgrade.getPropertyId());
    assertEquals(100.0, upgrade.getCost());
  }

  @Test
  void testContract() {
    PropertyResponse.Parties parties = new PropertyResponse.Parties("owner-123", List.of("tenant-123"));
    PropertyResponse.Contract contract = new PropertyResponse.Contract(
      TypeContrat.of(TypeContratEnum.RENT), 1.0, parties, true);

    assertEquals(1.0, contract.getRate());
    assertEquals(parties, contract.getParties());
    assertEquals(true, contract.getActive());
  }

  @Test
  void testParties() {
    PropertyResponse.Parties parties = new PropertyResponse.Parties("owner-123", List.of("tenant-123"));

    assertEquals("owner-123", parties.getOwnerId());
    assertEquals(List.of("tenant-123"), parties.getTenantId());
  }

  @Test
  void testMortgage() {
    PropertyResponse.Mortgage mortgage = new PropertyResponse.Mortgage(1000.0, false, 500.0);

    assertEquals(1000.0, mortgage.getValue());
    assertEquals(false, mortgage.getMortgaged());
    assertEquals(500.0, mortgage.getCancellationCost());
  }

  @Test
  void testOwner() {
    PropertyResponse.Owner owner = new PropertyResponse.Owner("alias", "token", List.of("property-123"), 1000.0);

    assertEquals("alias", owner.getAlias());
    assertEquals("token", owner.getToken());
    assertEquals(List.of("property-123"), owner.getPortfolio());
    assertEquals(1000.0, owner.getWealth());
  }
}