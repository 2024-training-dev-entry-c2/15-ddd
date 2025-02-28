package com.twilightimperium.expansioncommand.domain.faction.events;

import com.twilightimperium.shared.domain.generic.DomainEvent;

public class ConqueredFactionPercentageUpdated extends DomainEvent {
    private final String id;
    private final Integer percentage;

    public ConqueredFactionPercentageUpdated(String id, Integer percentage) {
        super(EventsEnum.CONQUERED_FACTION_PERCENTAGE_UPDATED.name());
        this.id = id;
        this.percentage = percentage;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public String getId() {
        return id;
    }
}
