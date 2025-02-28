package com.twilightimperium.expansioncommand.domain.system.events;

import com.twilightimperium.shared.domain.generic.DomainEvent;

public class InvadingUnitAdded extends DomainEvent {
    private final String type;

    public InvadingUnitAdded(String type) {
        super(EventsEnum.INVADING_UNIT_ADDED.name());
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
