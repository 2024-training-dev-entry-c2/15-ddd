package com.twilightimperium.expansioncommand.domain.faction.events;

import com.twilightimperium.shared.domain.generic.DomainEvent;

public class TechnologyLevelIncreased extends DomainEvent {
    private final Integer level;

    public TechnologyLevelIncreased(Integer level) {
        super(EventsEnum.TECHNOLOGY_LEVEL_INCREASED.name());
        this.level = level;
    }

    public Integer getLevel() {
        return level;
    }
}
