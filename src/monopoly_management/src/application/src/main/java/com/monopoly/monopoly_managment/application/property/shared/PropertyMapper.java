package com.monopoly.monopoly_managment.application.property.shared;

import com.monopoly.monopoly_managment.domain.property.Property;
import com.monopoly.monopoly_managment.domain.property.entities.Contract;
import com.monopoly.monopoly_managment.domain.property.entities.Mortgage;
import com.monopoly.monopoly_managment.domain.property.entities.Owner;
import com.monopoly.monopoly_managment.domain.property.entities.Upgrade;

public class PropertyMapper {
  public static PropertyResponse toPropertyResponse(Property property) {
    return new PropertyResponse(
      property.getIdentity().getValue(),
      toUpgradeResponse(property.getUpgradeById(property.getImprovements().getValue())
      ),
      toContractResponse(property.getContractById(property.getContract().getValue())),
      toMortgageResponse(property.getMortgageById(property.getMortgage().getValue(),false)),
      toOwnerResponse(property.getOwnerById(property.getOwner().getValue())), property.getName().getValue(), property.getPrice().getValue(), property.getColorGroup().getValue()
    );
  }

  private static PropertyResponse.Upgrade toUpgradeResponse(Upgrade upgrade) {
    return new PropertyResponse.Upgrade(
      upgrade.getTypeImprovement(),
      upgrade.getDevelopmentLevel().getValue(),
      upgrade.getPropertyId().getValue(),
      upgrade.getCost().getValue()
    );
  }

  private static PropertyResponse.Contract toContractResponse(Contract contract) {
    return new PropertyResponse.Contract(
      contract.getType(),
      contract.getRate().getBase(),
      new PropertyResponse.Parties(
        contract.getParties().getOwnerId(),
        contract.getParties().getTenantId()
      ),
      contract.getIsActive().getValue()
    );
  }

  private static PropertyResponse.Mortgage toMortgageResponse(Mortgage mortgage) {
    return new PropertyResponse.Mortgage(
      mortgage.getValue().getValue(),
      mortgage.getIsMortgaged().getValue(),
      mortgage.getCancellationCost().getValue()
    );
  }

  private static PropertyResponse.Owner toOwnerResponse(Owner owner) {
    return new PropertyResponse.Owner(
      owner.getAlias().getValue(),
      owner.getToken().getValue(),
      owner.getPortfolio().getPropertiesIds(),
      owner.getWealth().getBalance()
    );
  }
}