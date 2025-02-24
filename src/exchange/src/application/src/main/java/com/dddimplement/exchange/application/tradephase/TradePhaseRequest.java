package com.dddimplement.exchange.application.tradephase;

import com.buildingblocks.shared.application.Request;

public class TradePhaseRequest extends Request {
    private String playerId;
    private String offerId;
    private Integer offerAmount;
    private String offerType;

    public TradePhaseRequest(String aggregateId, String playerId, String offerId, Integer offerAmount, String offerType) {
        super(aggregateId);
        this.playerId = playerId;
        this.offerId = offerId;
        this.offerAmount = offerAmount;
        this.offerType = offerType;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getOfferId() {
        return offerId;
    }

    public Integer getOfferAmount() {
        return offerAmount;
    }

    public String getOfferType() {
        return offerType;
    }

}