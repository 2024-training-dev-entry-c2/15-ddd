package com.dddimplement.exchange.domain.trade.events;

import com.dddimplement.exchange.domain.trade.values.TradeType;
import com.dddimplement.shared.domain.generic.DomainEvent;

public class TradeSelected extends DomainEvent {
    private final TradeType type;

    public TradeSelected(TradeType type) {
        super(EventsEnum.TRADE_SELECTED.name());
        this.type = type;
    }

    public TradeType getType() {
        return type;
    }
}
