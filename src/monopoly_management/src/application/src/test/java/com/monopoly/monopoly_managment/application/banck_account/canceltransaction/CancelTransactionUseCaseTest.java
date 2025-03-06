package com.monopoly.monopoly_managment.application.banck_account.canceltransaction;

import com.monopoly.monopoly_managment.application.shared.ports.IEventsRepositoryPort;
import com.monopoly.monopoly_managment.domain.bank_account.events.CompletedTransaction;
import com.monopoly.monopoly_managment.domain.bank_account.events.CreatedBankAccount;
import com.monopoly.monopoly_managment.domain.bank_account.values.TypeEnum;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CancelTransactionUseCaseTest {
  private final CancelTransactionUseCase useCase;
  private final IEventsRepositoryPort repository;

  public CancelTransactionUseCaseTest(){
    repository = Mockito.mock(IEventsRepositoryPort.class);
    useCase = new CancelTransactionUseCase(repository);
  }

  @Test
  void executeSuccess() {
    Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
      .thenReturn(Flux.just(
        new CreatedBankAccount("bankAccountID", "ownerId"),
        new CompletedTransaction("accountId", "ownerId", "transactionId", TypeEnum.DEPOSIT, 0.0, "origin", "destiny")
      ));

    CancelTransactionRequest request = new CancelTransactionRequest("accountId", "ownerId", "transactionId", TypeEnum.DEPOSIT, 0.0, "origin", "destiny");

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