package com.buildingblocks.actionsandshiftsmanager.application.moveByCard;

import com.buildingblocks.actionsandshiftsmanager.application.shared.Card;
import com.buildingblocks.actionsandshiftsmanager.application.shared.MethodPerformed;
import com.buildingblocks.actionsandshiftsmanager.application.shared.Points;
import com.buildingblocks.shared.application.Request;

public class MovementRequest extends Request {
    private final String nextPlayerId;
    private final String nextPlayerName;
    private final Card card;
    private final String typePlace;
    private final MethodPerformed methodPerformed;
    private final Points points;


    public MovementRequest(String aggregateId, String nextPlayerId, String nextPlayerName, Card card, String typePlace, MethodPerformed methodPerformed, Points points) {
        super(aggregateId);
        this.nextPlayerId = nextPlayerId;
        this.nextPlayerName = nextPlayerName;
        this.card = card;
        this.typePlace = typePlace;
        this.methodPerformed = methodPerformed;
        this.points = points;
    }

    public Card getCard() {
        return card;
    }

    public String getTypePlace() {
        return typePlace;
    }

    public MethodPerformed getMethodPerformed() {
        return methodPerformed;
    }

    public Points getPoints() {
        return points;
    }

    public String getNextPlayerId() {
        return nextPlayerId;
    }

    public String getNextPlayerName() {
        return nextPlayerName;
    }
}
