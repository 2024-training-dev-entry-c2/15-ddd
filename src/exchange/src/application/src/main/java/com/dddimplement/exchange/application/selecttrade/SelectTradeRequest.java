package com.dddimplement.exchange.application.selecttrade;

import com.buildingblocks.shared.application.Request;
import com.dddimplement.exchange.domain.trade.values.TradeType;

public class SelectTradeRequest extends Request {
    private final String tradeId;
    private final TradeType tradeType;

    public SelectTradeRequest(String aggregateId, String tradeId, TradeType tradeType) {
        super(aggregateId);
        this.tradeId = tradeId;
        this.tradeType = tradeType;
    }

    public String getTradeId() {
        return tradeId;
    }

    public TradeType getTradeType() {
        return tradeType;
    }
}