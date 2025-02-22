package com.monopoly.monopoly_managment.application.banck_account.createbankaccount;

import com.monopoly.monopoly_managment.application.shared.repositories.IEventsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class CreateBankAccountUseCaseTest {
  private final CreateBankAccountUseCase useCase;

  CreateBankAccountUseCaseTest() {
    IEventsRepository repository = Mockito.mock(IEventsRepository.class);
    useCase = new CreateBankAccountUseCase(repository);
  }

  @Test
  void executeSuccess(){
    CreateBankAccountRequest request = new CreateBankAccountRequest("bankAccount-123");
    StepVerifier
      .create(useCase.execute(request))
      .assertNext(response ->{
        assertNotNull(response);
        assertEquals(request.getAggregateId(), response.getBankAccountID());
      });
  }

}