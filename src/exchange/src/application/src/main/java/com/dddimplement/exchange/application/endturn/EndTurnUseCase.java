package com.dddimplement.exchange.application.endturn;

import com.buildingblocks.shared.application.ICommandUseCase;
import com.dddimplement.exchange.application.shared.player.PlayerMapper;
import com.dddimplement.exchange.application.shared.player.PlayerResponse;
import com.dddimplement.exchange.application.shared.repositories.IEventsRepository;
import com.dddimplement.exchange.domain.player.Player;
import com.dddimplement.exchange.domain.player.values.TurnFaseEnum;
import reactor.core.publisher.Mono;

public class EndTurnUseCase implements ICommandUseCase<EndTurnRequest, Mono<PlayerResponse>> {
    private final IEventsRepository repository;

    public EndTurnUseCase(IEventsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<PlayerResponse> execute(EndTurnRequest request) {
        return repository.findEventsByAggregateId(request.getPlayerId())
                .collectList()
                .map(events -> Player.from(request.getPlayerId(), events))
                .flatMap(player -> {
                    player.endedTurn(TurnFaseEnum.END);
                    player.getUncommittedEvents().forEach(repository::save);
                    player.markEventsAsCommitted();
                    return Mono.just(PlayerMapper.mapToPlayer(player));
                });
    }
}