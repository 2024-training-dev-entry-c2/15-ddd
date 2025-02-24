package com.dddimplement.exchange.domain.player.events;

import com.dddimplement.exchange.domain.player.values.TurnFaseEnum;
import com.dddimplement.shared.domain.generic.DomainEvent;

public class TurnEnded extends DomainEvent {

    private final TurnFaseEnum turnEnded;

    public TurnEnded(TurnFaseEnum turnEnded) {
        super(EventsEnum.TURN_ENDED.name());
        this.turnEnded = turnEnded;
    }

    public TurnFaseEnum getTurnEnded() {
        return turnEnded;
    }
}
