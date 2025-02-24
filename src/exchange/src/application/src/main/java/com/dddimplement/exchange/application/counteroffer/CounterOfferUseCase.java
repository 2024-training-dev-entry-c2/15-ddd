package com.dddimplement.exchange.application.counteroffer;

import com.buildingblocks.shared.application.ICommandUseCase;
import com.dddimplement.exchange.application.shared.player.PlayerMapper;
import com.dddimplement.exchange.application.shared.player.PlayerResponse;
import com.dddimplement.exchange.application.shared.repositories.IEventsRepository;
import com.dddimplement.exchange.domain.player.Player;
import reactor.core.publisher.Mono;

public class CounterOfferUseCase implements ICommandUseCase<CounterOfferRequest, Mono<PlayerResponse>> {
    private final IEventsRepository repository;

    public CounterOfferUseCase(IEventsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<PlayerResponse> execute(CounterOfferRequest request) {
        return repository.findEventsByAggregateId(request.getPlayerId())
                .collectList()
                .map(events -> Player.from(request.getPlayerId(), events))
                .flatMap(player -> {
                    player.createdCounterOffer(request.getCounterOfferAmount(), request.getCounterOfferType());
                    player.getUncommittedEvents().stream()
                            .distinct()
                            .forEach(repository::save);
                    player.markEventsAsCommitted();
                    return Mono.just(PlayerMapper.mapToPlayer(player));
                });
    }
}