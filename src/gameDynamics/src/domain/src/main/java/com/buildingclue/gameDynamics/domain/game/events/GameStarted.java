package com.buildingclue.gameDynamics.domain.game.events;

import com.buildingclue.shared.domain.generic.DomainEvent;

public class GameStarted extends DomainEvent {

  private String gameId;

  public GameStarted(){
    super(EventsEnum.GAME_STARTED.name());
  }

  public GameStarted(String gameId) {
    super(EventsEnum.GAME_STARTED.name());
    this.gameId = gameId;
  }

  public String getGameId() {
    return gameId;
  }

  public void setGameId(String gameId) {
    this.gameId = gameId;
  }
}
