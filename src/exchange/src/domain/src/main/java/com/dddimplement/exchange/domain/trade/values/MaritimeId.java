package com.dddimplement.exchange.domain.trade.values;

import com.dddimplement.shared.domain.generic.Identity;

public class MaritimeId extends Identity {

    public MaritimeId() {
        super();
    }

    private MaritimeId(String id) {
        super(id);
    }

    public static MaritimeId of(String id) {
        return new MaritimeId(id);
    }
}
