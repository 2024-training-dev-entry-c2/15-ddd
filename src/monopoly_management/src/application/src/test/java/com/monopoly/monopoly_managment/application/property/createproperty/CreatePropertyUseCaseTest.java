package com.monopoly.monopoly_managment.application.property.createproperty;

import com.monopoly.monopoly_managment.application.shared.repositories.IEventsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class CreatePropertyUseCaseTest {
  private final CreatePropertyUseCase useCase;

  CreatePropertyUseCaseTest() {
    IEventsRepository repository = Mockito.mock(IEventsRepository.class);
    useCase = new CreatePropertyUseCase(repository);
  }

  @Test
  void execute() {
    CreatePropertyRequest request = new CreatePropertyRequest("upgrade", "contract", "mortgage", "owner", "update" ,"name", 1.0, "BROWN");
    StepVerifier
      .create(useCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals(request.getAggregateId(), response.getPropertyID());
      });
  }

}