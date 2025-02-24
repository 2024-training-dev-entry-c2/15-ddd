package com.dddimplement.exchange.domain.player.values;

import com.dddimplement.shared.domain.generic.IValueObject;

public class IsAccepted implements IValueObject {
    private final Boolean value;

    private IsAccepted(Boolean value) {
        this.value = value;
        validate();
    }

    public static IsAccepted of(Boolean value) {
        return new IsAccepted(value);
    }

    public Boolean getValue() {
        return value;
    }

    @Override
    public void validate() {
        if (this.value == null) {
            throw new IllegalArgumentException("isAccepted cannot be null");
        }
    }

}
