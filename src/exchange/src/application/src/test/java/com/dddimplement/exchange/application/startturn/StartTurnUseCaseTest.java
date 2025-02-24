package com.dddimplement.exchange.application.startturn;

import com.dddimplement.exchange.application.shared.repositories.IEventsRepository;
import com.dddimplement.exchange.domain.player.events.CreatedPlayer;
import com.dddimplement.exchange.domain.player.events.TurnStarted;
import com.dddimplement.exchange.domain.player.values.TurnFaseEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StartTurnUseCaseTest {
    private StartTurnUseCase useCase;
    private IEventsRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventsRepository.class);
        useCase = new StartTurnUseCase(repository);
    }

    @Test
    void execute() {
        String aggregateId = "player1";
        StartTurnRequest request = new StartTurnRequest(aggregateId);

        CreatedPlayer createdPlayerEvent = new CreatedPlayer("player1", "red");
        TurnStarted turnStartedEvent = new TurnStarted(TurnFaseEnum.ROLL);

        Mockito.when(repository.findEventsByAggregateId(aggregateId))
                .thenReturn(Flux.just(createdPlayerEvent));

        StepVerifier
                .create(useCase.execute(request))
                .assertNext(response -> {
                    assertNotNull(response);
                    assertEquals(aggregateId, response.getPlayerId());
                    assertEquals("ROLL", response.getTurn().getStatus());
                })
                .verifyComplete();

        Mockito.verify(repository).save(Mockito.any(TurnStarted.class));
    }
}