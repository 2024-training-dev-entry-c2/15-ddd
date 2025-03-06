package com.monopoly.monopoly_managment.application.property.createproperty;

import com.monopoly.monopoly_managment.application.shared.ports.IEventsRepositoryPort;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class CreatePropertyUseCaseTest {
  private final CreatePropertyUseCase useCase;

  CreatePropertyUseCaseTest() {
    IEventsRepositoryPort repository = Mockito.mock(IEventsRepositoryPort.class);
    useCase = new CreatePropertyUseCase(repository);
  }

  @Test
  void execute() {
    CreatePropertyRequest request = new CreatePropertyRequest("upgrade", "contract", "mortgage", "owner","name", 1.0, "BROWN");
    StepVerifier
      .create(useCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals(request.getAggregateId(), response.getPropertyID());
      });
  }

}