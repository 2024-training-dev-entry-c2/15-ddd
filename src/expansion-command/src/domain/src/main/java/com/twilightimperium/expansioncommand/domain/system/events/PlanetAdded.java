package com.twilightimperium.expansioncommand.domain.system.events;

import com.twilightimperium.shared.domain.generic.DomainEvent;

public class PlanetAdded extends DomainEvent {
    private final String systemId;
    private final String owningFaction;
    private final Integer resource;
    private final Integer influence;
    private final Integer invadingUnits;

    public PlanetAdded(String systemId, String owningFaction, Integer resource, Integer influence, Integer invadingUnits) {
        super(EventsEnum.PLANET_ADDED.name());
        this.systemId = systemId;
        this.owningFaction = owningFaction;
        this.resource = resource;
        this.influence = influence;
        this.invadingUnits = invadingUnits;
    }

    public String getSystemId() {
        return systemId;
    }

    public String getOwningFaction() {
        return owningFaction;
    }

    public Integer getResource() {
        return resource;
    }

    public Integer getInfluence() {
        return influence;
    }

    public Integer getInvadingUnits() {
        return invadingUnits;
    }
}
