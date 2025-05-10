package com.buildingblocks.actionsandshiftsmanager.application.turnStart;

import com.buildingblocks.actionsandshiftsmanager.application.shared.Card;

import java.util.List;

public class CreateTurnResponse {
    private final String playerName;
    private final Integer amountPoints;
    private final Card card;
    private final List<Card> cardsObtained;

    public CreateTurnResponse(String playerId, Integer amountPoints, Card card, List<Card> cardsObtained) {
        this.playerName = playerId;
        this.amountPoints = amountPoints;
        this.card = card;
        this.cardsObtained = cardsObtained;
    }

    public Integer getAmountPoints() {
        return amountPoints;
    }

    public List<Card> getCardsObtained() {
        return cardsObtained;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Card getCard() {
        return card;
    }
}
