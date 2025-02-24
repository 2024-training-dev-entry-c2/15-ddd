package com.dddimplement.exchange.application.createplayer;

import com.buildingblocks.shared.application.Request;

import java.util.List;

public class CreatePlayerRequest extends Request {
    private final String name;
    private final String color;

    public CreatePlayerRequest(String name, String color) {
        super(null);
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

}