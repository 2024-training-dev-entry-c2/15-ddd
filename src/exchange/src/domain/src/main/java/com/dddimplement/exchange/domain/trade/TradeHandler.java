package com.dddimplement.exchange.domain.trade;

import com.dddimplement.exchange.domain.trade.entities.Domestic;
import com.dddimplement.exchange.domain.trade.entities.Maritime;
import com.dddimplement.exchange.domain.trade.events.*;
import com.dddimplement.exchange.domain.trade.values.ExchangeRate;
import com.dddimplement.exchange.domain.trade.values.TradeState;
import com.dddimplement.exchange.domain.trade.values.TradeType;
import com.dddimplement.exchange.domain.trade.values.Value;
import com.dddimplement.shared.domain.generic.DomainActionsContainer;
import com.dddimplement.shared.domain.generic.DomainEvent;

import java.util.function.Consumer;

public class TradeHandler extends DomainActionsContainer {

    public TradeHandler(Trade trade) {
        add(TradeCreated(trade));
        add(TradeSelected(trade));
        add(ExchangeRateImproved(trade));
        add(ExchangeRateChanged(trade));
    }

    public Consumer<? extends DomainEvent> TradeCreated(Trade trade) {
        return (TradeCreated event) -> {
            Domestic domestic = new Domestic(
                    ExchangeRate.of(Value.of(event.getValueOrdered()), Value.of(event.getValueReceived()))

            );
           Maritime maritime = new Maritime(
                    ExchangeRate.of(Value.of(event.getValueOrdered()), Value.of(event.getValueReceived()))
            );
            trade.setDomestic(domestic);
            trade.setMaritime(maritime);
            trade.setState(TradeState.of(false));
        };
    }


    public Consumer<? extends DomainEvent> TradeSelected(Trade trade) {
        return (TradeSelected event) -> {
            if (event.getType() == null) {
                throw new IllegalArgumentException("Trade type cannot be null");
            }

            try {
                TradeType type = TradeType.valueOf(event.getType().name()); // Verifica si es válido
                switch (type) {
                    case MARITIME -> trade.getMaritime().activate();
                    case DOMESTIC -> trade.getDomestic().activate();
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid trade type");
            }
        };
    }

    public Consumer<? extends DomainEvent> ExchangeRateImproved(Trade trade) {
        return (ExchangeRateImproved event) -> {
            Maritime maritime = trade.getMaritime();
            maritime.improveExchangeRate(event.getValueOrdered(), event.getValueReceived());
        };
    }


    public Consumer<? extends DomainEvent> ExchangeRateChanged(Trade trade) {
        return (ExchangeRateChanged event) -> {
            Domestic domestic = trade.getDomestic();
            domestic.changeRate(event.getValueIOrdered(), event.getValueReceived());
            System.out.println("Exchange Rate Changed: Ordered Value = " + event.getValueIOrdered() + ", Received Value = " + event.getValueReceived());
        };
    }

}
