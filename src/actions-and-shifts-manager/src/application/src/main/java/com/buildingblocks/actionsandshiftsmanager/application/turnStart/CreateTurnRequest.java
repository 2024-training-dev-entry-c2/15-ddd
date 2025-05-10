package com.buildingblocks.actionsandshiftsmanager.application.turnStart;

import com.buildingblocks.actionsandshiftsmanager.application.shared.Card;
import com.buildingblocks.actionsandshiftsmanager.application.shared.Points;
import com.buildingblocks.shared.application.Request;

import java.util.List;

public class CreateTurnRequest extends Request {
    private final String playerId;
    private final String playerName;
    private final Card card;
    private final List<Card> cardList;
    private final Points points;

    protected CreateTurnRequest(String playerId, String playerName, Card card, List<Card> cardList, Points points) {
        super(null);
        this.playerId = playerId;
        this.playerName = playerName;
        this.card = card;
        this.cardList = cardList;
        this.points = points;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Points getPoints() {
        return points;
    }

    public Card getCard() {
        return card;
    }

    public List<Card> getCardList() {
        return cardList;
    }
}
