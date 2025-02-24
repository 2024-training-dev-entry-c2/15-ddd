package com.dddimplement.exchange.domain.player.events;

import com.dddimplement.exchange.domain.player.values.TerritoryTypeEnum;
import com.dddimplement.shared.domain.generic.DomainEvent;

public class TerritoryExpanded extends DomainEvent {
    private final String territoryType;

    public TerritoryExpanded(String territoryType) {
        super(EventsEnum.TERRITORY_EXPANDED.name());
        this.territoryType = territoryType;
    }

    public String getTerritoryType() {
        return territoryType;
    }
}
