package com.buildingblocks.actionsandshiftsmanager.application.moveByCard;

import com.buildingblocks.actionsandshiftsmanager.application.shared.Card;
import com.buildingblocks.actionsandshiftsmanager.application.shared.MethodPerformed;
import com.buildingblocks.actionsandshiftsmanager.application.shared.Points;
import com.buildingblocks.actionsandshiftsmanager.application.shared.ports.IEventsRepositoryPort;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class MovementUseCaseTest {
    private final MovementUseCase useCase;
    private final IEventsRepositoryPort repository;

    MovementUseCaseTest() {
        this.repository = Mockito.mock(IEventsRepositoryPort.class);
        this.useCase = new MovementUseCase(repository);
    }

    @Test
    void executeSuccess() {
        Card card = new Card("mexico", "ciudad", "descartar");
        MethodPerformed methodPerformed = new MethodPerformed("id", "descartar", "descartar carta");
        Points points = new Points("id", 4);

        MovementRequest request = new MovementRequest("id", "nextPlayerId", "nextPlayerName", card, "descartar", methodPerformed, points);

        StepVerifier
                .create(useCase.execute(request))
                .assertNext((movementResponse -> {
                    assertNotNull(movementResponse);
                    assertEquals(request.getTypePlace(), movementResponse.getType());
                    assertEquals(request.getPoints().getAmountPoints(), movementResponse.getPoints());
                    assertEquals(request.getMethodPerformed().getName(), movementResponse.getMethodPerformed().getName());
                    assertEquals(request.getMethodPerformed().getDescription(), movementResponse.getMethodPerformed().getDescription());
                }))
                .verifyComplete();
    }

}