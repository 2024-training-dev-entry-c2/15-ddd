package com.monopoly.monopoly_managment.application.property.cancelmortgage;

import com.monopoly.monopoly_managment.application.shared.repositories.IEventsRepository;
import com.monopoly.monopoly_managment.domain.property.events.CreatedProperty;
import com.monopoly.monopoly_managment.domain.property.events.MortgageConstituted;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class CancelMortgageUseCaseTest {
  private final CancelMortgageUseCase cancelMortgageUseCase;
  private final IEventsRepository repository;

  public CancelMortgageUseCaseTest() {
    repository = Mockito.mock(IEventsRepository.class);
    cancelMortgageUseCase = new CancelMortgageUseCase(repository);
  }

  @Test
  void execute() {
    Mockito.when(repository.findEventsByAggregateId(Mockito.anyString())).thenReturn(Flux.just(
      new CreatedProperty("contractId", "mortgageId", "ownerId","updateId" ,"name", 0.0, "BROWN"),
      new MortgageConstituted("mortgageId", "name", 0.0)
    ));

    CancelMortgageRequest request = new CancelMortgageRequest("aggregateId", "mortgageId", "ownerId", 0.0);

    StepVerifier.create(cancelMortgageUseCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals(request.getAggregateId(), response.getPropertyID());
      })
      .verifyComplete();
    Mockito.verify(repository).findEventsByAggregateId(Mockito.anyString());
  }

}