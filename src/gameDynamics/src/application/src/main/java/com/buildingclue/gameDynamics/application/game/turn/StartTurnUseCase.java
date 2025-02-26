package com.buildingclue.gameDynamics.application.game.turn;

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

public class StartTurnUseCase implements ICommandUseCase<StartTurnRequest, Mono<StartTurnResponse>> {

  private final IEventsRepositoryPort eventsRepository;

  public StartTurnUseCase(IEventsRepositoryPort eventsRepository) {
    this.eventsRepository = eventsRepository;
  }

  @Override
  public Mono<StartTurnResponse> execute(StartTurnRequest request) {
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

              boolean playerExists = game.getPlayersIds() != null &&
                      game.getPlayersIds().stream()
                              .anyMatch(player -> {
                                System.out.println("Comparando: " + player.getValue() + " con " + request.getPlayerId());
                                return player.getValue().equals(request.getPlayerId());
                              });

              if (!playerExists) {
                System.out.println("Jugador no encontrado en la partida.");
                return Mono.just(new StartTurnResponse(game.getIdentity().getValue(), request.getPlayerId(), request.getTurnNumber(), false));
              }


              game.startTurn(PlayerId.of(request.getPlayerId()), request.getTurnNumber());

              game.getUncommittedEvents().forEach(eventsRepository::save);
              game.markEventsAsCommitted();

              return Mono.just(new StartTurnResponse(
                      game.getIdentity().getValue(),
                      request.getPlayerId(),
                      request.getTurnNumber(),
                      true
              ));
            });
  }
}
