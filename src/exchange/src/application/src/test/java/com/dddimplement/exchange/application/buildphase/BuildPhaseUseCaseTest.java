package com.dddimplement.exchange.application.buildphase;

import com.dddimplement.exchange.application.shared.repositories.IEventsRepository;
import com.dddimplement.exchange.application.shared.player.PlayerResponse;
import com.dddimplement.exchange.domain.player.events.CreatedPlayer;
import com.dddimplement.exchange.domain.player.events.TurnStarted;
import com.dddimplement.exchange.domain.player.values.TurnFaseEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BuildPhaseUseCaseTest {
    private BuildPhaseUseCase useCase;
    private IEventsRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventsRepository.class);
        useCase = new BuildPhaseUseCase(repository);
    }

    @Test
    void executeBuildCitySuccess() {
        Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
                .thenReturn(Flux.just(
                        new CreatedPlayer("PlayerName", "Red"),
                        new TurnStarted(TurnFaseEnum.BUILD)
                ));

        BuildPhaseRequest request = new BuildPhaseRequest(null, "player1", true, false, false, "CITY");

        StepVerifier
                .create(useCase.execute(request))
                .assertNext(response -> {
                    assertNotNull(response);
                    assertEquals(TurnFaseEnum.BUILD.name(), response.getTurn().getStatus());
                })
                .verifyComplete();

        Mockito.verify(repository).findEventsByAggregateId(Mockito.anyString());
    }

    @Test
    void executeBuildPathSuccess() {
        Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
                .thenReturn(Flux.just(
                        new CreatedPlayer("PlayerName", "Red"),
                        new TurnStarted(TurnFaseEnum.BUILD)
                ));

        BuildPhaseRequest request = new BuildPhaseRequest(null, "player1", false, true, false, "PATH");

        StepVerifier
                .create(useCase.execute(request))
                .assertNext(response -> {
                    assertNotNull(response);
                    assertEquals(TurnFaseEnum.BUILD.name(), response.getTurn().getStatus());
                })
                .verifyComplete();

        Mockito.verify(repository).findEventsByAggregateId(Mockito.anyString());
    }

    @Test
    void executeBuildSettlementSuccess() {
        Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
                .thenReturn(Flux.just(
                        new CreatedPlayer("PlayerName", "Red"),
                        new TurnStarted(TurnFaseEnum.BUILD)
                ));

        BuildPhaseRequest request = new BuildPhaseRequest(null, "player1", false, false, true, "SETTLEMENT");

        StepVerifier
                .create(useCase.execute(request))
                .assertNext(response -> {
                    assertNotNull(response);
                    assertEquals(TurnFaseEnum.BUILD.name(), response.getTurn().getStatus());
                })
                .verifyComplete();

        Mockito.verify(repository).findEventsByAggregateId(Mockito.anyString());
    }

    @Test
    void executeNoBuildSuccess() {
        Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
                .thenReturn(Flux.just(
                        new CreatedPlayer("PlayerName", "Red"),
                        new TurnStarted(TurnFaseEnum.BUILD)
                ));

        BuildPhaseRequest request = new BuildPhaseRequest(null, "player1", false, false, false, "");

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