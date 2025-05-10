package com.buildingblocks.actionsandshiftsmanager.application.turnStart;

import com.buildingblocks.actionsandshiftsmanager.application.shared.Card;
import com.buildingblocks.actionsandshiftsmanager.application.shared.Points;
import com.buildingblocks.actionsandshiftsmanager.application.shared.ports.IEventsRepositoryPort;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreateTurnUseCaseTest {
    private final CreateTurnUseCase useCase;
    private final IEventsRepositoryPort repository;

    CreateTurnUseCaseTest() {
        this.repository = Mockito.mock(IEventsRepositoryPort.class);
        this.useCase = new CreateTurnUseCase(repository);
    }

    @Test
    void executeSuccess() {
        Card card = new Card("mexico", "ciudad", "descartar");
        Card card2 = new Card("uruguay", "ciudad", "descartar");
        Card card3 = new Card("colombia", "ciudad", "descartar");
        List<Card> cardList = List.of(card, card2, card3);
        Points points = new Points("id", 4);

        CreateTurnRequest request = new CreateTurnRequest("idPlayer", "Juan", card, cardList, points);

        StepVerifier
                .create(useCase.execute(request))
                .assertNext((response) -> {
                    assertNotNull(response);
                    assertEquals(request.getPlayerName(), response.getPlayerName());
                    assertEquals(request.getPoints().getAmountPoints(), response.getAmountPoints());
                    assertEquals(request.getCard().getId(), response.getCard().getId());
                    assertEquals(request.getCard().getName(), response.getCard().getName());
                    assertEquals(request.getCard().getAction(), response.getCard().getAction());
                    assertEquals(3, response.getCardsObtained().size());
                })
                .verifyComplete();
    }

}