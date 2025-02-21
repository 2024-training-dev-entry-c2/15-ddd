package com.monopoly.monopoly_managment.application.banck_account.novalidateionfounds;

import com.monopoly.monopoly_managment.application.banck_account.shared.BankAccountMapper;
import com.monopoly.monopoly_managment.application.banck_account.shared.BankAccountResponse;
import com.monopoly.monopoly_managment.application.shared.repositories.IEventsRepository;
import com.monopoly.monopoly_managment.domain.bank_account.BankAccount;
import com.monopoly.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

public class NoValidationFoundsUseCase implements ICommandUseCase<NoValididationFoundsRequest, Mono<BankAccountResponse>> {
  private final IEventsRepository repository;

  public NoValidationFoundsUseCase(IEventsRepository repository) {
    this.repository = repository;
  }

  @Override
  public Mono<BankAccountResponse> execute(NoValididationFoundsRequest request) {
    return repository
      .findEventsByAggregateId(request.getAggregateId())
      .collectList()
      .map(events ->{
        BankAccount bankAccount = BankAccount.from(request.getAggregateId(), request.getOwnerId(), events);
        bankAccount.notValidatedFounds(request.getTransactionId(), request.getAmount(), request.getType());
        bankAccount.getUncommittedEvents().forEach(repository::save);
        bankAccount.markEventsAsCommitted();
        return BankAccountMapper.toResponse(bankAccount);
      });
  }
}
