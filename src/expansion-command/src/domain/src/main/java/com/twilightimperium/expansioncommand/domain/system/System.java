package com.twilightimperium.expansioncommand.domain.system;

import com.twilightimperium.expansioncommand.domain.faction.values.FactionId;
import com.twilightimperium.expansioncommand.domain.system.entities.Planet;
import com.twilightimperium.expansioncommand.domain.system.events.InvadingUnitAdded;
import com.twilightimperium.expansioncommand.domain.system.events.InvadingUnitRemoved;
import com.twilightimperium.expansioncommand.domain.system.events.PlanetAdded;
import com.twilightimperium.expansioncommand.domain.system.events.PlanetOwningFactionUpdated;
import com.twilightimperium.expansioncommand.domain.system.events.SystemCreated;
import com.twilightimperium.expansioncommand.domain.system.events.SystemOwningFactionUpdated;
import com.twilightimperium.expansioncommand.domain.system.values.SystemId;
import com.twilightimperium.expansioncommand.domain.system.values.SystemNumber;
import com.twilightimperium.shared.domain.generic.AggregateRoot;

import java.util.List;

public class System extends AggregateRoot<SystemId> {
    private FactionId factionId;
    private SystemNumber number;
    private List<Planet> planetsList;

    // region Constructors
    private System(SystemId identity) {
        super(identity);
    }

    public System(String factionId, Integer number, List<String> planetsList) {
        super(new SystemId());
        apply(new SystemCreated(number, factionId, planetsList));
        subscribe(new SystemHandler(this));
    }
    // endregion

    // region Getters and Setters
    public FactionId getFactionId() {
        return factionId;
    }

    public void setFactionId(FactionId factionId) {
        this.factionId = factionId;
    }

    public SystemNumber getNumber() {
        return number;
    }

    public void setNumber(SystemNumber number) {
        this.number = number;
    }

    public List<Planet> getPlanetsList() {
        return planetsList;
    }

    public void setPlanetsList(List<Planet> planetsList) {
        this.planetsList = planetsList;
    }
    // endregion

    // region Domain Actions
    public void addPlanet(String systemId, String owningFaction, Integer resource, Integer influence, Integer invadingUnits) {
        apply(new PlanetAdded(systemId, owningFaction, resource, influence, invadingUnits));
    }

    public void updateSystemOwningFaction(String id) {
        apply(new SystemOwningFactionUpdated(id));
    }

    public void changePlanetOwningFaction(String id, String owningFaction) {
        apply(new PlanetOwningFactionUpdated(id, owningFaction));
    }

    public void addInvadingUnitToPlanet(String type) {
        apply(new InvadingUnitAdded(type));
    }

    public void removeInvadingUnitFromPlanet(String id) {
        apply(new InvadingUnitRemoved(id));
    }
    // endregion

}
