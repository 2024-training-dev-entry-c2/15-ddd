package com.twilightimperium.expansioncommand.domain.system.events;

import com.twilightimperium.shared.domain.generic.DomainEvent;

public class SystemOwningFactionUpdated extends DomainEvent {
    private final String id;

    public SystemOwningFactionUpdated(String id) {
        super(EventsEnum.SYSTEM_OWNING_FACTION_UPDATED.name());
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
