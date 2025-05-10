package com.buildingblocks.actionsandshiftsmanager.application.moveByCard;


import com.buildingblocks.actionsandshiftsmanager.application.shared.MethodPerformed;

public class MovementResponse {
    private final String Type;
    private final MethodPerformed methodPerformed;
    private final Integer points;

    public MovementResponse(String type, MethodPerformed methodPerformed, Integer points) {
        Type = type;
        this.methodPerformed = methodPerformed;
        this.points = points;
    }

    public String getType() {
        return Type;
    }

    public MethodPerformed getMethodPerformed() {
        return methodPerformed;
    }

    public Integer getPoints() {
        return points;
    }
}
