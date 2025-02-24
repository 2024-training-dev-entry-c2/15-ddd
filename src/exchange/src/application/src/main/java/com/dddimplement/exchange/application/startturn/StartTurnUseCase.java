package com.dddimplement.exchange.application.startturn;

import com.buildingblocks.shared.application.ICommandUseCase;
import com.dddimplement.exchange.application.shared.player.PlayerMapper;
import com.dddimplement.exchange.application.shared.player.PlayerResponse;
import com.dddimplement.exchange.application.shared.repositories.IEventsRepository;
import com.dddimplement.exchange.domain.player.Player;
import com.dddimplement.exchange.domain.player.values.TurnFaseEnum;
import reactor.core.publisher.Mono;

public class StartTurnUseCase implements ICommandUseCase<StartTurnRequest, Mono<PlayerResponse>> {
    private final IEventsRepository repository;

    public StartTurnUseCase(IEventsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<PlayerResponse> execute(StartTurnRequest request) {
        return repository.findEventsByAggregateId(request.getAggregateId())
                .collectList()
                .map(events -> Player.from(request.getAggregateId(), events))
                .flatMap(player -> {
                    player.startedTurn(TurnFaseEnum.ROLL);
                    player.getUncommittedEvents().forEach(repository::save);
                    player.markEventsAsCommitted();
                    return Mono.just(PlayerMapper.mapToPlayer(player));
                });
    }
}