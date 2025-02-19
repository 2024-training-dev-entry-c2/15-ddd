package com.monopoly.monopoly_managment.domain.property;

import com.monopoly.monopoly_managment.domain.property.events.DemolishedImprovement;
import com.monopoly.monopoly_managment.domain.property.events.MadeImprovement;
import com.monopoly.monopoly_managment.domain.property.events.MortgageCanceled;
import com.monopoly.monopoly_managment.domain.property.events.MortgageConstituted;
import com.monopoly.monopoly_managment.domain.property.events.OwnerAssigned;
import com.monopoly.monopoly_managment.domain.property.events.OwnerModified;
import com.monopoly.monopoly_managment.domain.property.events.OwnerRemoved;
import com.monopoly.shared.domain.generic.DomainActionsContainer;
import com.monopoly.shared.domain.generic.DomainEvent;

import java.util.function.Consumer;

public class PropertyHandler extends DomainActionsContainer {
    private final Property property;
    public PropertyHandler(Property property) {
        this.property = property;
        add(makeImprovement());
        add(demolishImprovement());
        add(mortgage());
        add(cancelMortgage());
        add(assignOwner());
        add(removeOwner());
        add(modifyOwner());
    }

  public Consumer<? extends DomainEvent> makeImprovement() {
    return (MadeImprovement event) ->{
      property.validateSufficientBalance(event.getCost());
    property.validateMaximumDevelopmentLevel();
    property.validateOwnerMonopoly();
    property.getImprovements().build();
    property.subtractBalance(event.getCost());
    };
  }

  public Consumer<? extends DomainEvent> demolishImprovement() {
    return (DemolishedImprovement event) -> {

    property.getImprovements().downgrade();
    property.addBalance(event.getCost());
    };
  }

  public Consumer<? extends DomainEvent> mortgage() {
    return (MortgageConstituted event) -> {
      if (property.getMortgage().getIsMortgaged().getValue()) {
      throw new RuntimeException("The property is already mortgaged");
    }
    property.getMortgage().activate();
    property.addBalance(event.getAmount());
    };
  }

  public Consumer<? extends DomainEvent> cancelMortgage() {
    return (MortgageCanceled event) -> {

    property.getMortgage().cancel();
    property.subtractBalance(event.getAmount());
  };}

  public Consumer<? extends DomainEvent> assignOwner() {
    return (OwnerAssigned event) -> {
    property.setOwner(property.getOwnerById(event.getOwnerId()));
    };
  }

  public Consumer<? extends DomainEvent> removeOwner() {
    return (OwnerRemoved event) -> {
      property.setOwner(null);
    };
  }

  public Consumer<? extends DomainEvent> modifyOwner() {
    return (OwnerModified event) -> {
      property.setOwner(property.getOwnerById(event.getOwnerId()));
  };
}
}
