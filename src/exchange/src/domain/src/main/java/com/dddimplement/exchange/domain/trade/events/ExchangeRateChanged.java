package com.dddimplement.exchange.domain.trade.events;

import com.dddimplement.shared.domain.generic.DomainEvent;

public class ExchangeRateChanged extends DomainEvent {
    private final Integer valueIOrdered;
    private final Integer valueReceived;

    public ExchangeRateChanged(Integer valueIOrdered, Integer valueReceived) {
        super(EventsEnum.EXCHANGE_RATE_CHANGED.name());
        this.valueIOrdered = valueIOrdered;
        this.valueReceived = valueReceived;
    }

    public Integer getValueIOrdered() {
        return valueIOrdered;
    }

    public Integer getValueReceived() {
        return valueReceived;
    }
}
