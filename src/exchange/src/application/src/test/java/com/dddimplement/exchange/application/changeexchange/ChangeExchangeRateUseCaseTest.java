package com.dddimplement.exchange.application.changeexchange;

import com.dddimplement.exchange.application.shared.repositories.IEventsRepository;
import com.dddimplement.exchange.domain.trade.events.ExchangeRateChanged;
import com.dddimplement.exchange.domain.trade.events.TradeCreated;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ChangeExchangeRateUseCaseTest {
    private ChangeExchangeRateUseCase useCase;
    private IEventsRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventsRepository.class);
        useCase = new ChangeExchangeRateUseCase(repository);
    }

    @Test
    void executeSuccess() {
        String tradeId = "trade1";
        Integer valueOrdered = 20;
        Integer valueReceived = 10;
        ChangeExchangeRateRequest request = new ChangeExchangeRateRequest(null, tradeId, valueOrdered, valueReceived);

        TradeCreated tradeCreatedEvent = new TradeCreated(100, 50);
        ExchangeRateChanged exchangeRateChangedEvent = new ExchangeRateChanged(20, 10);

        Mockito.when(repository.findEventsByAggregateId(tradeId))
                .thenReturn(Flux.just(tradeCreatedEvent, exchangeRateChangedEvent));

        StepVerifier
                .create(useCase.execute(request))
                .assertNext(response -> {
                    assertNotNull(response);
                    assertEquals(tradeId, response.getTradeId());
                    assertEquals(100, response.getMaritimeExchangeRate().getValueOrdered());
                    assertEquals(50, response.getMaritimeExchangeRate().getValueReceived());
                })
                .verifyComplete();

        Mockito.verify(repository, Mockito.times(3)).save(Mockito.any());
    }
}