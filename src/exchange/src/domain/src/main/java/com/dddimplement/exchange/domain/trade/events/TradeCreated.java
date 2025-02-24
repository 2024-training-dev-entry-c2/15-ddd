package com.dddimplement.exchange.domain.trade.events;

import com.dddimplement.shared.domain.generic.DomainEvent;

public class TradeCreated extends DomainEvent {
    private final Integer valueOrdered;
    private final Integer valueReceived;

    public TradeCreated(Integer valueOrdered, Integer valueReceived) {
        super(EventsEnum.TRADE_CREATED.name());
        this.valueOrdered = valueOrdered;
        this.valueReceived = valueReceived;
    }

    public Integer getValueOrdered() {
        return valueOrdered;
    }

    public Integer getValueReceived() {
        return valueReceived;
    }
}
