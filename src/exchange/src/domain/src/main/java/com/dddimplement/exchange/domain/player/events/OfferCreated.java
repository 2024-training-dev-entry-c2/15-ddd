package com.dddimplement.exchange.domain.player.events;

import com.dddimplement.shared.domain.generic.DomainEvent;

public class OfferCreated extends DomainEvent {
    private final Integer Amount;
    private final String resourceType;

    public OfferCreated( Integer amount, String resourceType) {
        super(EventsEnum.OFFER_CREATED.name());
        Amount = amount;
        this.resourceType = resourceType;
    }

    public Integer getAmount() {
        return Amount;
    }

    public String getResourceType() {
        return resourceType;
    }
}
