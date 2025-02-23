package com.monopoly.monopoly_managment.application.banck_account.registertransaction;

import com.monopoly.monopoly_managment.application.banck_account.shared.BankAccountMapper;
import com.monopoly.monopoly_managment.application.banck_account.shared.BankAccountResponse;
import com.monopoly.monopoly_managment.application.shared.repositories.IEventsRepository;
import com.monopoly.monopoly_managment.domain.bank_account.BankAccount;
import com.monopoly.monopoly_managment.domain.bank_account.entities.Transaction;
import com.monopoly.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

public class CreateTransactionUseCase implements ICommandUseCase<CreateTransactionRequest, Mono<BankAccountResponse>> {
  private final IEventsRepository repository;

  public CreateTransactionUseCase(IEventsRepository repository) {
    this.repository = repository;
  }

  @Override
  public Mono<BankAccountResponse> execute(CreateTransactionRequest request) {
    return repository
      .findEventsByAggregateId(request.getAggregateId())
      .collectList()
      .map(events ->{
        BankAccount bankAccount = BankAccount.from(request.getAggregateId(), request.getOrigin(), events);
        Transaction transaction = new Transaction(request.getAmount(), request.getType(), request.getOrigin(), request.getDestiny());
        bankAccount.registeredTransaction(bankAccount.getIdentity(), bankAccount.getOwnerId(), transaction.getIdentity(), transaction.getType(), transaction.getAmount(), transaction.getOrigin(), transaction.getDestiny());
        bankAccount.getUncommittedEvents().forEach(repository::save);
        bankAccount.markEventsAsCommitted();
        return BankAccountMapper.toResponse(bankAccount);
      });
  }
}
