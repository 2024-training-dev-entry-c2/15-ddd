package com.dddimplement.exchange.application.changeexchange;

import com.buildingblocks.shared.application.Request;

public class ChangeExchangeRateRequest extends Request {
    private final String tradeId;
    private final Integer valueOrdered;
    private final Integer valueReceived;

    public ChangeExchangeRateRequest(String aggregateId, String tradeId, Integer valueOrdered, Integer valueReceived) {
        super(aggregateId);
        this.tradeId = tradeId;
        this.valueOrdered = valueOrdered;
        this.valueReceived = valueReceived;
    }

    public String getTradeId() {
        return tradeId;
    }

    public Integer getValueOrdered() {
        return valueOrdered;
    }

    public Integer getValueReceived() {
        return valueReceived;
    }
}
