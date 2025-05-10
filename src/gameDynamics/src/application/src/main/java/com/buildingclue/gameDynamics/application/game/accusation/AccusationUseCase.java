package com.buildingclue.gameDynamics.application.game.accusation;

import com.buildingclue.gameDynamics.application.shared.ports.IEventsRepositoryPort;
import com.buildingclue.gameDynamics.domain.game.Game;
import com.buildingclue.gameDynamics.domain.game.values.GameId;
import com.buildingclue.gameDynamics.domain.game.values.PlayerId;
import com.buildingclue.shared.application.ICommandUseCase;
import com.buildingclue.shared.domain.constants.States;
import reactor.core.publisher.Mono;

public class AccusationUseCase implements ICommandUseCase<AccusationRequest, Mono<AccusationResponse>> {

  private final IEventsRepositoryPort eventsRepository;

  public AccusationUseCase(IEventsRepositoryPort eventsRepository) {
    this.eventsRepository = eventsRepository;
  }

  @Override
  public Mono<AccusationResponse> execute(AccusationRequest request) {
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

              game.makeAccusation(PlayerId.of(request.getPlayerId()), request.getSuspect(), request.getWeapon(), request.getLocation());

              boolean isAccusationCorrect = request.getSuspect().equals("Miss Scarlet") &&
                      request.getWeapon().equals("Candlestick") &&
                      request.getLocation().equals("Ballroom");

              if (isAccusationCorrect) {
                game.endGame(request.getPlayerId(), true);
              }

              game.getUncommittedEvents().forEach(eventsRepository::save);
              game.markEventsAsCommitted();

              return Mono.just(new AccusationResponse(
                      game.getIdentity().getValue(),
                      request.getPlayerId(),
                      request.getSuspect(),
                      request.getWeapon(),
                      request.getLocation(),
                      isAccusationCorrect
              )
            );
    });
  }
}
