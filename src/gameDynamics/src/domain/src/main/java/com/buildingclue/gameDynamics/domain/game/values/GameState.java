package com.buildingclue.gameDynamics.domain.game.values;

import com.buildingclue.shared.domain.constants.States;
import com.buildingclue.shared.domain.generic.IValueObject;

import static com.buildingclue.shared.domain.utils.ValidationUtils.validateNotNull;

public class GameState implements IValueObject {

  private final States state;

  public GameState(States state) {
    this.state = state;
    validate();
  }

  public static GameState of(States state) {
    return new GameState(state);
  }

  @Override
  public void validate() {
    validateNotNull(state, "state cannot be null");
  }

  public States getState() {
    return state;
  }
}
