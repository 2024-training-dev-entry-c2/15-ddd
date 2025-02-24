package com.dddimplement.exchange.domain.player.values;

import com.dddimplement.shared.domain.generic.IValueObject;

public class Path implements IValueObject {
    private final  Integer value;

    private Path(Integer value) {
        this.value = value;
        validate();
    }

    public static Path of(Integer value) {
        return new Path(value);
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public void validate() {
        if(this.value == null) {
            throw new IllegalArgumentException("Path cannot be null");
        }

        if (this.value < 0) {
            throw new IllegalArgumentException("Path cannot be negative");
        }
    }
}
