package com.dddimplement.exchange.domain.trade.events;

import com.dddimplement.shared.domain.generic.DomainEvent;

public class ExchangeRateImproved extends DomainEvent {
    private final Integer valueOrdered;
    private final Integer valueReceived;

    public ExchangeRateImproved(Integer valueOrdered, Integer valueReceived) {
        super(EventsEnum.EXCHANGE_RATE_IMPROVED.name());
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
