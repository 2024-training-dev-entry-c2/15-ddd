package com.monopoly.monopoly_managment.application.banck_account.canceltransaction;

import com.monopoly.monopoly_managment.application.banck_account.shared.BankAccountMapper;
import com.monopoly.monopoly_managment.application.banck_account.shared.BankAccountResponse;
import com.monopoly.monopoly_managment.application.shared.ports.IEventsRepositoryPort;
import com.monopoly.monopoly_managment.domain.bank_account.BankAccount;
import com.monopoly.shared.application.ICommandUseCase;
import com.monopoly.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Mono;

import java.util.Comparator;

public class CancelTransactionUseCase implements ICommandUseCase<CancelTransactionRequest, Mono<BankAccountResponse>> {
  private final IEventsRepositoryPort repository;

  public CancelTransactionUseCase(IEventsRepositoryPort repository) {
    this.repository = repository;
  }

  @Override
  public Mono<BankAccountResponse> execute (CancelTransactionRequest request) {
    return repository
      .findEventsByAggregateId(request.getAggregateId())
      .collectList()
      .map(events ->{
        events.sort(Comparator.comparing(DomainEvent::getWhen));
        BankAccount bankAccount = BankAccount.from(request.getAggregateId(), request.getOwnerId(), events);
        bankAccount.canceledTransaction(bankAccount.getOwnerId(), request.getTransactionId(), request.getType(), request.getAmount(), request.getOrigin(), request.getDestiny());
        bankAccount.getUncommittedEvents().forEach(repository::save);
        bankAccount.markEventsAsCommitted();
        return BankAccountMapper.toResponse(bankAccount);
      });
  }
}