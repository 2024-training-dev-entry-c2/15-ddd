package com.twilightimperium.expansioncommand.domain.faction.events;

import com.twilightimperium.shared.domain.generic.DomainEvent;

public class GovernmentLevelIncreased extends DomainEvent {
    private final Integer level;

    public GovernmentLevelIncreased(Integer level) {
        super(EventsEnum.GOVERNMENT_LEVEL_INCREASED.name());
        this.level = level;
    }

    public Integer getLevel() {
        return level;
    }


}
