package com.dddimplement.exchange.application.createtrade;

import com.buildingblocks.shared.application.ICommandUseCase;
import com.dddimplement.exchange.application.shared.trade.TradeMapper;
import com.dddimplement.exchange.application.shared.trade.TradeResponse;
import com.dddimplement.exchange.application.shared.repositories.IEventsRepository;
import com.dddimplement.exchange.domain.trade.Trade;
import reactor.core.publisher.Mono;

public class CreateTradeUseCase implements ICommandUseCase<CreateTradeRequest, Mono<TradeResponse>> {
    private final IEventsRepository repository;

    public CreateTradeUseCase(IEventsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<TradeResponse> execute(CreateTradeRequest request) {
        Trade trade = new Trade(request.getValueOrdered(), request.getValueReceived());
        trade.tradeCreated(request.getValueOrdered(), request.getValueReceived());
        trade.getUncommittedEvents().forEach(repository::save);
        trade.markEventsAsCommitted();
        return Mono.just(TradeMapper.mapToTradeResponse(trade));
    }
}