package com.dddimplement.exchange.application.improveexchange;

import com.buildingblocks.shared.application.ICommandUseCase;
import com.dddimplement.exchange.application.shared.trade.TradeMapper;
import com.dddimplement.exchange.application.shared.trade.TradeResponse;
import com.dddimplement.exchange.application.shared.repositories.IEventsRepository;
import com.dddimplement.exchange.domain.trade.Trade;
import reactor.core.publisher.Mono;

public class ImproveExchangeRateUseCase implements ICommandUseCase<ImproveExchangeRateRequest, Mono<TradeResponse>> {
    private final IEventsRepository repository;

    public ImproveExchangeRateUseCase(IEventsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<TradeResponse> execute(ImproveExchangeRateRequest request) {
        return repository.findEventsByAggregateId(request.getTradeId())
                .collectList()
                .map(events -> {
                    Trade trade = Trade.from(request.getTradeId(), events);
                    trade.getMaritime().improveExchangeRate(request.getValueOrdered(), request.getValueReceived());
                    trade.getUncommittedEvents().forEach(repository::save);
                    trade.markEventsAsCommitted();
                    return TradeMapper.mapToTradeResponse(trade);
                });
    }
}