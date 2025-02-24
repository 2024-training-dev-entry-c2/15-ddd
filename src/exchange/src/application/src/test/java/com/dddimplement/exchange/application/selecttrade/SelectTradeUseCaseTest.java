package com.dddimplement.exchange.application.selecttrade;

import com.dddimplement.exchange.application.shared.repositories.IEventsRepository;
import com.dddimplement.exchange.application.shared.trade.TradeResponse;
import com.dddimplement.exchange.domain.trade.events.TradeCreated;
import com.dddimplement.exchange.domain.trade.values.TradeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SelectTradeUseCaseTest {
    private SelectTradeUseCase useCase;
    private IEventsRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventsRepository.class);
        useCase = new SelectTradeUseCase(repository);
    }

    @Test
    void executeSuccess() {
        String tradeId = "trade1";
        TradeType tradeType = TradeType.DOMESTIC;
        SelectTradeRequest request = new SelectTradeRequest(null, tradeId, tradeType);

        TradeCreated tradeCreatedEvent = new TradeCreated(100, 50);

        Mockito.when(repository.findEventsByAggregateId(tradeId))
                .thenReturn(Flux.just(tradeCreatedEvent));

        StepVerifier
                .create(useCase.execute(request))
                .assertNext(response -> {
                    assertNotNull(response);
                    assertEquals(tradeId, response.getTradeId());
                    assertEquals(tradeType, response.getTradeType());
                })
                .verifyComplete();

        Mockito.verify(repository, Mockito.times(2)).save(Mockito.any());
    }
}