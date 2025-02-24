package com.dddimplement.exchange.application.tradephase;

import com.dddimplement.exchange.application.shared.repositories.IEventsRepository;
import com.dddimplement.exchange.domain.player.events.CreatedPlayer;
import com.dddimplement.exchange.domain.player.events.OfferCreated;
import com.dddimplement.exchange.domain.player.events.CounterOfferCreated;
import com.dddimplement.exchange.domain.player.events.OfferAccepted;
import com.dddimplement.exchange.domain.player.events.OfferRejected;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class TradePhaseUseCaseTest {
    private TradePhaseUseCase useCase;
    private IEventsRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventsRepository.class);
        useCase = new TradePhaseUseCase(repository);
    }

    @Test
    void execute_withoutCounterOffer() {
        String aggregateId = "player1";
        TradePhaseRequest request = new TradePhaseRequest(aggregateId, "player1", "offer1", 5, "WOOD");

        CreatedPlayer createdPlayerEvent = new CreatedPlayer("player1", "red");
        OfferCreated offerCreatedEvent = new OfferCreated(5, "WOOD");

        Mockito.when(repository.findEventsByAggregateId(aggregateId))
                .thenReturn(Flux.just(createdPlayerEvent));

        StepVerifier.create(useCase.execute(request))
                .expectNextMatches(response -> response.getPlayerId().equals("player1"))
                .verifyComplete();

        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(OfferCreated.class));
    }

    @Test
    void execute_acceptOffer() {
        String aggregateId = "player1";
        TradePhaseRequest request = new TradePhaseRequest(aggregateId, "player1", "offer1", 5, "WOOD");

        CreatedPlayer createdPlayerEvent = new CreatedPlayer("player1", "red");
        OfferCreated offerCreatedEvent = new OfferCreated(5, "WOOD");

        Mockito.when(repository.findEventsByAggregateId(aggregateId))
                .thenReturn(Flux.just(createdPlayerEvent, offerCreatedEvent));

        StepVerifier.create(useCase.execute(request))
                .expectNextMatches(response -> response.getPlayerId().equals("player1"))
                .verifyComplete();

        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(OfferAccepted.class));
    }

    @Test
    void execute_rejectOffer() {
        String aggregateId = "player1";
        TradePhaseRequest request = new TradePhaseRequest(aggregateId, "player1", "offer1", 0, "WOOD");

        CreatedPlayer createdPlayerEvent = new CreatedPlayer("player1", "red");
        OfferCreated offerCreatedEvent = new OfferCreated(5, "WOOD");

        Mockito.when(repository.findEventsByAggregateId(aggregateId))
                .thenReturn(Flux.just(createdPlayerEvent, offerCreatedEvent));

        StepVerifier.create(useCase.execute(request))
                .expectNextMatches(response -> response.getPlayerId().equals("player1"))
                .verifyComplete();

        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(OfferRejected.class));
    }

    @Test
    void execute_withoutOfferId() {
        String aggregateId = "player1";
        TradePhaseRequest request = new TradePhaseRequest(aggregateId, "player1", null, 5, "WOOD");

        CreatedPlayer createdPlayerEvent = new CreatedPlayer("player1", "red");

        Mockito.when(repository.findEventsByAggregateId(aggregateId))
                .thenReturn(Flux.just(createdPlayerEvent));

        StepVerifier.create(useCase.execute(request))
                .expectNextMatches(response -> response.getPlayerId().equals("player1"))
                .verifyComplete();

        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(OfferCreated.class));
    }

}