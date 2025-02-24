package com.dddimplement.exchange.application.shared.trade;

import com.dddimplement.exchange.domain.trade.Trade;
import com.dddimplement.exchange.domain.trade.values.ExchangeRate;

public class TradeMapper {
    public static TradeResponse mapToTradeResponse(Trade trade) {
        return new TradeResponse(
                trade.getIdentity().getValue(),
                trade.getState().getState().toString(),
                mapExchangeRate(trade.getDomestic().getExchangeRate()),
                mapExchangeRate(trade.getMaritime().getExchangeRate()),
                trade.getTradeType()
        );
    }

    private static TradeResponse.ExchangeRate mapExchangeRate(ExchangeRate exchangeRate) {
        return new TradeResponse.ExchangeRate(
                exchangeRate.getValueOrdered().getValue(),
                exchangeRate.getValueReceived().getValue()
        );
    }
}