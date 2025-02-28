package com.twilightimperium.expansioncommand.domain.faction.events;

import com.twilightimperium.shared.domain.generic.DomainEvent;

public class FactionCreated extends DomainEvent {
    private final String name;
    private final String description;
    private final Boolean isSurrendered;
    private final String governmentType;
    private final Integer governmentLevel;

    public FactionCreated(String name, String description, boolean isSurrendered, String governmentType, int governmentLevel) {
        super(EventsEnum.FACTION_CREATED.name());
        this.name = name;
        this.description = description;
        this.isSurrendered = isSurrendered;
        this.governmentType = governmentType;
        this.governmentLevel = governmentLevel;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String getName() {
        return name;
    }

    public boolean isSurrendered() {
        return isSurrendered;
    }

    public String getGovernmentType() {
        return governmentType;
    }

    public int getGovernmentLevel() {
        return governmentLevel;
    }
}
