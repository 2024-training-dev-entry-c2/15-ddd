package com.buildingclue.gameDynamics.domain.game.entities;

import com.buildingclue.gameDynamics.domain.game.values.BoardId;
import com.buildingclue.gameDynamics.domain.game.values.Dimensions;
import com.buildingclue.gameDynamics.domain.game.values.PlayerId;
import com.buildingclue.gameDynamics.domain.game.values.Positions;
import com.buildingclue.shared.domain.generic.Entity;

import java.util.List;

public class Board extends Entity<BoardId> {
  private Dimensions dimensions;
  private Positions positions;
  private List<PlayerId> players;

  public Board(Dimensions dimensions, Positions positions, List<PlayerId> players) {
    super(new BoardId());
    this.dimensions = dimensions;
    this.positions = positions;
    this.players = players;
  }

  public Board(BoardId id, Dimensions dimensions, Positions positions, List<PlayerId> players) {
    super(id);
    this.dimensions = dimensions;
    this.positions = positions;
    this.players = players;
  }

  public Dimensions getDimensions() {
    return dimensions;
  }

  public void setDimensions(Dimensions dimensions) {
    this.dimensions = dimensions;
  }

  public Positions getPositions() {
    return positions;
  }

  public void setPositions(Positions positions) {
    this.positions = positions;
  }

  public List<PlayerId> getPlayers() {
    return players;
  }

  public void setPlayers(List<PlayerId> players) {
    this.players = players;
  }
}
