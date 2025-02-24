package com.dddimplement.exchange.application.counteroffer;

import com.dddimplement.exchange.application.shared.repositories.IEventsRepository;
import com.dddimplement.exchange.domain.player.events.CreatedPlayer;
import com.dddimplement.exchange.domain.player.events.CounterOfferCreated;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class CounterOfferUseCaseTest {
    private CounterOfferUseCase useCase;
    private IEventsRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventsRepository.class);
        useCase = new CounterOfferUseCase(repository);
    }

    @Test
    void execute_withCounterOffer() {
        String aggregateId = "player1";
        CounterOfferRequest request = new CounterOfferRequest(aggregateId, "player1", 3, "BRICK");

        CreatedPlayer createdPlayerEvent = new CreatedPlayer("player1", "red");

        Mockito.when(repository.findEventsByAggregateId(aggregateId))
                .thenReturn(Flux.just(createdPlayerEvent));

        StepVerifier.create(useCase.execute(request))
                .expectNextMatches(response -> response.getPlayerId().equals("player1"))
                .verifyComplete();

        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(CounterOfferCreated.class));
    }
}