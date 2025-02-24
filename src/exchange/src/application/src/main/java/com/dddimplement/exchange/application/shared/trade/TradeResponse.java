package com.dddimplement.exchange.application.shared.trade;

import com.dddimplement.exchange.domain.trade.values.TradeType;

import java.util.List;

public class TradeResponse {
    private final String tradeId;
    private final String state;
    private final ExchangeRate domesticExchangeRate;
    private final ExchangeRate maritimeExchangeRate;
    private final TradeType tradeType;

    public TradeResponse(String tradeId, String state, ExchangeRate domesticExchangeRate, ExchangeRate maritimeExchangeRate, TradeType tradeType) {
        this.tradeId = tradeId;
        this.state = state;
        this.domesticExchangeRate = domesticExchangeRate;
        this.maritimeExchangeRate = maritimeExchangeRate;
        this.tradeType = tradeType;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public String getTradeId() {
        return tradeId;
    }

    public String getState() {
        return state;
    }

    public ExchangeRate getDomesticExchangeRate() {
        return domesticExchangeRate;
    }

    public ExchangeRate getMaritimeExchangeRate() {
        return maritimeExchangeRate;
    }

    public static class ExchangeRate {
        private final Integer valueOrdered;
        private final Integer valueReceived;

        public ExchangeRate(Integer valueOrdered, Integer valueReceived) {
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
}