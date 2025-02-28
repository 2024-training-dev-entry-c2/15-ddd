package com.twilightimperium.expansioncommand.domain.faction.events;

import com.twilightimperium.shared.domain.generic.DomainEvent;

public class ConqueredFactionLost extends DomainEvent {
    private final String id;

    public ConqueredFactionLost(String id) {
        super(EventsEnum.CONQUERED_FACTION_LOST.name());
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
