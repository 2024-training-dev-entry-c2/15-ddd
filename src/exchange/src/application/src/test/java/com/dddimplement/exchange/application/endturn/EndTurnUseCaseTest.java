package com.dddimplement.exchange.application.endturn;

import com.dddimplement.exchange.application.shared.repositories.IEventsRepository;
import com.dddimplement.exchange.application.shared.player.PlayerResponse;
import com.dddimplement.exchange.domain.player.events.TurnEnded;
import com.dddimplement.exchange.domain.player.events.CreatedPlayer;
import com.dddimplement.exchange.domain.player.values.TurnFaseEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EndTurnUseCaseTest {
    private EndTurnUseCase useCase;
    private IEventsRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventsRepository.class);
        useCase = new EndTurnUseCase(repository);
    }

    @Test
    void executeSuccess() {
        Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
                .thenReturn(Flux.just(
                        new CreatedPlayer("PlayerName", "Red"),
                        new TurnEnded(TurnFaseEnum.END)
                ));

        EndTurnRequest request = new EndTurnRequest(null, "player1");

        StepVerifier
                .create(useCase.execute(request))
                .assertNext(response -> {
                    assertNotNull(response);
                    assertEquals(TurnFaseEnum.BUILD.name(), response.getTurn().getStatus());
                })
                .verifyComplete();

        Mockito.verify(repository).findEventsByAggregateId(Mockito.anyString());
    }
}