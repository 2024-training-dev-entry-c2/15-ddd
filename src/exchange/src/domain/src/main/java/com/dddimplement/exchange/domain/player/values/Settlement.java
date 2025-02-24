package com.dddimplement.exchange.domain.player.values;

import com.dddimplement.shared.domain.generic.IValueObject;

public class Settlement implements IValueObject{
    private final Integer value;

    private Settlement(Integer value) {
        this.value = value;
        validate();
    }

    public static Settlement of(Integer value) {
        return new Settlement(value);
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public void validate() {
        if(this.value == null) {
            throw new IllegalArgumentException("Settlement cannot be null");
        }

        if (this.value < 0) {
            throw new IllegalArgumentException("Settlement cannot be negative");
        }
    }
}
