package com.twilightimperium.expansioncommand.domain.faction.events;

import com.twilightimperium.shared.domain.generic.DomainEvent;

public class GovernmentLevelDecreased extends DomainEvent {
    private final String type;
    private final Integer level;

    public GovernmentLevelDecreased(String type, Integer level) {
        super(EventsEnum.GOVERNMENT_LEVEL_DECREASED.name());
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
