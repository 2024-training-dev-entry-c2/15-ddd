package com.monopoly.monopoly_managment.application.banck_account.createbankaccount;

import com.monopoly.monopoly_managment.application.banck_account.shared.BankAccountMapper;
import com.monopoly.monopoly_managment.application.banck_account.shared.BankAccountResponse;
import com.monopoly.monopoly_managment.application.shared.repositories.IEventsRepository;
import com.monopoly.monopoly_managment.domain.bank_account.BankAccount;
import com.monopoly.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

public class CreateBankAccountUseCase implements ICommandUseCase<CreateBankAccountRequest, Mono<BankAccountResponse>> {
  private final IEventsRepository eventsRepository;

  public CreateBankAccountUseCase(IEventsRepository eventsRepository) {this.eventsRepository = eventsRepository;}

  @Override
  public Mono<BankAccountResponse> execute(CreateBankAccountRequest request) {
    BankAccount bankAccount = new BankAccount();
    bankAccount.createdBankAccount(request.getAggregateId(),request.getOwnerId());
    bankAccount.getUncommittedEvents().forEach(eventsRepository::save);
    bankAccount.markEventsAsCommitted();
    return Mono.just(BankAccountMapper.toResponse(bankAccount));
  }
}