package com.dddimplement.exchange.domain.trade.entities;

import com.dddimplement.exchange.domain.trade.values.ExchangeRate;
import com.dddimplement.exchange.domain.trade.values.MaritimeId;
import com.dddimplement.exchange.domain.trade.values.Value;
import com.dddimplement.shared.domain.generic.Entity;

public class Maritime extends Entity<MaritimeId> {
 private ExchangeRate exchangeDesired;
 private boolean active;

 // region Constructors
    public Maritime(MaritimeId identity, ExchangeRate exchangeRate) {
        super(identity);
        this.exchangeDesired = exchangeRate;
        this.active = false;
    }

    public Maritime(ExchangeRate exchangeRate) {
        super(new MaritimeId());
        this.exchangeDesired = exchangeRate;
    }
 // endregion

 //region Getters and Setters
    public ExchangeRate getExchangeRate() {
        return exchangeDesired;
    }

    public void setExchangeRate(ExchangeRate exchangeRate) {
        this.exchangeDesired = exchangeRate;
    }

    public void activate() {
        this.active = true;
    }

    public boolean isActive() {
        return active;
    }
    //endregion

    //region Methods
    public void improveExchangeRate(Integer additionalValueOrdered, Integer additionalValueReceived) {
        Value newValueOrdered = Value.of(this.exchangeDesired.getValueOrdered().getValue() + additionalValueOrdered);
        Value newValueReceived = Value.of(this.exchangeDesired.getValueReceived().getValue() + additionalValueReceived);
        this.exchangeDesired = ExchangeRate.of(newValueOrdered, newValueReceived);
    }
    //endregion
}
