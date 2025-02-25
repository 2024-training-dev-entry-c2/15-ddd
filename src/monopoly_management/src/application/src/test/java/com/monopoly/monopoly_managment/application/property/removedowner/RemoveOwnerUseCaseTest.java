package com.monopoly.monopoly_managment.application.property.removedowner;

import com.monopoly.monopoly_managment.application.shared.ports.IEventsRepositoryPort;
import com.monopoly.monopoly_managment.domain.property.events.CreatedProperty;
import com.monopoly.monopoly_managment.domain.property.events.OwnerAssigned;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class RemoveOwnerUseCaseTest {
  private final RemoveOwnerUseCase removeOwnerUseCase;
  private final IEventsRepositoryPort repository;

  public RemoveOwnerUseCaseTest() {
    repository = Mockito.mock(IEventsRepositoryPort.class);
    removeOwnerUseCase = new RemoveOwnerUseCase(repository);
  }

  @Test
  void execute() {
    Mockito.when(repository.findEventsByAggregateId(Mockito.anyString())).thenReturn(Flux.just(
      new CreatedProperty("contractId", "mortgageId", "ownerId","updateId" ,"name", 0.0, "BROWN"),
      new OwnerAssigned("ownerId", "propertyId")
    ));

    RemoveOwnerRequest request = new RemoveOwnerRequest("propertyId", "ownerId");
    StepVerifier.create(removeOwnerUseCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals(request.getAggregateId(), response.getPropertyID());
      })
      .verifyComplete();
    Mockito.verify(repository).findEventsByAggregateId(Mockito.anyString());
  }

}