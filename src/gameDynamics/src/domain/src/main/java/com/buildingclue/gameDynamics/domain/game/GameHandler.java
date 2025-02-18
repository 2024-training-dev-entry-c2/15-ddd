package com.buildingclue.gameDynamics.domain.game;

import com.buildingclue.gameDynamics.domain.game.events.AccusationMade;
import com.buildingclue.gameDynamics.domain.game.events.GameOver;
import com.buildingclue.gameDynamics.domain.game.events.GameStarted;
import com.buildingclue.gameDynamics.domain.game.events.MoveMade;
import com.buildingclue.gameDynamics.domain.game.events.TurnEnded;
import com.buildingclue.gameDynamics.domain.game.events.TurnStarted;
import com.buildingclue.gameDynamics.domain.game.values.GameState;
import com.buildingclue.gameDynamics.domain.game.values.PlayerId;
import com.buildingclue.shared.domain.constants.States;
import com.buildingclue.shared.domain.generic.DomainActionsContainer;
import com.buildingclue.shared.domain.generic.DomainEvent;

import java.util.function.Consumer;

public class GameHandler extends DomainActionsContainer {
  public GameHandler(Game game) {
    addAction(handleGameStarted(game));
    addAction(handleGameOver(game));
    addAction(handleTurnStarted(game));
    addAction(handleTurnEnded(game));
    addAction(handleMoveMade(game));
    addAction(handleAccusationMade(game));
  }

  private Consumer<? super DomainEvent> handleGameStarted(Game game) {
    return event -> {
      if (event instanceof GameStarted) {
        game.setState(GameState.of(States.IN_PROGRESS));
      }
    };
  }

  private Consumer<? super DomainEvent> handleGameOver(Game game) {
    return event -> {
      if (event instanceof GameOver) {
        game.setState(GameState.of(States.IN_PROGRESS));
      }
    };
  }

  private Consumer<? super DomainEvent> handleTurnStarted(Game game) {
    return event -> {
      if (event instanceof TurnStarted) {
        TurnStarted turnEvent = (TurnStarted) event;
        game.startTurn(PlayerId.of(turnEvent.getPlayerId()), turnEvent.getTurnNumber());
      }
    };
  }

  private Consumer<? super DomainEvent> handleTurnEnded(Game game) {
    return event -> {
      if (event instanceof TurnEnded) {
        TurnEnded turnEvent = (TurnEnded) event;
        game.endTurn(PlayerId.of(turnEvent.getPlayerId()), turnEvent.getTurnNumber(), turnEvent.getReason());
      }
    };
  }

  private Consumer<? super DomainEvent> handleMoveMade(Game game) {
    return event -> {
      if (event instanceof MoveMade) {
        MoveMade moveEvent = (MoveMade) event;
        game.makeMove(PlayerId.of(moveEvent.getPlayerId()), moveEvent.getFromPosition(), moveEvent.getToPosition());
      }
    };
  }

  private Consumer<? super DomainEvent> handleAccusationMade(Game game) {
    return event -> {
      if (event instanceof AccusationMade) {
        AccusationMade accEvent = (AccusationMade) event;
        game.makeAccusation(PlayerId.of(accEvent.getPlayerId()), accEvent.getAccusedSuspect(), accEvent.getAccusedWeapon(), accEvent.getAccusedLocation());
      }
    };
  }
}
