package com.dddimplement.exchange.application.createtrade;

import com.dddimplement.exchange.application.shared.repositories.IEventsRepository;
import com.dddimplement.exchange.application.shared.trade.TradeResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class CreateTradeUseCaseTest {
    private CreateTradeUseCase useCase;
    private IEventsRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventsRepository.class);
        useCase = new CreateTradeUseCase(repository);
    }

    @Test
    void executeSuccess() {
        CreateTradeRequest request = new CreateTradeRequest(100, 50);

        StepVerifier
                .create(useCase.execute(request))
                .assertNext(response -> {
                    assertNotNull(response);
                    assertEquals(request.getValueOrdered(), response.getDomesticExchangeRate().getValueOrdered());
                    assertEquals(request.getValueReceived(), response.getDomesticExchangeRate().getValueReceived());
                })
                .verifyComplete();
    }
}