package com.dddimplement.exchange.domain.player.values;

import com.dddimplement.shared.domain.generic.IValueObject;

public class City implements IValueObject {
    private final Integer value;

    private City(Integer value) {
        this.value = value;
        validate();
    }

    public static City of(Integer value) {
        return new City(value);
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public void validate() {
    }
}
