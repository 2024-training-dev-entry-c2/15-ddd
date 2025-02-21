package com.monopoly.monopoly_managment.application.banck_account.registertransaction;

import com.monopoly.monopoly_managment.application.shared.repositories.IEventsRepository;
import com.monopoly.monopoly_managment.domain.bank_account.events.CompletedTransaction;
import com.monopoly.monopoly_managment.domain.bank_account.events.CreatedBankAccount;
import com.monopoly.monopoly_managment.domain.bank_account.values.TypeEnum;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class CreateTransactionRequestTest {
  private final CreateTransactionUseCase useCase;
  private final IEventsRepository repository;

  public CreateTransactionRequestTest() {
    repository = Mockito.mock(IEventsRepository.class);
    useCase = new CreateTransactionUseCase(repository);
  }

  @Test
  void execute() {
    Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
      .thenReturn(Flux.just(
        new CreatedBankAccount("bankAccountID", "ownerId"),
        new CompletedTransaction("accountId", "ownerId", "transactionId", TypeEnum.DEPOSIT, 0.0, "origin", "destiny")
      ));

    CreateTransactionRequest request = new CreateTransactionRequest("accountId", 0.0, TypeEnum.DEPOSIT, "destiny", "origin");

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