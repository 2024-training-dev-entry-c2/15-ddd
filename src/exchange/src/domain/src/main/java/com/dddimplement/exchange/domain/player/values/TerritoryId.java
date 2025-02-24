package com.dddimplement.exchange.domain.player.values;

import com.dddimplement.exchange.domain.player.entities.Territory;
import com.dddimplement.shared.domain.generic.Identity;

public class TerritoryId extends Identity {

    public TerritoryId() {
        super();
    }

    private TerritoryId(String id) {
        super(id);
    }

    public static TerritoryId of(String id) {
        return new TerritoryId(id);
    }
}
