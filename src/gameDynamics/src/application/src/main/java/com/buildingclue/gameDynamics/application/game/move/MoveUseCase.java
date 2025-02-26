package com.buildingclue.gameDynamics.application.game.move;

import com.buildingclue.gameDynamics.application.shared.ports.IEventsRepositoryPort;
import com.buildingclue.gameDynamics.domain.game.Game;
import com.buildingclue.gameDynamics.domain.game.values.GameId;
import com.buildingclue.gameDynamics.domain.game.values.PlayerId;
import com.buildingclue.gameDynamics.domain.game.values.Positions;
import com.buildingclue.shared.application.ICommandUseCase;
import com.buildingclue.shared.domain.constants.States;
import reactor.core.publisher.Mono;

public class MoveUseCase implements ICommandUseCase<MoveRequest, Mono<MoveResponse>> {

  private final IEventsRepositoryPort eventsRepository;

  public MoveUseCase(IEventsRepositoryPort eventsRepository) {
    this.eventsRepository = eventsRepository;
  }

  @Override
  public Mono<MoveResponse> execute(MoveRequest request) {
    return eventsRepository.findEventsByAggregateId(request.getAggregateId())
            .collectList()
            .flatMap(events -> {
              if (events.isEmpty()) {
                return Mono.error(new RuntimeException("No se encontró el juego con ID: " + request.getAggregateId()));
              }

              Game game = Game.rebuildGame(GameId.of(request.getAggregateId()), events);

              if (!game.getState().getState().equals(States.IN_PROGRESS)) {
                return Mono.error(new RuntimeException("El juego no está en progreso."));
              }

              if (game.getBoard() == null) {
                return Mono.error(new RuntimeException("El tablero no está inicializado correctamente."));
              }

              Positions fromPosition = new Positions(Integer.parseInt(request.getFromPosition().split(",")[0]),
                      Integer.parseInt(request.getFromPosition().split(",")[1]));
              Positions toPosition = new Positions(Integer.parseInt(request.getToPosition().split(",")[0]),
                      Integer.parseInt(request.getToPosition().split(",")[1]));

              boolean isMoveValid = game.getBoard().canMove(PlayerId.of(request.getPlayerId()), toPosition);

              if (isMoveValid) {
                game.makeMove(PlayerId.of(request.getPlayerId()), request.getFromPosition(), request.getToPosition());
              }

              game.getUncommittedEvents().forEach(eventsRepository::save);
              game.markEventsAsCommitted();

              return Mono.just(new MoveResponse(
                      game.getIdentity().getValue(),
                      request.getPlayerId(),
                      request.getFromPosition(),
                      request.getToPosition(),
                      isMoveValid
              ));
            });
  }
}
