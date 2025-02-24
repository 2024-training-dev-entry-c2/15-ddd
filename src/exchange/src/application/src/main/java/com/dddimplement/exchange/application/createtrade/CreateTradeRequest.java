package com.dddimplement.exchange.application.createtrade;

import com.buildingblocks.shared.application.Request;

public class CreateTradeRequest extends Request {
    private final Integer valueOrdered;
    private final Integer valueReceived;

    public CreateTradeRequest( Integer valueOrdered, Integer valueReceived) {
        super(null);
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