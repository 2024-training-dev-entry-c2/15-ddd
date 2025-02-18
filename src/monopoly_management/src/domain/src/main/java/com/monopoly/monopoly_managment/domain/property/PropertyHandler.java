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
      if (property.getDevelopmentLevel() == 0) {
      throw new RuntimeException("The property is already at its minimum level");
    }
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
      if (!property.getMortgage().getIsMortgaged().getValue()) {
      throw new RuntimeException("The property is not mortgaged");
    }
    property.validateCancelMortgage();
    property.getMortgage().cancel();
    property.subtractBalance(event.getAmount());
  };}

  public Consumer<? extends DomainEvent> assignOwner() {
    return (OwnerAssigned event) -> {
      if (property.getOwner().getIdentity().getValue() != null) {
        throw new RuntimeException("The property already has an owner");
      }
      property.getOwner().acquireProperty(property.getIdentity());
      property.getContract().sign(event.getOwnerId());
    };
  }

  public Consumer<? extends DomainEvent> removeOwner() {
    return (OwnerRemoved event) -> {
      if ( property.getOwner().getIdentity().getValue().equals(event.getPropertyId())) {
        if ( property.getOwner().getIdentity().getValue() == null) {
      throw new RuntimeException("The property does not have an owner");
        }
    property.getOwner().transferProperty(property.getIdentity(), property.getOwner());
    }
    property.getContract().cancel();
    property.getOwner().sellProperty(property.getIdentity());
    property.addBalance(property.getPrice().getValue());
    };
  }

  public Consumer<? extends DomainEvent> modifyOwner() {
    return (OwnerModified event) -> {
      if ( property.getOwner().getIdentity().getValue().equals( event.getPreviousOwnerId())) {
        if ( property.getOwner().getIdentity().getValue() == null) {
      throw new RuntimeException("The property does not have an owner");
        }
    }
    property.getOwner().transferProperty(property.getIdentity(), property.getOwnerById(event.getOwnerId()));
    property.getContract().cancel();
    property.getOwnerById(event.getOwnerId()).sellProperty(property.getIdentity());
  };
}
}
