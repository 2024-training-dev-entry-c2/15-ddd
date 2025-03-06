package com.monopoly.monopoly_managment.application.property.assignedowner;

import com.monopoly.monopoly_managment.application.shared.ports.IEventsRepositoryPort;
import com.monopoly.monopoly_managment.domain.property.events.CreatedProperty;
import com.monopoly.monopoly_managment.domain.property.events.OwnerAssigned;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class AssignOwnerUseCaseTest {
  private final AssignOwnerUseCase useCase;
  private final IEventsRepositoryPort repository;

  public AssignOwnerUseCaseTest() {
    repository = Mockito.mock(IEventsRepositoryPort.class);
    useCase = new AssignOwnerUseCase(repository);
  }

  @Test
  void execute() {
    Mockito.when(repository.findEventsByAggregateId(Mockito.anyString())).thenReturn(Flux.just(
    new CreatedProperty("contractId", "mortgageId", "ownerId","updateId" ,"name", 0.0, "BROWN"),
      new OwnerAssigned("ownerId")
    ));

    AssignOwnerRequest request = new AssignOwnerRequest("propertyId", "ownerId");

    StepVerifier.create(useCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals(request.getAggregateId(), response.getPropertyID());
      })
      .verifyComplete();
    Mockito.verify(repository).findEventsByAggregateId(Mockito.anyString());
  }
}