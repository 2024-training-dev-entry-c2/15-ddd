package com.dddimplement.exchange.domain.player.events;

import com.dddimplement.shared.domain.generic.DomainEvent;

public class OfferRejected extends DomainEvent {
    private final String offerId;


    public OfferRejected(String offerId) {
        super(EventsEnum.OFFER_REJECTED.name());
        this.offerId = offerId;
    }

    public String getOfferId() {
        return offerId;
    }


}
