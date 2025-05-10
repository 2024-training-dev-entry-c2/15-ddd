package com.buildingclue.gameDynamics.domain.game.events;

import com.buildingclue.shared.domain.generic.DomainEvent;

public class CardDrawn extends DomainEvent {
  private String playerId;
  private String cardType;
  private String cardName;

  public CardDrawn() {
    super(EventsEnum.CARD_DRAWN.name());
  }

  public CardDrawn(String playerId, String cardType, String cardName) {
    super(EventsEnum.CARD_DRAWN.name());
    this.playerId = playerId;
    this.cardType = cardType;
    this.cardName = cardName;
  }

  public String getPlayerId() {
    return playerId;
  }

  public String getCardType() {
    return cardType;
  }

  public String getCardName() {
    return cardName;
  }

  public void setPlayerId(String playerId) {
    this.playerId = playerId;
  }

  public void setCardType(String cardType) {
    this.cardType = cardType;
  }

  public void setCardName(String cardName) {
    this.cardName = cardName;
  }
}
