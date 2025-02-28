package com.twilightimperium.expansioncommand.domain.system.entities;

import com.twilightimperium.expansioncommand.domain.system.values.Influence;
import com.twilightimperium.expansioncommand.domain.system.values.InvadingUnitCount;
import com.twilightimperium.expansioncommand.domain.system.values.OwningFaction;
import com.twilightimperium.expansioncommand.domain.system.values.PlanetId;
import com.twilightimperium.expansioncommand.domain.system.values.Resource;
import com.twilightimperium.shared.domain.generic.Entity;

public class Planet extends Entity<PlanetId> {
    private OwningFaction owningFaction;
    private Resource resource;
    private Influence influence;
    private InvadingUnitCount invadingUnitCount;

    // region Constructors
    public Planet(OwningFaction owningFaction, Resource resource, Influence influence, InvadingUnitCount invadingUnitCount) {
        super(new PlanetId());
        this.owningFaction = owningFaction;
        this.resource = resource;
        this.influence = influence;
        this.invadingUnitCount = invadingUnitCount;
    }

    public Planet(PlanetId identity, OwningFaction owningFaction, Resource resource, Influence influence, InvadingUnitCount invadingUnitCount) {
        super(identity);
        this.owningFaction = owningFaction;
        this.resource = resource;
        this.influence = influence;
        this.invadingUnitCount = invadingUnitCount;
    }
    // endregion

    // region Getters and Setters
    public Influence getInfluence() {
        return influence;
    }

    public void setInfluence(Influence influence) {
        this.influence = influence;
    }

    public InvadingUnitCount getInvadingUnitCount() {
        return invadingUnitCount;
    }

    public void setInvadingUnitCount(InvadingUnitCount invadingUnitCount) {
        this.invadingUnitCount = invadingUnitCount;
    }

    public OwningFaction getOwningFaction() {
        return owningFaction;
    }

    public void setOwningFaction(OwningFaction owningFaction) {
        this.owningFaction = owningFaction;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
    // endregion

    // region Public Methods
    public void addInvadingUnit(Integer count) {
        this.invadingUnitCount = InvadingUnitCount.of(this.invadingUnitCount.getValue() + count);
    }

    public void removeInvadingUnit(Integer count) {
        int newCount = this.invadingUnitCount.getValue() - count;
        this.invadingUnitCount = InvadingUnitCount.of(newCount);
    }
    // endregion

}
