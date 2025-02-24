package com.dddimplement.exchange.domain.player.events;

import com.dddimplement.shared.domain.generic.DomainEvent;

public class CounterOfferCreated extends DomainEvent {
    private final Integer Amount;
    private final String resourceType;

    public CounterOfferCreated(Integer Amount, String resourceType) {
        super(EventsEnum.COUNTER_OFFER_CREATED.name());
        this.Amount = Amount;
        this.resourceType = resourceType;
    }

    public Integer getAmount() {
        return Amount;
    }

    public String getResourceType() {
        return resourceType;
    }
}
