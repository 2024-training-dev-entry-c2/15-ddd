package com.dddimplement.exchange.domain.player.events;

import com.dddimplement.shared.domain.generic.DomainEvent;

public class TerritoryImproved extends DomainEvent {

    private final String territoryType;

    public TerritoryImproved( String territoryType) {
        super(EventsEnum.TERRITORY_IMPROVED.name());
        this.territoryType = territoryType;
    }

    public String getTerritoryType() {
        return territoryType;
    }
}
