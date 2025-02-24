package com.dddimplement.exchange.domain.player.events;

import com.dddimplement.exchange.domain.player.values.TurnFaseEnum;
import com.dddimplement.shared.domain.generic.DomainEvent;

public class TurnStarted extends DomainEvent {

    private final TurnFaseEnum turnFase;

    public TurnStarted(TurnFaseEnum turnFase) {
        super(EventsEnum.TURN_STARTED.name());
        this.turnFase = turnFase;
    }

    public TurnFaseEnum getTurnFase() {
        return turnFase;
    }
}
