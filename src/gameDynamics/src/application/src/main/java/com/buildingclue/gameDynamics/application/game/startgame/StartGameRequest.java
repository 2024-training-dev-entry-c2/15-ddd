package com.buildingclue.gameDynamics.application.game.startgame;

import com.buildingclue.shared.application.Request;

public class StartGameRequest extends Request {

  public StartGameRequest(){
    super(null);
  }

  public StartGameRequest(String aggregateId) {
    super(aggregateId);
  }
}
