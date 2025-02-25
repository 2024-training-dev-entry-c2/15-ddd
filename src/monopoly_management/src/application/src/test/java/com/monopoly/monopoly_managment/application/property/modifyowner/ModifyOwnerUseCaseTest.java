package com.monopoly.monopoly_managment.application.property.modifyowner;

import com.monopoly.monopoly_managment.application.shared.ports.IEventsRepositoryPort;
import com.monopoly.monopoly_managment.domain.property.events.CreatedProperty;
import com.monopoly.monopoly_managment.domain.property.events.OwnerAssigned;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class ModifyOwnerUseCaseTest {
  private final ModifyOwnerUseCase useCase;
  private final IEventsRepositoryPort repository;

  public ModifyOwnerUseCaseTest() {
    repository = Mockito.mock(IEventsRepositoryPort.class);
    useCase = new ModifyOwnerUseCase(repository);
  }

  @Test
  void execute() {
    Mockito.when(repository.findEventsByAggregateId(Mockito.anyString())).thenReturn(Flux.just(
      new CreatedProperty("contractId", "mortgageId", "ownerId","updateId" ,"name", 0.0, "BROWN"),
      new OwnerAssigned("ownerId", "propertyId")
    ));

    ModifyOwnerRequest request = new ModifyOwnerRequest("aggregateId", "previousOwnerId","ownerId", "propertyId");

    StepVerifier.create(useCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals(request.getAggregateId(), response.getPropertyID());
    }).verifyComplete();

    Mockito.verify(repository).findEventsByAggregateId(Mockito.anyString());
  }
}