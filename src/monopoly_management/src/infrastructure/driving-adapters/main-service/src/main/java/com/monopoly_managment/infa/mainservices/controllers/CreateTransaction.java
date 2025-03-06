package com.monopoly_managment.infa.mainservices.controllers;

import com.monopoly.monopoly_managment.application.banck_account.registertransaction.CreateTransactionRequest;
import com.monopoly.monopoly_managment.application.banck_account.registertransaction.CreateTransactionUseCase;
import com.monopoly.monopoly_managment.application.banck_account.shared.BankAccountResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/create-transaction")
public class CreateTransaction {
  private final CreateTransactionUseCase createTransactionUseCase;

  public CreateTransaction(CreateTransactionUseCase createTransactionUseCase) {
    this.createTransactionUseCase = createTransactionUseCase;
  }

  @PostMapping
  public Mono<BankAccountResponse> execute(@RequestBody CreateTransactionRequest request) {
    return createTransactionUseCase.execute(request);
  }
}
