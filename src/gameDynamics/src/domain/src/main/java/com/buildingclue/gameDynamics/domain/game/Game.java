package com.buildingclue.gameDynamics.domain.game;

import com.buildingclue.gameDynamics.domain.game.entities.Board;
import com.buildingclue.gameDynamics.domain.game.entities.Rule;
import com.buildingclue.gameDynamics.domain.game.entities.Turn;
import com.buildingclue.gameDynamics.domain.game.events.AccusationMade;
import com.buildingclue.gameDynamics.domain.game.events.GameOver;
import com.buildingclue.gameDynamics.domain.game.events.GameStarted;
import com.buildingclue.gameDynamics.domain.game.events.MoveMade;
import com.buildingclue.gameDynamics.domain.game.events.TurnEnded;
import com.buildingclue.gameDynamics.domain.game.events.TurnStarted;
import com.buildingclue.gameDynamics.domain.game.values.Dimensions;
import com.buildingclue.gameDynamics.domain.game.values.GameId;
import com.buildingclue.gameDynamics.domain.game.values.GameState;
import com.buildingclue.gameDynamics.domain.game.values.NumberPlayers;
import com.buildingclue.gameDynamics.domain.game.values.PlayerId;
import com.buildingclue.gameDynamics.domain.game.values.Positions;
import com.buildingclue.shared.domain.constants.States;
import com.buildingclue.shared.domain.generic.AggregateRoot;
import com.buildingclue.shared.domain.generic.DomainEvent;

import java.util.List;

public class Game extends AggregateRoot<GameId> {
  private GameState state;
  private Board board;
  private List<Rule> rules;
  private List<Turn> turns;
  private NumberPlayers players;
  private List<PlayerId> playersIds;

  public Game() {
    super(new GameId());
    this.state = GameState.of(States.WAITING);
    this.playersIds = List.of();
    subscribe(new GameHandler(this));
  }

  private Game(GameId id, GameState state, Board board, List<Rule> rules, List<Turn> turns, NumberPlayers players, List<PlayerId> playersIds) {
    super(id);
    this.state = state;
    this.board = board;
    this.rules = rules;
    this.turns = turns;
    this.players = players;
    this.playersIds = playersIds;
    subscribe(new GameHandler(this));
    apply(new GameStarted(id.getValue(), playersIds.stream().map(PlayerId::getValue).toList()));
  }

  public static Game createGame(GameId id, GameState state, Board board, List<Rule> rules, List<Turn> turns, NumberPlayers players, List<PlayerId> playersIds) {
    return new Game(id, state, board, rules, turns, players, playersIds);
  }

  public static Game rebuildGame(GameId gameId, List<DomainEvent> events) {
    Game game = new Game(gameId, GameState.of(States.WAITING), null, null, null, new NumberPlayers(2), List.of());
    events.forEach(game::applyEvent);

    if (game.getPlayersIds() == null || game.getPlayersIds().isEmpty()) {
      System.out.println("No hay jugadores en el evento `GAME_STARTED`. Evitando restauración incompleta.");
    }

    return game;
  }

  private void applyEvent(DomainEvent event) {
    if (event instanceof GameStarted) {
      GameStarted gameStarted = (GameStarted) event;
      this.state = GameState.of(States.IN_PROGRESS);
      this.board = new Board(new Dimensions(10, 10), new Positions(0, 0), List.of());

      if (gameStarted.getPlayersIds() == null || gameStarted.getPlayersIds().isEmpty()) {
        System.out.println("No hay jugadores en el evento `GameStarted`. No restaurando datos vacíos.");
        return;
      }

      this.playersIds = gameStarted.getPlayersIds().stream()
              .map(PlayerId::of)
              .toList();
      System.out.println("Evento `GameStarted` aplicado. Jugadores restaurados: " + this.playersIds);
    }

    if (event instanceof GameOver) {
      System.out.println("Evento `GameOver` aplicado: El juego ha terminado.");
      this.state = GameState.of(States.FINISHED);
    }
    if (event instanceof TurnStarted) {
      System.out.println("Turno iniciado para jugador: " + ((TurnStarted) event).getPlayerId());
    }
  }

  public GameState getState() {
    return state;
  }

  public void setState(GameState state) {
    this.state = state;
  }

  public Board getBoard() {
    return board;
  }

  public void setBoard(Board board) {
    this.board = board;
  }

  public List<Rule> getRules() {
    return rules;
  }

  public void setRules(List<Rule> rules) {
    this.rules = rules;
  }

  public List<Turn> getTurns() {
    return turns;
  }

  public void setTurns(List<Turn> turns) {
    this.turns = turns;
  }

  public NumberPlayers getPlayers() {
    return players;
  }

  public List<PlayerId> getPlayersIds() {
    return playersIds;
  }

  public void setPlayersIds(List<PlayerId> playersIds) {
    this.playersIds = playersIds;
  }

  public void setPlayers(NumberPlayers players) {
    this.players = players;
  }


  public void startGame() {
    if (!this.state.equals(GameState.of(States.WAITING))) {
      System.out.println("El juego ya fue iniciado. No se puede aplicar `GameStarted` nuevamente.");
      return;
    }

    if (playersIds == null || playersIds.isEmpty()) {
      System.out.println("No se puede iniciar el juego sin jugadores.");
      return;
    }

    this.state = GameState.of(States.IN_PROGRESS);
    apply(new GameStarted(this.getIdentity().getValue(), playersIds.stream().map(PlayerId::getValue).toList()));
  }

  public void endGame(String winnerPlayerId, Boolean wasCaseSolved) {
    this.state = GameState.of(States.FINISHED);
    apply(new GameOver(winnerPlayerId, wasCaseSolved));
  }

  public void startTurn(PlayerId playerId, Integer turnNumber) {
    if (this.state.equals(GameState.of(States.FINISHED))) {
      System.out.println("No se puede iniciar el turno, el juego ya terminó.");
      return;
    }
    System.out.println("Turno iniciado para: " + playerId.getValue() + " - Turno: " + turnNumber);
    apply(new TurnStarted(this.getIdentity().getValue(), playerId.getValue(), turnNumber));
  }


  public void endTurn(PlayerId playerId, Integer turnNumber, String reason) {
    if (this.state.equals(GameState.of(States.FINISHED))) {
      return;
    }
    apply(new TurnEnded(playerId.getValue(), turnNumber, reason));
  }


  public void makeMove(PlayerId playerId, String fromPosition, String toPosition) {
    apply(new MoveMade(playerId.getValue(), fromPosition, toPosition));
  }

  public void makeAccusation(PlayerId playerId, String suspect, String weapon, String location) {
    apply(new AccusationMade(playerId.getValue(), suspect, weapon, location));
  }
}
