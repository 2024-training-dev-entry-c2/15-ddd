package com.buildingclue.gameDynamics.domain.game.events;

import com.buildingclue.shared.domain.generic.DomainEvent;

public class TurnEnded extends DomainEvent {

  private String playerId;
  private Integer turnNumber;
  private String reason;

  public TurnEnded(){
    super(EventsEnum.TURN_ENDED.name());
  }

  public TurnEnded(String playerId, Integer turnNumber, String reason) {
    super(EventsEnum.TURN_ENDED.name());
    this.playerId = playerId;
    this.turnNumber = turnNumber;
    this.reason = reason;
  }

  public String getPlayerId() {
    return playerId;
  }

  public Integer getTurnNumber() {
    return turnNumber;
  }

  public String getReason() {
    return reason;
  }

  public void setPlayerId(String playerId) {
    this.playerId = playerId;
  }

  public void setTurnNumber(Integer turnNumber) {
    this.turnNumber = turnNumber;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }
}
