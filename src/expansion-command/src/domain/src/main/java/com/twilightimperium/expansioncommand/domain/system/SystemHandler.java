package com.twilightimperium.expansioncommand.domain.system;

import com.twilightimperium.expansioncommand.domain.faction.values.FactionId;
import com.twilightimperium.expansioncommand.domain.system.entities.Planet;
import com.twilightimperium.expansioncommand.domain.system.events.InvadingUnitAdded;
import com.twilightimperium.expansioncommand.domain.system.events.InvadingUnitRemoved;
import com.twilightimperium.expansioncommand.domain.system.events.PlanetAdded;
import com.twilightimperium.expansioncommand.domain.system.events.PlanetOwningFactionUpdated;
import com.twilightimperium.expansioncommand.domain.system.events.SystemCreated;
import com.twilightimperium.expansioncommand.domain.system.events.SystemOwningFactionUpdated;
import com.twilightimperium.expansioncommand.domain.system.values.Influence;
import com.twilightimperium.expansioncommand.domain.system.values.InvadingUnitCount;
import com.twilightimperium.expansioncommand.domain.system.values.OwningFaction;
import com.twilightimperium.expansioncommand.domain.system.values.Resource;
import com.twilightimperium.expansioncommand.domain.system.values.SystemNumber;
import com.twilightimperium.shared.domain.generic.DomainActionsContainer;
import com.twilightimperium.shared.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.function.Consumer;

public class SystemHandler extends DomainActionsContainer {
    public SystemHandler(System system) {
        add(createSystem(system));
        add(addPlanet(system));
        add(updateSystemOwningFaction(system));
        add(addInvadingUnitToPlanet(system));
        add(removeInvadingUnitFromPlanet(system));
        add(updatePlanetOwningFaction(system));
    }

    public Consumer<? extends DomainEvent> createSystem(System system) {
        return (SystemCreated event) -> {
            system.setNumber(SystemNumber.of(event.getNumber()));
            system.setFactionId(FactionId.of(event.getFactionId()));
            system.setPlanetsList(new ArrayList<>());
        };
    }

    public Consumer<? extends DomainEvent> addPlanet(System system) {
        return (PlanetAdded event) -> {
            Planet newPlanet = new Planet(
                    OwningFaction.of(event.getOwningFaction()),
                    Resource.of(event.getResource()),
                    Influence.of(event.getInfluence()),
                    InvadingUnitCount.of(event.getInvadingUnits())
            );

            if (system.getPlanetsList() == null) {
                system.setPlanetsList(new ArrayList<>());
            }

            system.getPlanetsList().add(newPlanet);
        };
    }

    public Consumer<? extends DomainEvent> updateSystemOwningFaction(System system) {
        return (SystemOwningFactionUpdated event) -> {
            system.setFactionId(FactionId.of(event.getId()));
        };
    }

    public Consumer<? extends DomainEvent> addInvadingUnitToPlanet(System system) {
        return (InvadingUnitAdded event) -> {
            system.getPlanetsList().stream()
                    .findFirst()
                    .ifPresent(planet -> {
                        planet.addInvadingUnit(1);
                    });
        };
    }

    public Consumer<? extends DomainEvent> removeInvadingUnitFromPlanet(System system) {
        return (InvadingUnitRemoved event) -> {
            system.getPlanetsList().stream()
                    .findFirst()
                    .ifPresent(planet -> {
                        planet.removeInvadingUnit(1);
                    });
        };
    }

    public Consumer<? extends DomainEvent> updatePlanetOwningFaction(System system) {
        return (PlanetOwningFactionUpdated event) -> {
            system.getPlanetsList().stream()
                    .filter(planet -> planet.getIdentity().getValue().equals(event.getId()))
                    .findFirst()
                    .ifPresent(planet -> planet.setOwningFaction(OwningFaction.of(event.getOwningFaction())));
        };
    }

}
