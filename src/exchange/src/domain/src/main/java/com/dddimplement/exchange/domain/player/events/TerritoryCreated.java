package com.dddimplement.exchange.domain.player.events;

import com.dddimplement.shared.domain.generic.DomainEvent;

public class TerritoryCreated extends DomainEvent {
    private final String territoryType;

    public TerritoryCreated(String territoryType  ) {
        super(EventsEnum.TERRITORY_CREATED.name());
        this.territoryType = territoryType;
    }

    public String getTerritoryType() {
        return territoryType;
    }
}
