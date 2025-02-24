package com.dddimplement.exchange.application.tradephase;

import com.buildingblocks.shared.application.ICommandUseCase;
import com.dddimplement.exchange.application.shared.player.PlayerMapper;
import com.dddimplement.exchange.application.shared.player.PlayerResponse;
import com.dddimplement.exchange.application.shared.repositories.IEventsRepository;
import com.dddimplement.exchange.domain.player.Player;
import com.dddimplement.exchange.domain.player.values.TurnFaseEnum;
import reactor.core.publisher.Mono;

public class TradePhaseUseCase implements ICommandUseCase<TradePhaseRequest, Mono<PlayerResponse>> {
    private final IEventsRepository repository;

    public TradePhaseUseCase(IEventsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<PlayerResponse> execute(TradePhaseRequest request) {
        return repository.findEventsByAggregateId(request.getPlayerId())
                .collectList()
                .map(events -> Player.from(request.getPlayerId(), events))
                .flatMap(player -> {
                    player.startedTurn(TurnFaseEnum.TRADE);
                    player.createdOffer(request.getOfferAmount(), request.getOfferType());

                    if (request.getOfferId() != null) {
                        if (request.getOfferAmount() > 0) {
                            player.aceptedOffer(request.getOfferId());
                        } else {
                            player.rejectedOffer(request.getOfferId());
                        }
                    }
                    player.getUncommittedEvents().stream()
                            .distinct()
                            .forEach(repository::save);
                    player.markEventsAsCommitted();
                    return Mono.just(PlayerMapper.mapToPlayer(player));
                });
    }
}