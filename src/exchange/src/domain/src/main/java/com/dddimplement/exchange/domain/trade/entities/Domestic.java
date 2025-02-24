package com.dddimplement.exchange.domain.trade.entities;

import com.dddimplement.exchange.domain.trade.Trade;
import com.dddimplement.exchange.domain.trade.values.DomesticId;
import com.dddimplement.exchange.domain.trade.values.ExchangeRate;
import com.dddimplement.exchange.domain.trade.values.Value;
import com.dddimplement.shared.domain.generic.Entity;

public class Domestic extends Entity<DomesticId> {
    private ExchangeRate exchangeRate;
    private boolean active;

    // region Constructors
    public Domestic(DomesticId identity, ExchangeRate exchangeRate) {
        super(identity);
        this.exchangeRate = exchangeRate;
        this.active = false;
    }

    public Domestic(ExchangeRate exchangeRate) {
        super(new DomesticId());
        this.exchangeRate = exchangeRate;
    }
    // endregion

    //region Getters and Setters

    public ExchangeRate getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(ExchangeRate exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public void activate() {
        this.active = true;
    }

    public boolean isActive() {
        return active;
    }

    //endregion

    //region Methods
    public void changeRate(Integer valueOrdered, Integer valueReceived) {
        int newValueOrdered = this.exchangeRate.getValueOrdered().getValue() + valueOrdered;
        int newValueReceived = this.exchangeRate.getValueReceived().getValue() + valueReceived;
        this.exchangeRate = ExchangeRate.of(Value.of(newValueOrdered), Value.of(newValueReceived));
    }
    //endregion
}
