package com.dddimplement.exchange.domain.player.values;

import com.dddimplement.shared.domain.generic.Identity;

public class TurnId extends Identity {

    public TurnId() {
        super();
    }

    private TurnId(String id) {
        super(id);
    }

    public static TurnId of(String id) {
        return new TurnId(id);
    }
}
