package com.monopoly.monopoly_managment.domain.property;

import com.monopoly.monopoly_managment.domain.bank_account.values.Amount;
import com.monopoly.monopoly_managment.domain.property.entities.Owner;
import com.monopoly.monopoly_managment.domain.property.values.Cost;
import com.monopoly.monopoly_managment.domain.property.values.OwnerId;
import com.monopoly.monopoly_managment.domain.property.values.PropertyId;
import com.monopoly.monopoly_managment.domain.property.values.TypeImprovementEnum;
import com.monopoly.monopoly_managment.domain.property.values.UpgradeId;
import com.monopoly.shared.domain.generic.DomainActionsContainer;

public class PropertyHandler extends DomainActionsContainer {
    private final Property property;
    public PropertyHandler(Property property) {
        this.property = property;
    }

  public void makeImprovement(UpgradeId improvementId, PropertyId propertyId, TypeImprovementEnum type, Cost cost) {
    property.validateSufficientBalance(cost);
    property.validateMaximumDevelopmentLevel();
    property.validateOwnerMonopoly();
    property.getImprovements().build();
    property.subtractBalance(cost.getValue());
    property.madeImprovement(improvementId, propertyId, type, cost);
  }

  public void demolishImprovement(UpgradeId improvementId, PropertyId propertyId, TypeImprovementEnum type, Cost cost) {
    if (property.getDevelopmentLevel() == 0) {
      throw new RuntimeException("The property is already at its minimum level");
    }
    property.getImprovements().downgrade();
    property.addBalance(cost.getValue());
    property.demolishedImprovement(improvementId, propertyId, type, cost);
  }

  public void mortgage(OwnerId ownerId, Amount amount) {
    if (property.getMortgage().getIsMortgaged().getValue()) {
      throw new RuntimeException("The property is already mortgaged");
    }
    property.getMortgage().activate();
    property.mortgaged(ownerId, property.getIdentity(), amount);
  }

  public void cancelMortgage(OwnerId ownerId, Amount amount) {
    if (!property.getMortgage().getIsMortgaged().getValue()) {
      throw new RuntimeException("The property is not mortgaged");
    }
    property.validateCancelMortgage();
    property.getMortgage().cancel();
    property.canceledMortgage(ownerId, property.getIdentity(), amount);
  }

  public void assignOwner(OwnerId ownerId) {
    if ( property.getOwner().getIdentity().getValue() != null) {
      throw new RuntimeException("The property already has an owner");
    }
    property.getOwner().acquireProperty(property.getIdentity());
    property.getContract().sign(ownerId);
    property.assignedOwner(ownerId, property.getIdentity());
  }

  public void removeOwner(OwnerId ownerId) {
    if ( property.getOwner().getIdentity().getValue() == null) {
      throw new RuntimeException("The property does not have an owner");
    }
    property.getContract().cancel();
    property.getOwner().sellProperty(property.getIdentity());
    property.removedOwner(ownerId, property.getIdentity());
  }

  public void modifyOwner(OwnerId ownerId, Owner previousOwner) {
    if ( property.getOwner().getIdentity().getValue() == null) {
      throw new RuntimeException("The property does not have an owner");
    }
    property.getOwner().transferProperty(property.getIdentity(), previousOwner);
    property.getContract().cancel();
    previousOwner.sellProperty(property.getIdentity());
    property.modifiedOwner(ownerId, property.getIdentity(), previousOwner.getIdentity());
  }
}
