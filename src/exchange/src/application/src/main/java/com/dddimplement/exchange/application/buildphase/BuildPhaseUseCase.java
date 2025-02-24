package com.dddimplement.exchange.application.buildphase;

import com.buildingblocks.shared.application.ICommandUseCase;
import com.dddimplement.exchange.application.shared.player.PlayerMapper;
import com.dddimplement.exchange.application.shared.player.PlayerResponse;
import com.dddimplement.exchange.application.shared.repositories.IEventsRepository;
import com.dddimplement.exchange.domain.player.Player;
import com.dddimplement.exchange.domain.player.values.TurnFaseEnum;
import reactor.core.publisher.Mono;

public class BuildPhaseUseCase implements ICommandUseCase<BuildPhaseRequest, Mono<PlayerResponse>> {
    private final IEventsRepository repository;

    public BuildPhaseUseCase(IEventsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<PlayerResponse> execute(BuildPhaseRequest request) {
        return repository.findEventsByAggregateId(request.getPlayerId())
                .collectList()
                .map(events -> Player.from(request.getPlayerId(), events))
                .flatMap(player -> {
                    if (request.isBuildCity()) {
                        player.improvedTerritory(request.getTerritoryType());
                    } else if (request.isBuildPath()) {
                        player.expandedTerriorty(request.getTerritoryType());
                    } else if (request.isBuildSettlement()) {
                        player.createdTerritory(request.getTerritoryType());
                    }
                    player.startedTurn(TurnFaseEnum.BUILD);
                    player.getUncommittedEvents().forEach(repository::save);
                    player.markEventsAsCommitted();
                    return Mono.just(PlayerMapper.mapToPlayer(player));
                });
    }
}