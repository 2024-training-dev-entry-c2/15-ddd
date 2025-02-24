package com.dddimplement.exchange.domain.player.values;

import com.dddimplement.shared.domain.generic.IValueObject;

public class Color implements IValueObject {
    private final String color;

    private Color(String color) {
        this.color = color;
        validate();
    }

    public String getValue() {
        return color;
    }

    public static Color of(String color) {
        return new Color(color);
    }

    @Override
    public void validate() {
        if (this.color == null) {
            throw new IllegalArgumentException("Color cannot be null");
        }
    }
}
