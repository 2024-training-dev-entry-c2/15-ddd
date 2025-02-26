package com.buildingclue.gameDynamics.domain.game.events;

import com.buildingclue.shared.domain.generic.DomainEvent;

import java.util.List;

public class GameStarted extends DomainEvent {

  private String gameId;
  private List<String> playersIds;

  public GameStarted(){
    super(EventsEnum.GAME_STARTED.name());
  }

  public GameStarted(String gameId, List<String> playersIds) {
    super(EventsEnum.GAME_STARTED.name());
    this.gameId = gameId;
    this.playersIds = playersIds;
  }

  public String getGameId() {
    return gameId;
  }

  public void setGameId(String gameId) {
    this.gameId = gameId;
  }

  public List<String> getPlayersIds() {
    return playersIds;
  }

  public void setPlayersIds(List<String> playersIds) {
    this.playersIds = playersIds;
  }
}
