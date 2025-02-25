package com.monopoly.monopoly_managment.application.banck_account.novalidateionfounds;

import com.monopoly.monopoly_managment.application.banck_account.shared.BankAccountMapper;
import com.monopoly.monopoly_managment.application.banck_account.shared.BankAccountResponse;
import com.monopoly.monopoly_managment.application.shared.ports.IEventsRepositoryPort;
import com.monopoly.monopoly_managment.domain.bank_account.BankAccount;
import com.monopoly.shared.application.ICommandUseCase;
import com.monopoly.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Mono;

import java.util.Comparator;

public class NoValidationFoundsUseCase implements ICommandUseCase<NoValididationFoundsRequest, Mono<BankAccountResponse>> {
  private final IEventsRepositoryPort repository;

  public NoValidationFoundsUseCase(IEventsRepositoryPort repository) {
    this.repository = repository;
  }

  @Override
  public Mono<BankAccountResponse> execute(NoValididationFoundsRequest request) {
    return repository
      .findEventsByAggregateId(request.getAggregateId())
      .collectList()
      .map(events ->{
        events.sort(Comparator.comparing(DomainEvent::getWhen));
        BankAccount bankAccount = BankAccount.from(request.getAggregateId(), request.getOwnerId(), events);
        bankAccount.notValidatedFounds(request.getTransactionId(), request.getAmount(), request.getType());
        bankAccount.getUncommittedEvents().forEach(repository::save);
        bankAccount.markEventsAsCommitted();
        return BankAccountMapper.toResponse(bankAccount);
      });
  }
}
