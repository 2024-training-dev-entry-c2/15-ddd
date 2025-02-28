package com.twilightimperium.expansioncommand.domain.faction;

import com.twilightimperium.expansioncommand.domain.faction.entities.ConqueredFaction;
import com.twilightimperium.expansioncommand.domain.faction.entities.Government;
import com.twilightimperium.expansioncommand.domain.faction.entities.Technology;
import com.twilightimperium.expansioncommand.domain.faction.entities.Unit;
import com.twilightimperium.expansioncommand.domain.faction.events.ConqueredFactionAdded;
import com.twilightimperium.expansioncommand.domain.faction.events.ConqueredFactionLost;
import com.twilightimperium.expansioncommand.domain.faction.events.ConqueredFactionPercentageUpdated;
import com.twilightimperium.expansioncommand.domain.faction.events.FactionCreated;
import com.twilightimperium.expansioncommand.domain.faction.events.FactionSurrendered;
import com.twilightimperium.expansioncommand.domain.faction.events.GovernmentLevelDecreased;
import com.twilightimperium.expansioncommand.domain.faction.events.GovernmentLevelIncreased;
import com.twilightimperium.expansioncommand.domain.faction.events.GovernmentTypeChanged;
import com.twilightimperium.expansioncommand.domain.faction.events.TechnologyAdded;
import com.twilightimperium.expansioncommand.domain.faction.events.TechnologyLevelIncreased;
import com.twilightimperium.expansioncommand.domain.faction.events.UnitCapacityIncreased;
import com.twilightimperium.expansioncommand.domain.faction.events.UnitCostReduced;
import com.twilightimperium.expansioncommand.domain.faction.events.UnitCreated;
import com.twilightimperium.expansioncommand.domain.faction.events.UnitRemoved;
import com.twilightimperium.expansioncommand.domain.faction.values.CombatPower;
import com.twilightimperium.expansioncommand.domain.faction.values.Capacity;
import com.twilightimperium.expansioncommand.domain.faction.values.Cost;
import com.twilightimperium.expansioncommand.domain.faction.values.Description;
import com.twilightimperium.expansioncommand.domain.faction.values.IsSurrendered;
import com.twilightimperium.expansioncommand.domain.faction.values.Level;
import com.twilightimperium.expansioncommand.domain.faction.values.Movement;
import com.twilightimperium.expansioncommand.domain.faction.values.Name;
import com.twilightimperium.expansioncommand.domain.faction.values.Percentage;
import com.twilightimperium.expansioncommand.domain.faction.values.Type;
import com.twilightimperium.shared.domain.generic.DomainActionsContainer;
import com.twilightimperium.shared.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.function.Consumer;

public class FactionHandler extends DomainActionsContainer {
    public FactionHandler(Faction faction) {
        add(createFaction(faction));
        add(createUnit(faction));
        add(removeUnit(faction));
        add(increaseUnitCapacity(faction));
        add(reduceUnitCost(faction));
        add(increaseGovernmentLevel(faction));
        add(decreaseGovernmentLevel(faction));
        add(changeGovernmentType(faction));
        add(addTechnology(faction));
        add(addConqueredFaction(faction));
        add(removeConqueredFaction(faction));
        add(surrenderFaction(faction));
        add(increaseTechnologyLevel(faction));

    }

    public Consumer<? extends DomainEvent> createFaction(Faction faction) {
        return (FactionCreated event) -> {
            faction.setName(Name.of(event.getName()));
            faction.setDescription(Description.of(event.getDescription()));

            faction.setIsSurrendered(IsSurrendered.of(false));
            faction.setUnitsList(new ArrayList<>());
            faction.setTechnologiesList(new ArrayList<>());
            faction.setConqueredFactionsList(new ArrayList<>());
            faction.setGovernment(new Government(Type.of("Tribal"), Level.of(1)));
        };
    }

    public Consumer<? extends DomainEvent> createUnit(Faction faction) {
        return (UnitCreated event) -> {
            Unit newUnit = new Unit(
                    CombatPower.of(event.getCombatPower()),
                    Capacity.of(event.getCapacity()),
                    Cost.of(event.getCost()),
                    Type.of(event.getType()),
                    Movement.of(event.getMovement())
            );

            if (faction.getUnitsList() == null) {
                faction.setUnitsList(new ArrayList<>());
            }

            faction.getUnitsList().add(newUnit);
        };
    }

    public Consumer<? extends DomainEvent> removeUnit(Faction faction) {
        return (UnitRemoved event) -> {
            faction.getUnitsList().removeIf(unit -> unit.getIdentity().getValue().equals(event.getId()));
        };
    }

    public Consumer<? extends DomainEvent> increaseUnitCapacity(Faction faction) {
        return (UnitCapacityIncreased event) -> {
            faction.getUnitsList().stream()
                    .filter(unit -> unit.getIdentity().getValue().equals(event.getId()))
                    .findFirst()
                    .ifPresent(unit -> unit.increaseCapacity());
        };
    }

    public Consumer<? extends DomainEvent> reduceUnitCost(Faction faction) {
        return (UnitCostReduced event) -> {
            faction.getUnitsList().stream()
                    .filter(unit -> unit.getIdentity().getValue().equals(event.getId()))
                    .findFirst()
                    .ifPresent(unit -> unit.reduceCost(event.getCost()));
        };
    }

    public Consumer<? extends DomainEvent> increaseGovernmentLevel(Faction faction) {
        return (GovernmentLevelIncreased event) -> {
            faction.getGovernment().increaseLevel(event.getLevel());
        };
    }

    public Consumer<? extends DomainEvent> decreaseGovernmentLevel(Faction faction) {
        return (GovernmentLevelDecreased event) -> {
            faction.getGovernment().decreaseLevel(event.getLevel());
        };
    }

    public Consumer<? extends DomainEvent> changeGovernmentType(Faction faction) {
        return (GovernmentTypeChanged event) -> {
            faction.getGovernment().setType(Type.of(event.getType()));
        };
    }

    public Consumer<? extends DomainEvent> addTechnology(Faction faction) {
        return (TechnologyAdded event) -> {
            if (faction.getTechnologiesList() == null) {
                faction.setTechnologiesList(new ArrayList<>());
            }

            Technology newTechnology = new Technology(
                    Name.of(event.getName()),
                    Level.of(event.getLevel())
            );

            faction.getTechnologiesList().add(newTechnology);
        };
    }

    public Consumer<? extends DomainEvent> addConqueredFaction(Faction faction) {
        return (ConqueredFactionAdded event) -> {
            if (faction.getConqueredFactionsList() == null) {
                faction.setConqueredFactionsList(new ArrayList<>());
            }

            ConqueredFaction newConqueredFaction = new ConqueredFaction(
                    Name.of(event.getName()),
                    Percentage.of(event.getPercentage())
            );

            faction.getConqueredFactionsList().add(newConqueredFaction);
        };
    }

    public Consumer<? extends DomainEvent> removeConqueredFaction(Faction faction) {
        return (ConqueredFactionLost event) -> {
            if (faction.getConqueredFactionsList() != null) {
                faction.getConqueredFactionsList().removeIf(conqueredFaction ->
                        conqueredFaction.getIdentity().getValue().equals(event.getId())
                );
            }
        };
    }

    public Consumer<? extends DomainEvent> surrenderFaction(Faction faction) {
        return (FactionSurrendered event) -> {
            faction.setIsSurrendered(IsSurrendered.of(event.getIsSurrendered()));
        };
    }

    public Consumer<? extends DomainEvent> increaseTechnologyLevel(Faction faction) {
        return (TechnologyLevelIncreased event) -> {
            if (faction.getTechnologiesList() != null) {
                faction.getTechnologiesList().forEach(technology -> technology.increaseLevel(event.getLevel()));
            }
        };
    }

    public Consumer<? extends DomainEvent> updateConqueredFactionPercentage(Faction faction) {
        return (ConqueredFactionPercentageUpdated event) -> {
            if (faction.getConqueredFactionsList() != null) {
                faction.getConqueredFactionsList().stream()
                        .filter(conqueredFaction -> conqueredFaction.getIdentity().getValue().equals(event.getId()))
                        .findFirst()
                        .ifPresent(conqueredFaction -> conqueredFaction.updatePercentage(event.getPercentage()));
            }
        };
    }


}
