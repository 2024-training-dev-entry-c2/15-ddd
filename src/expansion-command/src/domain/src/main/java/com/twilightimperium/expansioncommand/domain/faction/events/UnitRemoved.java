package com.twilightimperium.expansioncommand.domain.faction.events;

import com.twilightimperium.shared.domain.generic.DomainEvent;

public class UnitRemoved extends DomainEvent {
    private final String id;


    public UnitRemoved(String id) {
        super(EventsEnum.UNIT_REMOVED.name());
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
