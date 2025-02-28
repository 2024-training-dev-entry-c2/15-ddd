package com.twilightimperium.expansioncommand.domain.faction.events;

import com.twilightimperium.shared.domain.generic.DomainEvent;

public class ConqueredFactionAdded extends DomainEvent {
    private final String name;
    private final Integer percentage;

    public ConqueredFactionAdded(String name, Integer percentage) {
        super(EventsEnum.CONQUERED_FACTION_ADDED.name());
        this.name = name;
        this.percentage = percentage;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public String getName() {
        return name;
    }
}
