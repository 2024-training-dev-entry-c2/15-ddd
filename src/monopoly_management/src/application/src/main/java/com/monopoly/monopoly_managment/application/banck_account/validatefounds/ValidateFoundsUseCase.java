package com.monopoly.monopoly_managment.application.banck_account.validatefounds;

import com.monopoly.monopoly_managment.application.banck_account.shared.BankAccountMapper;
import com.monopoly.monopoly_managment.application.banck_account.shared.BankAccountResponse;
import com.monopoly.monopoly_managment.application.shared.repositories.IEventsRepository;
import com.monopoly.monopoly_managment.domain.bank_account.BankAccount;
import com.monopoly.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

public class ValidateFoundsUseCase implements ICommandUseCase<ValidateFoundsRequest, Mono<BankAccountResponse>> {
  private final IEventsRepository repository;

  public ValidateFoundsUseCase(IEventsRepository repository) {
    this.repository = repository;
  }

  @Override
  public Mono<BankAccountResponse> execute(ValidateFoundsRequest request) {
    return repository
      .findEventsByAggregateId(request.getAggregateId())
      .collectList()
      .map(events ->{
        BankAccount bankAccount = BankAccount.from(request.getAggregateId(), request.getOwnerId(), events);
        bankAccount.validatedFounds(request.getTransactionId(), request.getAmount(), request.getType());
        bankAccount.getUncommittedEvents().forEach(repository::save);
        bankAccount.markEventsAsCommitted();
        return BankAccountMapper.toResponse(bankAccount);
      });
  }
}
