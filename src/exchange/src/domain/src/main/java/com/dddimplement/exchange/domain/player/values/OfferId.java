package com.dddimplement.exchange.domain.player.values;

import com.dddimplement.shared.domain.generic.Identity;

public class OfferId extends Identity {

    public OfferId() {
        super();
    }

    private OfferId(String id) {
        super(id);
    }

    public static OfferId of(String id) {
        return new OfferId(id);
    }


}
