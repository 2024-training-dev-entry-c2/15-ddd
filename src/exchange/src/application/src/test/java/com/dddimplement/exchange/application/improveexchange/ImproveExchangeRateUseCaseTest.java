package com.dddimplement.exchange.application.improveexchange;

import com.dddimplement.exchange.application.shared.repositories.IEventsRepository;
import com.dddimplement.exchange.domain.trade.events.ExchangeRateImproved;
import com.dddimplement.exchange.domain.trade.events.TradeCreated;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ImproveExchangeRateUseCaseTest {
    private ImproveExchangeRateUseCase useCase;
    private IEventsRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventsRepository.class);
        useCase = new ImproveExchangeRateUseCase(repository);
    }

    @Test
    void executeSuccess() {
        String tradeId = "trade1";
        Integer valueOrdered = 10;
        Integer valueReceived = 5;
        ImproveExchangeRateRequest request = new ImproveExchangeRateRequest(null, tradeId, valueOrdered, valueReceived);

        TradeCreated tradeCreatedEvent = new TradeCreated(100, 50);
        ExchangeRateImproved exchangeRateImprovedEvent = new ExchangeRateImproved(10, 5);

        Mockito.when(repository.findEventsByAggregateId(tradeId))
                .thenReturn(Flux.just(tradeCreatedEvent, exchangeRateImprovedEvent));

        StepVerifier
                .create(useCase.execute(request))
                .assertNext(response -> {
                    assertNotNull(response);
                    assertEquals(tradeId, response.getTradeId());
                    assertEquals(120, response.getMaritimeExchangeRate().getValueOrdered());
                    assertEquals(60, response.getMaritimeExchangeRate().getValueReceived());
                })
                .verifyComplete();

        Mockito.verify(repository, Mockito.times(2)).save(Mockito.any());
    }
}