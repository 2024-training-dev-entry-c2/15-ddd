package com.dddimplement.exchange.application.createplayer;

import com.buildingblocks.shared.application.ICommandUseCase;
import com.dddimplement.exchange.application.shared.player.PlayerMapper;
import com.dddimplement.exchange.application.shared.player.PlayerResponse;
import com.dddimplement.exchange.application.shared.repositories.IEventsRepository;
import com.dddimplement.exchange.domain.player.Player;
import reactor.core.publisher.Mono;

public class CreatePlayerUseCase implements ICommandUseCase<CreatePlayerRequest, Mono<PlayerResponse>> {
    private final IEventsRepository repository;

    public CreatePlayerUseCase(IEventsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<PlayerResponse> execute(CreatePlayerRequest request) {
        Player player = new Player(
                request.getName(),
                request.getColor()
        );
        player.createdPlayer(request.getName(), request.getColor());
        player.getUncommittedEvents().forEach(repository::save);
        player.markEventsAsCommitted();
        return Mono.just(PlayerMapper.mapToPlayer(player));
    }
}