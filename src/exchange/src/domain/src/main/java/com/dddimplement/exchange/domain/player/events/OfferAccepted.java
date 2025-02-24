package com.dddimplement.exchange.domain.player.events;

import com.dddimplement.shared.domain.generic.DomainEvent;

public class OfferAccepted extends DomainEvent {
    private final String offerId;

    public OfferAccepted(String offerId) {
       super(EventsEnum.OFFER_ACCEPTED.name());
         this.offerId = offerId;
    }

    public String getOfferId() {
        return offerId;
    }
}
