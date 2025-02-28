package com.twilightimperium.expansioncommand.domain.faction.events;

import com.twilightimperium.shared.domain.generic.DomainEvent;

public class GovernmentTypeChanged extends DomainEvent {
    private final String type;
    private final Integer level;

    public GovernmentTypeChanged(String type, Integer level) {
        super(EventsEnum.GOVERNMENT_TYPE_CHANGED.name());
        this.type = type;
        this.level = level;
    }

    public Integer getLevel() {
        return level;
    }

    public String getType() {
        return type;
    }
}
