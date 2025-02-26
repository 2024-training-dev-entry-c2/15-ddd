package com.buildingclue.gameDynamics.application.game.startgame;

import com.buildingclue.gameDynamics.application.shared.ports.IEventsRepositoryPort;
import com.buildingclue.gameDynamics.domain.game.Game;
import com.buildingclue.gameDynamics.domain.game.entities.Board;
import com.buildingclue.gameDynamics.domain.game.entities.Rule;
import com.buildingclue.gameDynamics.domain.game.entities.Turn;
import com.buildingclue.gameDynamics.domain.game.values.Dimensions;
import com.buildingclue.gameDynamics.domain.game.values.GameId;
import com.buildingclue.gameDynamics.domain.game.values.GameState;
import com.buildingclue.gameDynamics.domain.game.values.NumberPlayers;
import com.buildingclue.gameDynamics.domain.game.values.PlayerId;
import com.buildingclue.gameDynamics.domain.game.values.Positions;
import com.buildingclue.shared.application.ICommandUseCase;
import com.buildingclue.shared.domain.constants.States;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

public class StartGameUseCase implements ICommandUseCase<StartGameRequest, Mono<StartGameResponse>> {

  private final IEventsRepositoryPort eventsRepository;

  public StartGameUseCase(IEventsRepositoryPort eventsRepository) {
    this.eventsRepository = eventsRepository;
  }

  @Override
  public Mono<StartGameResponse> execute(StartGameRequest request) {
    Dimensions dimensions = new Dimensions(10, 10);
    Positions startPosition = new Positions(0, 0);
    List<PlayerId> playerIds = List.of(PlayerId.of("player-001"), PlayerId.of("player-002"));
    List<Rule> rules = Collections.emptyList();
    List<Turn> turns = Collections.emptyList();

    Board board = new Board(dimensions, startPosition, playerIds);

    Game game = Game.createGame(
            GameId.of(request.getAggregateId()),
            GameState.of(States.WAITING),
            board,
            rules,
            turns,
            new NumberPlayers(2),
            playerIds
    );

    game.startGame();
    game.getUncommittedEvents().forEach(eventsRepository::save);
    game.markEventsAsCommitted();

    return Mono.just(new StartGameResponse(
            game.getIdentity().getValue(),
            playerIds.isEmpty() ? "No Players" : playerIds.get(0).getValue(),
            game.getState().getState().toString()
    ));
  }
}
