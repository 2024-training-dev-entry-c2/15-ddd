package com.dddimplement.exchange.application.createplayer;

import com.dddimplement.exchange.application.shared.repositories.IEventsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class CreatePlayerUseCaseTest {
    private final CreatePlayerUseCase useCase;
    private final IEventsRepository repository;

    public CreatePlayerUseCaseTest() {
        repository = Mockito.mock(IEventsRepository.class);
        useCase = new CreatePlayerUseCase(repository);
    }

    @Test
    void executeSuccess() {
        CreatePlayerRequest request = new CreatePlayerRequest("player1", "red");
        StepVerifier
                .create(useCase.execute(request))
                .assertNext(response -> {
                    assertNotNull(response);
                    assertEquals(request.getName(), response.getName());
                    assertEquals(request.getColor(), response.getColor());
                })
                .verifyComplete();
    }
}