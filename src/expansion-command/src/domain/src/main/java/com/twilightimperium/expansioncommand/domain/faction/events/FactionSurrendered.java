package com.twilightimperium.expansioncommand.domain.faction.events;

import com.twilightimperium.shared.domain.generic.DomainEvent;

public class FactionSurrendered extends DomainEvent {
    private final Boolean isSurrendered;

    public FactionSurrendered(Boolean isSurrendered) {
        super(EventsEnum.FACTION_SURRENDER.name());
        this.isSurrendered = isSurrendered;
    }
    
    public Boolean getIsSurrendered() {
        return isSurrendered;
    }
}
