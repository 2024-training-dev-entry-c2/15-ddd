package com.buildingclue.gameDynamics.domain.game.events;

import com.buildingclue.shared.domain.generic.DomainEvent;

public class GameOver extends DomainEvent {
  private String winnerPlayerId;
  private Boolean wasCaseSolved;

  public GameOver(){
    super(EventsEnum.GAME_OVER.name());
  }
  public GameOver(String winnerPlayerId, Boolean wasCaseSolved) {
    super(EventsEnum.GAME_OVER.name());
    this.winnerPlayerId = winnerPlayerId;
    this.wasCaseSolved = wasCaseSolved;
  }

  public String getWinnerPlayerId() {
    return winnerPlayerId;
  }

  public Boolean getWasCaseSolved() {
    return wasCaseSolved;
  }

  public void setWinnerPlayerId(String winnerPlayerId) {
    this.winnerPlayerId = winnerPlayerId;
  }

  public void setWasCaseSolved(Boolean wasCaseSolved) {
    this.wasCaseSolved = wasCaseSolved;
  }
}
