package com.dddimplement.exchange.domain.player.values;

import com.dddimplement.shared.domain.generic.IValueObject;

public class Amount implements IValueObject {
    private final Integer value;

    public Amount(Integer value) {
        this.value = value;
        validate();
    }

    public static Amount of(Integer value) {
        return new Amount(value);
    }

    @Override
    public void validate() {

        if(this.value == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }

        if (this.value < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

    }

    public Integer getValue() {
        return value;
    }
}
