package com.buildingclue.gameDynamics.domain.game.events;

import com.buildingclue.shared.domain.generic.DomainEvent;

public class TurnStarted extends DomainEvent {
  private String playerId;
  private Integer turnNumber;

  public TurnStarted(){
    super(EventsEnum.TURN_STARTED.name());
  }

  public TurnStarted(String currentPlayer, String playerId, Integer turnNumber) {
    super(EventsEnum.TURN_STARTED.name());
    this.playerId = playerId;
    this.turnNumber = turnNumber;
  }

  public String getPlayerId() {
    return playerId;
  }

  public Integer getTurnNumber() {
    return turnNumber;
  }

  public void setPlayerId(String playerId) {
    this.playerId = playerId;
  }

  public void setTurnNumber(Integer turnNumber) {
    this.turnNumber = turnNumber;
  }
}
