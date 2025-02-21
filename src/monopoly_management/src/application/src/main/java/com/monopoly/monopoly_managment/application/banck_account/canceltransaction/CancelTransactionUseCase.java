package com.monopoly.monopoly_managment.application.banck_account.canceltransaction;

import com.monopoly.monopoly_managment.application.banck_account.shared.BankAccountMapper;
import com.monopoly.monopoly_managment.application.banck_account.shared.BankAccountResponse;
import com.monopoly.monopoly_managment.application.shared.repositories.IEventsRepository;
import com.monopoly.monopoly_managment.domain.bank_account.BankAccount;
import com.monopoly.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

public class CancelTransactionUseCase implements ICommandUseCase<CancelTransactionRequest, Mono<BankAccountResponse>> {
  private final IEventsRepository repository;

  public CancelTransactionUseCase(IEventsRepository repository) {
    this.repository = repository;
  }

  @Override
  public Mono<BankAccountResponse> execute (CancelTransactionRequest request) {
    return repository
      .findEventsByAggregateId(request.getAggregateId())
      .collectList()
      .map(events ->{
        BankAccount bankAccount = BankAccount.from(request.getAggregateId(), request.getOwnerId(), events);
        bankAccount.canceledTransaction(bankAccount.getOwnerId(), request.getTransactionId(), request.getType(), request.getAmount(), request.getOrigin(), request.getDestiny());
        bankAccount.getUncommittedEvents().forEach(repository::save);
        bankAccount.markEventsAsCommitted();
        return BankAccountMapper.toResponse(bankAccount);
      });
  }
}