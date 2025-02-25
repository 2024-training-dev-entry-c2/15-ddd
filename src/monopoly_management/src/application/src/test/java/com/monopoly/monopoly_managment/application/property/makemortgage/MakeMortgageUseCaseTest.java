package com.monopoly.monopoly_managment.application.property.makemortgage;

import com.monopoly.monopoly_managment.application.shared.ports.IEventsRepositoryPort;
import com.monopoly.monopoly_managment.domain.property.events.CreatedProperty;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class MakeMortgageUseCaseTest {
  private final MakeMortgageUseCase makeMortgageUseCase;
  private final IEventsRepositoryPort repository;

  public MakeMortgageUseCaseTest() {
    repository = Mockito.mock(IEventsRepositoryPort.class);
    makeMortgageUseCase = new MakeMortgageUseCase(repository);
  }

  @Test
  void execute() {
    Mockito.when(repository.findEventsByAggregateId(Mockito.anyString())).thenReturn(Flux.just(
      new CreatedProperty("contractId", "mortgageId", "ownerId","updateId" ,"name", 0.0, "BROWN")
    ));

    MakeMortgageRequest request = new MakeMortgageRequest("aggregateId", "mortgageId", "ownerId", 0.0);

    StepVerifier.create(makeMortgageUseCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals(request.getAggregateId(), response.getPropertyID());
      })
      .verifyComplete();
    Mockito.verify(repository).findEventsByAggregateId(Mockito.anyString());
  }

}