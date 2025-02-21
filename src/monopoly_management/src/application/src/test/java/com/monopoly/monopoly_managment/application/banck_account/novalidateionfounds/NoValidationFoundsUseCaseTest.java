package com.monopoly.monopoly_managment.application.banck_account.novalidateionfounds;

import com.monopoly.monopoly_managment.application.shared.repositories.IEventsRepository;
import com.monopoly.monopoly_managment.domain.bank_account.events.CompletedTransaction;
import com.monopoly.monopoly_managment.domain.bank_account.events.CreatedBankAccount;
import com.monopoly.monopoly_managment.domain.bank_account.values.TypeEnum;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class NoValidationFoundsUseCaseTest {
  private final NoValidationFoundsUseCase useCase;
  private final IEventsRepository repository;

  public NoValidationFoundsUseCaseTest() {
    repository = Mockito.mock(IEventsRepository.class);
    useCase = new NoValidationFoundsUseCase(repository);
  }

  @Test
  void execute() {
    Mockito.when(repository.findEventsByAggregateId(Mockito.anyString())).thenReturn(Flux.just(
      new CreatedBankAccount("bankAccountID", "ownerId"),
      new CompletedTransaction("accountId", "ownerId", "transactionId", TypeEnum.DEPOSIT, 0.0, "origin", "destiny")
    ));

    NoValididationFoundsRequest request = new NoValididationFoundsRequest("bankAccountID","transactionId" ,1.0, TypeEnum.DEPOSIT, "ownerId");

    StepVerifier
      .create(useCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals(request.getAggregateId(), response.getBankAccountID());
      })
      .verifyComplete();
    Mockito.verify(repository).findEventsByAggregateId(Mockito.anyString());
  }

}