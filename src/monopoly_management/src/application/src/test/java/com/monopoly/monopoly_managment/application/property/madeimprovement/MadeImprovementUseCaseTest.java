package com.monopoly.monopoly_managment.application.property.madeimprovement;

import com.monopoly.monopoly_managment.application.shared.repositories.IEventsRepository;
import com.monopoly.monopoly_managment.domain.property.events.CreatedProperty;
import com.monopoly.monopoly_managment.domain.property.values.TypeImprovementEnum;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class MadeImprovementUseCaseTest {
  private final MadeImprovementUseCase useCase;
  private final IEventsRepository repository;

  public MadeImprovementUseCaseTest() {
    repository = Mockito.mock(IEventsRepository.class);
    useCase = new MadeImprovementUseCase(repository);
  }

  @Test
  void execute() {
    Mockito.when(repository.findEventsByAggregateId(Mockito.anyString())).thenReturn(Flux.just(
      new CreatedProperty("contractId", "mortgageId", "ownerId","updateId" ,"name", 0.0, "BROWN")
    ));

    MadeImprovementRequest request = new MadeImprovementRequest("aggregateId", "improvementId" ,"name", TypeImprovementEnum.HOUSE, 0.0);

    StepVerifier.create(useCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals(request.getAggregateId(), response.getPropertyID());
      })
      .verifyComplete();
    Mockito.verify(repository).findEventsByAggregateId(Mockito.anyString());
  }
}