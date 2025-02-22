package com.monopoly.monopoly_managment.domain.property;

import com.monopoly.monopoly_managment.domain.property.events.CreatedProperty;
import com.monopoly.monopoly_managment.domain.property.events.DemolishedImprovement;
import com.monopoly.monopoly_managment.domain.property.events.MadeImprovement;
import com.monopoly.monopoly_managment.domain.property.events.MortgageCanceled;
import com.monopoly.monopoly_managment.domain.property.events.MortgageConstituted;
import com.monopoly.monopoly_managment.domain.property.events.OwnerAssigned;
import com.monopoly.monopoly_managment.domain.property.events.OwnerModified;
import com.monopoly.monopoly_managment.domain.property.events.OwnerRemoved;
import com.monopoly.monopoly_managment.domain.property.values.ColorGroup;
import com.monopoly.monopoly_managment.domain.property.values.ContractId;
import com.monopoly.monopoly_managment.domain.property.values.MortgageId;
import com.monopoly.monopoly_managment.domain.property.values.Name;
import com.monopoly.monopoly_managment.domain.property.values.Price;
import com.monopoly.monopoly_managment.domain.property.values.UpgradeId;
import com.monopoly.shared.domain.generic.DomainActionsContainer;
import com.monopoly.shared.domain.generic.DomainEvent;

import java.util.function.Consumer;

public class PropertyHandler extends DomainActionsContainer {
    private final Property property;
    public PropertyHandler(Property property) {
        this.property = property;
        add(createProperty());
        add(makeImprovement());
        add(demolishImprovement());
        add(mortgage());
        add(cancelMortgage());
        add(assignOwner());
        add(removeOwner());
        add(modifyOwner());
    }

    public Consumer<? extends DomainEvent> createProperty(){
      return (CreatedProperty event) -> {
        property.setOwner(event.getOwnerId());
        property.setName(Name.of(event.getName()));
        property.setPrice(Price.of(event.getPrice()));
        property.setColorGroup(ColorGroup.of(event.getColorGroup()));
        property.setContract(ContractId.of(event.getContractId()));
        property.setMortgage(MortgageId.of(event.getMortgageId()));
        property.setImprovements(UpgradeId.of(event.getImprovementsId()));
      };
    }


  public Consumer<? extends DomainEvent> makeImprovement() {
    return (MadeImprovement event) ->{
      property.validateSufficientBalance(event.getCost());
    property.validateMaximumDevelopmentLevel();
    property.validateOwnerMonopoly();
    property.buildImprovement(event.getImprovementId(),event.getPropertyId(),event.getType(), event.getCost());
    property.subtractBalance(event.getCost());
    };
  }

  public Consumer<? extends DomainEvent> demolishImprovement() {
    return (DemolishedImprovement event) -> {
    property.downgradeImprovement(event.getImprovementId(),event.getPropertyId(),event.getType(), event.getCost());
    property.addBalance(event.getCost());
    };
  }

  public Consumer<? extends DomainEvent> mortgage() {
    return (MortgageConstituted event) -> {
      if (property.getIsMortgaged().getIsMortgaged().getValue()) {
      throw new RuntimeException("The property is already mortgaged");
    }
    property.getMortgageById(event.getAggregateName(), true);
    property.addBalance(event.getAmount());
    };
  }

  public Consumer<? extends DomainEvent> cancelMortgage() {
    return (MortgageCanceled event) -> {

    property.getMortgageById(event.getAggregateName(), false);
    property.subtractBalance(event.getAmount());
  };}

  public Consumer<? extends DomainEvent> assignOwner() {
    return (OwnerAssigned event) -> {
    property.setOwner(property.getOwnerIdById(event.getOwnerId()).getValue());
    };
  }

  public Consumer<? extends DomainEvent> removeOwner() {
    return (OwnerRemoved event) -> {
      property.setOwner(null);
    };
  }

  public Consumer<? extends DomainEvent> modifyOwner() {
    return (OwnerModified event) -> {
      property.setOwner(property.getOwnerIdById(event.getOwnerId()).getValue());
  };
}
}
