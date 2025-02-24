package com.dddimplement.exchange.domain.trade.values;


import com.dddimplement.shared.domain.generic.IValueObject;

public class ExchangeRate implements IValueObject {
    private final Value valueOrdered;
    private final Value valueReceived;

    private ExchangeRate(Value valueOrdered, Value valueReceived) {
        this.valueOrdered = valueOrdered;
        this.valueReceived = valueReceived;
        validate();
    }

    public static ExchangeRate of(Value valueOrdered, Value valueReceived) {
        return new ExchangeRate(valueOrdered, valueReceived);
    }

    public Value getValueOrdered() {
        return valueOrdered;
    }

    public Value getValueReceived() {
        return valueReceived;
    }

    @Override
    public void validate() {
        if(this.valueOrdered == null) {
            throw new IllegalArgumentException("ValueOrdered cannot be null");
        }
        if(this.valueReceived == null) {
            throw new IllegalArgumentException("ValueReceived cannot be null");
        }
    }
}
