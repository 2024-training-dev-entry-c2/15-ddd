package com.dddimplement.exchange.domain.trade.values;

import com.dddimplement.shared.domain.generic.IValueObject;

public class Value implements IValueObject {
    private final  Integer value;

    private Value(Integer value) {
        this.value = value;
        validate();
    }

    public static Value of(Integer value) {
        return new Value(value);
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public void validate() {
        if (this.value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }

        if (this.value < 0) {
            throw new IllegalArgumentException("Value cannot be negative");
        }

    }
}
