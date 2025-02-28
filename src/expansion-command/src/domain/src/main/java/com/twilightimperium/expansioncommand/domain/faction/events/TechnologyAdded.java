package com.twilightimperium.expansioncommand.domain.faction.events;

import com.twilightimperium.shared.domain.generic.DomainEvent;

public class TechnologyAdded extends DomainEvent {
    private final String name;
    private final Integer level;

    public TechnologyAdded(String name, Integer level) {
        super(EventsEnum.TECHNOLOGY_ADDED.name());
        this.name = name;
        this.level = level;
    }

    public Integer getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }
}
