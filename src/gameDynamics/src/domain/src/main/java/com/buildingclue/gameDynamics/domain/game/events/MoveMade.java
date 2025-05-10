package com.buildingclue.gameDynamics.domain.game.events;

import com.buildingclue.shared.domain.generic.DomainEvent;

public class MoveMade extends DomainEvent {

  private String playerId;
  private String fromPosition;
  private String toPosition;

  public MoveMade(){
    super(EventsEnum.MOVE_MADE.name());
  }

  public MoveMade(String playerId, String fromPosition, String toPosition) {
    super(EventsEnum.MOVE_MADE.name());
    this.playerId = playerId;
    this.fromPosition = fromPosition;
    this.toPosition = toPosition;
  }

  public String getPlayerId() {
    return playerId;
  }

  public String getFromPosition() {
    return fromPosition;
  }

  public String getToPosition() {
    return toPosition;
  }

  public void setPlayerId(String playerId) {
    this.playerId = playerId;
  }

  public void setFromPosition(String fromPosition) {
    this.fromPosition = fromPosition;
  }

  public void setToPosition(String toPosition) {
    this.toPosition = toPosition;
  }
}
